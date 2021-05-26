<template>
  <div class="container">
    <PharmacyComponent v-if="pharmacy" :pharmacy="pharmacy" :admin="admin"/>
    <MapComponent v-if="pharmacy" :address="pharmacy.address" :edit="false"/>
    <h3>Medicines</h3>
    <h6>Search medicines</h6>
    <MedicinesSearch v-model="searchName" @clicked="onSearchClick"/>
    <MedicinesList :medicines="this.medicines" :extra="extra"/>
    <hr/>
    <div class="row">
      <div class="col">
        <h3>Dermatologists</h3>
        <DoctorListComponent v-if="dermatologists" :doctors="dermatologists" :doctorRole="2" @clicked="handleRowDblClick"/>
      </div>
      <div class="col">
        <h3>Pharmacists</h3>
        <DoctorListComponent v-if="pharmacists" :doctors="pharmacists" :doctorRole="3" />
      </div>
    </div>

    <div v-if="selectedDermatologist">
      <div>
        <h3>Dermatologists free terms</h3>
        <div class="card mb-3">
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
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
import MedicinesList from '@/components/MedicinesList'
import MedicinesSearch from '@/components/MedicinesSearch'
import PharmacyComponent from '@/components/PharmacyComponent.vue'
import MapComponent from '../components/MapComponent.vue'
import DoctorListComponent from '../components/DoctorListComponent.vue'

const API_URL = config.API_URL

export default {
  name: 'PharmacyPage',
  components: {
    MedicinesList,
    MedicinesSearch,
    PharmacyComponent,
    MapComponent,
    DoctorListComponent,
  },
  data() {
    return {
      pharmacy: null,
      admin: null,
      medicines: null,
      dermatologists: null,
      pharmacists: null,
      selectedDermatologist: null,
      freeTerms: null,
      searchName: '',
      extra: '',
    }
  },
  mounted() {
    const arr = window.location.href.split('/');
    const id = arr[arr.length - 1];
    axios
      .get(`${API_URL}/pharmacies/${id}`)
      .then(response => {
        this.pharmacy = response.data;

        axios
          .get(`${API_URL}/labadmins/registered-admin`)
          .then(response => {
            this.admin = response.data
            if (this.admin.pharmacy.id === this.pharmacy.id) {
              this.extra = "update"
            }
          })

        axios
          .get(`${API_URL}/dermatologists/all-from-pharmacy/${this.pharmacy.id}`)
          .then(response => {
            this.dermatologists = response.data
          })

        axios
          .get(`${API_URL}/pharmacist/all-from-pharmacy/${this.pharmacy.id}`)
          .then(response => {
            this.pharmacists = response.data
          })
      })

    axios
      .get(`${API_URL}/pharmacy-medicines/get-medicines/${id}`)
      .then(response => {
        const pms = response.data
        var meds = []
        pms.map(pm => {
          meds.push(pm.medicine)
        })
        this.medicines = meds
      })
  },
  methods: {
    onSearchClick(searchName) {
      if (searchName == '') {
        axios
          .get(`${API_URL}/pharmacy-medicines/get-medicines/${this.pharmacy.id}`)
          .then(response => {
            const pms = response.data
            this.medicines = []
            pms.map(pm => {
              this.medicines.push(pm.medicine)
            })
      })
      } 
      else {
        axios
          .get(`${API_URL}/pharmacy-medicines/get-medicines-by-name/${this.pharmacy.id}/${searchName}`)
          .then(response => {
            const pms = response.data
            this.medicines = []
            pms.map(pm => {
              this.medicines.push(pm.medicine)
            })
          })
      }
    },
    handleRowDblClick(doctor) {
      this.selectedDermatologist = doctor;
      axios
        .get(`${API_URL}/doctorterms/definedterms-admin/${this.pharmacy.id}/${this.selectedDermatologist.id}`)
        .then(response => {
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
    
  }
}
</script>

<style>

</style>