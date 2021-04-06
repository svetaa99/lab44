<template>
  <div>
    <h1>{{pharmacy.name}}</h1>
    <MedicinesList :medicines="this.medicines"/>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
import MedicinesList from '@/components/MedicinesList'

const API_URL = config.API_URL

export default {
  name: 'PharmacyPage',
  components: {
    MedicinesList,
  },
  data() {
    return {
      pharmacy: {},
      medicines: [],
    }
  },
  mounted() {
    const arr = window.location.href.split('/');
    const id = arr[arr.length - 1];
    axios
      .get(`${API_URL}/pharmacies/${id}`)
      .then(response => this.pharmacy = response.data)

    axios
      .get(`${API_URL}/pharmacy-medicines/get-medicines/${id}`)
      .then(response => {this.medicines = response.data; console.log(response.data)})
  }
}
</script>

<style>

</style>