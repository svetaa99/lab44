<template>
  <div>
    <PharmacyComponent :pharmacy="pharmacy" />
    <MapComponent v-if="pharmacy.address" :address="pharmacy.address" :edit="false"/>
    <MedicinesSearch v-model="searchName" @clicked="onSearchClick"/>
    <MedicinesList :medicines="this.medicines" :extra="extra"/>
    <hr>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
import MedicinesList from '@/components/MedicinesList'
import MedicinesSearch from '@/components/MedicinesSearch'
import PharmacyComponent from '@/components/PharmacyComponent.vue'
import MapComponent from '../components/MapComponent.vue'

const API_URL = config.API_URL

export default {
  name: 'PharmacyPage',
  components: {
    MedicinesList,
    MedicinesSearch,
    PharmacyComponent,
    MapComponent,
  },
  data() {
    return {
      pharmacy: {},
      medicines: [],
      searchName: '',
      extra: 'update',
    }
  },
  mounted() {
    const arr = window.location.href.split('/');
    const id = arr[arr.length - 1];
    axios
      .get(`${API_URL}/pharmacies/${id}`)
      .then(response => {this.pharmacy = response.data;})

    axios
      .get(`${API_URL}/pharmacy-medicines/get-medicines/${id}`)
      .then(response => {
        const pms = response.data
        pms.map(pm => {
          this.medicines.push(pm.medicine)
        })
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
            console.log(pms)
            this.medicines = []
            pms.map(pm => {
              this.medicines.push(pm.medicine)
            })
            console.log(this.medicines)
          })
      }
    }
  }
}
</script>

<style>

</style>