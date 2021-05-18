<template>
  <div class="container">
    <h1>Add dermatologist to the pharmacy</h1>
    <br/>
    <h3>Choose available doctor</h3>
    <DoctorListComponent :doctorRole="2" :doctors="doctors" :action="'add'" @clicked="addDermatologist"/>
    <div v-if="Object.keys(selectedDoctor).length !== 0">
      <br/>
      <h4>Selected doctor:<br/> {{selectedDoctor.name}}</h4>
    </div>
    <br/>
    <h3>Select worktime</h3>
    <div>
        <hr>
        <div class="mob"> <label class="text-grey mr-1">From</label> <input class="ml-1" type="time" name="from" v-model="workTime.startTime"> </div>
        <hr>
        <div class="mob mb-2"> <label class="text-grey mr-4">To</label> <input class="ml-1" type="time" name="to" v-model="workTime.finishTime"> </div>
        <hr>
        <button type="button" class="btn btn-success" @click="saveDermatologist">Save</button>
        <button type="button" class="btn btn-outline-danger" id="cancelButton">Cancel</button>
    </div>
  </div>
</template>

<script>
import DoctorListComponent from '../components/DoctorListComponent.vue'
import axios from 'axios'
import { config } from "@/config.js";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  components: { DoctorListComponent },
  data() {
    return {
      admin: {},
      doctors: [],
      selectedDoctor: {},
      workTime: {
        startTime: '',
        finishTime: '',
      },
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data

        axios
          .get(`${API_URL}/dermatologists/all`)
          .then(response => {
            const dermas = response.data
            dermas.forEach(d => {
              if (!d.pharmacies.some(p => p.id === this.admin.pharmacy.id)) {
                this.doctors.push(d);
              }
            })
          })
      })
  },
  methods: {
    addDermatologist(doctor) {
      this.selectedDoctor = doctor;
    },
    saveDermatologist() {
      if (Object.keys(this.selectedDoctor).length === 0) {
        Swal.fire({
          title: 'Error',
          text: 'No dermatologist selected!',
          icon: 'error',
        })
      } else if (this.workTime.startTime === '' || this.workTime.finishTime === '') {
        Swal.fire({
          title: 'Error',
          text: 'No worktime selected!',
          icon: 'error',
        })
      } else {
        axios
          .post(`${API_URL}/dermatologists/add-to-pharmacy/${this.selectedDoctor.id}`, this.workTime)
          .then(response => {
            if (response.status === 200) {
              Swal.fire({
                title: 'Success',
                text: 'Successfully added dermatologist to the pharmacy',
                icon: 'success',
              }).then(result => {
                if (result.isConfirmed) {
                  window.location.reload()
                }
              })
            }
          })
      }
    }
  }
}
</script>

<style>

</style>