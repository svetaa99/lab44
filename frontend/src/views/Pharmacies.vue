<template>
  <div>
    <PharmaciesSearch v-model="pharmacies" @clicked="onSearchClick"/>
    <PharmaciesList v-bind:pharmacies="this.pharmacies"/>

  </div>
</template>

<script>
import axios from 'axios'
import PharmaciesList from '@/components/PharmaciesList.vue'
import PharmaciesSearch from '@/components/PharmaciesSearch.vue'

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
				.get('http://localhost:8000/pharmacies/all')
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies)})
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