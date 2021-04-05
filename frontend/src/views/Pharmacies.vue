<template>
  <div>
    <PharmaciesSearch v-model="pharmacies" @clicked="onSearchClick"/>
    <PharmaciesList :pharmacies="this.pharmacies"/>

  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
import PharmaciesList from '@/components/PharmaciesList.vue'
import PharmaciesSearch from '@/components/PharmaciesSearch.vue'

const API_URL = config.API_URL;


export default {
  name: 'Pharmacies',
  components: {
    PharmaciesList,
    PharmaciesSearch,
  },
  data() {
    return {
      pharmacies: [],
    }
  },
  mounted () {
    axios
				.get(`${API_URL}/pharmacies/all`)
				.then(response => {this.pharmacies = response.data; })
  },
  methods: {
    onSearchClick (pharmacies) {
      this.pharmacies = pharmacies;
    }
  }
}
</script>

<style>

</style>