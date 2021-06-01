<template>
    <div class="container">
      <DoctorListComponent :doctors="dermatologists" :doctorRole="2" :action="'select'" @clicked="handleSelectClick"/>

      <div>
              <div class="card mb-3"> <!-- Only dermatologist can make new term -->
                <div class="card-body">
                  <hr>
                  <table class="table">
                    <thead class="thead-dark">
                        <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Start</th>
                        <th scope="col">Finish</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="term in freeTerms" :key="term.id">
                          <td>{{formatDate(term.start.date)}}</td>
                          <td>{{formatTime(term.start.time)}}</td>
                          <td>{{formatTime(term.finish.time)}}</td>
                        </tr>
                    </tbody>
                    </table>
                    <hr>
                </div>
                </div>

      <div>
        <b-form-datepicker id="datepicker-lg" size="lg" locale="en" v-model="newStartDate"></b-form-datepicker>
        <hr>
        <div class="mob"> <label class="text-grey mr-1">From</label> <input class="ml-1" type="time" name="from" v-model="newStartTime"> </div>
        <hr>
        <div class="mob mb-2"> <label class="text-grey mr-4">To</label> <input class="ml-1" type="time" name="to" v-model="newFinishTime"> </div>
        <hr>
        <button type="button" class="btn btn-success" v-on:click="saveNewTerm()">Save</button>
        <button type="button" class="btn btn-outline-danger" id="cancelButton">Cancel</button>
      </div>
    </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2'
import axios from 'axios'
import { config } from '@/config.js'
import DoctorListComponent from '../components/DoctorListComponent.vue'

const API_URL = config.API_URL

export default {
  components: {
    DoctorListComponent,
  },
  data() {
    return {
      admin: {},
      dermatologists: [],
      selectedDermatologist: {},
      newStartDate: '',
      newStartTime: '',
      newFinishTime: '',
      freeTerms: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data;
      })

    axios
      .get(`${API_URL}/labadmins/all-dermatologists`)
      .then(response => {
        this.dermatologists = response.data;
      })

    
  },
  methods: {
    handleSelectClick(dermatologist) {
      this.selectedDermatologist = dermatologist;
      axios
        .get(`http://localhost:8000/doctorterms/definedterms-admin/${this.admin.pharmacy.id}/${this.selectedDermatologist.id}`)
        .then(response => {
          console.log("Defined terms\n")
          console.log(response.data)
          this.freeTerms = response.data
        })
    },
    formatDate: function(dateInJson){
        return "" + dateInJson.day.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "." + dateInJson.month.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "." + dateInJson.year + "." 
      },
      formatTime: function(timeInJson){
        return "" + timeInJson.hour.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + ":" + timeInJson.minute.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          })
      },
      formatDateTimeForReq: function(dateTimeJson){
        return "" + dateTimeJson.date.year + "-" 
        + dateTimeJson.date.month.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "-" 
        + dateTimeJson.date.day.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "T" 
        + dateTimeJson.time.hour.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + ":" 
        + dateTimeJson.time.minute.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          })
      },
    saveNewTerm: function(){
      if (Object.keys(this.selectedDermatologist).length === 0) {
        Swal.fire({
          title: 'No dermatologist selected!',
          text: 'Select dermatologist from table to add new term.',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      }

      if (this.newStartDate === '') {
        Swal.fire({
          title: 'No date selected!',
          text: 'Select date for the term.',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      }

      var newTerm = {
        doctorId: this.selectedDermatologist.id, 
        pharmacyId: this.admin.pharmacy.id, 
        start: (this.newStartDate + "T" + this.newStartTime), 
        finish: (this.newStartDate + "T" + this.newFinishTime)
      }

      if(this.newStartTime < this.newFinishTime){
          axios
          .post(`http://localhost:8000/doctorterms/createnew-admin`, newTerm) //add param
          .then(response => {this.handleResponse(response.data)})
      }
      else{
        Swal.fire({
          title: 'Invalid time values',
          text: 'Finish time is before start time!',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      }
    },
    handleResponse: function(respData){
        respData == "Taken term" ? 
        Swal.fire({
          title: 'Taken term',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      : respData == "Not in your working hours" ? 
        Swal.fire({
          title: 'Not in your working hours',
          icon: 'error',
          confirmButtonText: 'Ok'
        }) : Swal.fire({
          title: 'Success',
          text: 'Successfully added new free term',
          icon: 'success',
          confirmButtonText: 'Ok'
        }).then(result => {
          if (result.isConfirmed) {
            console.log("After add term: \n");
            console.log(respData)
            this.freeTerms = respData;
          }
        })
      },
  }
}
</script>

<style>

</style>