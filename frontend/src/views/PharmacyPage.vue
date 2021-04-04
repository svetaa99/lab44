<template>
  <div>
    <h1>{{pharmacy.name}}</h1>
    <MedicinesList :medicines="this.pharmacy.medicines"/>
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
    }
  },
  mounted() {
    const arr = window.location.href.split('/');
    const id = arr[arr.length - 1];
    axios
      .get(`${API_URL}/pharmacies/${id}`)
      .then(response => this.pharmacy = response.data)
  }
}
</script>

<style>

</style>