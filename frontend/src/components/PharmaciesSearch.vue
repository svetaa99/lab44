<template>
  <div class="input-group">
    <div class="form-outline">
      <input type="search" id="form1" class="form-control" v-model="searchName"/>
    </div>
    <button type="button" class="btn btn-primary" v-on:click="searchPharmacy()">
      <i class="fas fa-search">Pretrazi</i>
    </button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PharmaciesSearch',
  data() {
    return {
      searchName: '',
    }
  },
  methods: {
    searchPharmacy: function() {
      if(this.searchName == '')
			{
				axios
				.get('http://localhost:8000/pharmacies/all')
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies); this.$emit('clicked', this.pharmacies)})
			}
			else
			{
				axios
				.get(`http://localhost:8000/pharmacies/search/${this.searchName}`)
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies); this.$emit('clicked', this.pharmacies)})
			}
    }
  }
}
</script>

<style>

</style>