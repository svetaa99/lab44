<template>
    <div class="container">
    <div class="main-body">
          <!-- User information -->
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                      <h4>{{patient.name}} {{patient.surname}}</h4>
                      <p class="text-secondary mb-1">{{patient.email}}</p>
                      <p class="text-muted font-size-sm">{{patient.category}}</p>
                      <button class="btn btn-outline-primary">Scheduled terms</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>      
            <!-- Search form and table with free terms -->
            <div class="col-md-8">
              <div class="card mb-3" v-if="doctorRoles.includes(2)"> <!-- Only dermatologist can make new term -->
                <div class="card-body">
                  <div class="row">
                    <div class="input-group">
                    <input type="search" class="form-control rounded" placeholder="YYYY-MM-dd" aria-label="Search"
                        aria-describedby="search-addon" v-model="searchDateTimeObject.searchDate"/>
                    <input type="search" class="form-control rounded" placeholder="HH:mm" aria-label="Search"
                        aria-describedby="search-addon" v-model="searchDateTimeObject.searchTime"/>
                    <button type="button" class="btn btn-outline-primary" v-on:click="searchDateTime()">Search</button>
                    </div>
                  </div>
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
                        <tr v-for="term in freeTerms" :key="term.id" v-on:click="selectTerm(term)" v-bind:class="{selected : selectedTerm == term}">
                          <td>{{formatDate(term.start.date)}}</td>
                          <td>{{formatTime(term.start.time)}}</td>
                          <td>{{formatTime(term.finish.time)}}</td>
                        </tr>
                    </tbody>
                    </table>
                    <hr>
                    <button class="btn btn-primary" v-on:click="newTermShow=true" id="newTermButton">New term</button>
                    <button type="button" class="btn btn-primary" :disabled='isDisabled' id="finishButton" v-on:click="makeReservation()">Finish</button>
                </div>
                </div>
                <div class="card mb-3">
                    <div class="card-body" v-if="newTermShow || doctorRoles.includes(3)" id="newTermDiv"> <!-- if dermatist clicked on new term or if doctor is pharmacist since pharmacists do not have terms-->
                        <b-form-datepicker id="datepicker-lg" size="lg" locale="en" v-model="newStartDate"></b-form-datepicker>
                        <hr>
                        <div class="mob"> <label class="text-grey mr-1">From</label> <input class="ml-1" type="time" name="from" v-model="newStartTime"> </div>
                        <hr>
                        <div class="mob mb-2"> <label class="text-grey mr-4">To</label> <input class="ml-1" type="time" name="to" v-model="newFinishTime"> </div>
                        <hr>
                        <button type="button" class="btn btn-success" v-on:click="saveNewTerm()">Save</button>
                        <button type="button" class="btn btn-outline-danger" v-if="!doctorRoles.includes(3)" id="cancelButton" v-on:click="newTermShow=false">Cancel</button>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </div>
</template>

<script>
import axios from 'axios'
import Swal from 'sweetalert2'
export default {
    name: "DoctorReservation",
    props: {
        freeTerms: Array,
    },
    data() {
        return {
            visitId: this.$route.params.id,
            newTermShow: false,
            patient: null,  //this data will be hardcoded for now because there is no authentification ATM
            doctorRoles: [],
            pharmacyId: '',
            newStartDate: '',
            newStartTime: '', //these attributes will be selected by doctor
            newFinishTime: '',
            selectedTerm: null,
            searchDateTimeObject : {
              searchDate: '',
              searchTime: '',
            }
        }
    },
    computed: {
      isDisabled: function(){
        return this.selectedTerm==null;
      }
    },
    methods : {
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
      selectTerm: function(tableTerm){
        this.selectedTerm = tableTerm;
      },
      saveNewTerm: function(){
        //axios request to write new term into database
        //doctorId and pharmacyId replaced on backend
        var newTerm = {doctorId: 1, pharmacyId: this.pharmacyId, start: (this.newStartDate + "T" + this.newStartTime), finish: (this.newStartDate + "T" + this.newFinishTime)}
        if(this.doctorRoles.includes(3)){
          this.makeReservation();
        }
        else{
          axios
          .post('http://localhost:8000/doctorterms/createnew', newTerm)
          .then(response => {this.handleResponse(response.data)})
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
        }) : this.freeTerms = respData;
      },
      makeReservation: function(){
        //dialog for are you sure first - once clicked yes action preforms
        //doctorId changes on backend
        if(this.doctorRoles.includes(3))
          var newReservation = {patientId: this.patient.id, doctorId: 1, start: this.newStartDate + "T" + this.newStartTime, finish: this.newStartDate + "T" + this.newFinishTime, pharmacy: this.pharmacyId};
        else
          var newReservation = {patientId: this.patient.id, doctorId: 1, start: this.formatDateTimeForReq(this.selectedTerm.start), finish: this.formatDateTimeForReq(this.selectedTerm.finish), pharmacy: this.pharmacyId}
        axios
        .post('http://localhost:8000/appointments/make-appointment', newReservation)
        .then(response => {response.data === "Patient unavailable" ? Swal.fire({
          text: 'Patient is unavailable in given term',
          icon: 'error',
          confirmButtonText: 'Ok'
        }) : 
        Swal.fire({
          title: 'New appointment added to work calendar',
          icon: 'success',
          confirmButtonText: 'Ok'
        });})
      },
      searchDateTime: function(){
        console.log(this.searchDateTimeObject.searchDate + " " + this.searchDateTimeObject.searchTime);
        axios
        .post('http://localhost:8000/doctorterms/search-date-time', this.searchDateTimeObject)
        .then(response => {this.freeTerms = response.data})
      }
    },
    mounted: function(){
        axios 
        .get('http://localhost:8000/doctorterms/definedterms') 
        .then(response => {this.freeTerms = response.data; console.log(this.freeTerms)});
        axios
        .get(`http://localhost:8000/appointments/get-user/${this.visitId}`)
        .then(response => {this.patient = response.data; console.log(this.patient)});
        axios
        .get(`http://localhost:8000/appointments/get-pharmacy/${this.visitId}`)
        .then(response => { this.pharmacyId = response.data; })

        const tokenItem = JSON.parse(localStorage.getItem('jwt'));
        tokenItem.token.roles.map(el => {
          this.doctorRoles.push(el.id);
        });
    }

}
</script>

<style>
#cancelButton{
    margin: 0 10px;
}
.selected{
  background-color:deepskyblue;
}
#finishButton{
  margin: 0 20px
}
#newTermButton{
  margin: 0 20px
}
.swal-overlay {
  background-color: rgba(43, 165, 137, 0.45);
}
</style>