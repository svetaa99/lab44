<template>
  <div>
    <h1>{{pharmacy.name}}</h1>
    <ul v-for="m in pharmacy.medicines" :key="m.id">
      <li>{{m.name}}</li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'

const API_URL = config.API_URL

export default {
  name: 'PharmacyPage',
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
      .then(response => {this.pharmacy = response.data; console.log(this.pharmacy)})
  }
}
</script>

<style>

</style>