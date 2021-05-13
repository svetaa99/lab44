<template>
  <div class="padding-bottom">
    <h3>Filter by:</h3>
    <div v-if="doctorRole === 3">
      <label for="rating" class="form-label">Rating</label><br/>
      <select id="rating" v-model="filter.rating">
        <option value=-1>None</option>
        <option value="10">10</option>
        <option value="9">9</option>
        <option value="8">8</option>
        <option value="7">7</option>
        <option value="6">6</option>
        <option value="5">5</option>
      </select>
    </div>

    <br/>
    
    <div class="padding-bottom">
      <label for="pharmacy" class="form-label">Pharmacy</label><br/>
      <select id="pharmacy" v-model="filter.pharmacy">
        <option value=-1>All</option>
        <option v-for="p in pharmacies" :key="p.id" :value="p.id">{{p.name}}</option>
      </select>
    </div>
    <button type="button" class="btn btn-primary" @click="handleFilterClick">Filter</button>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  props: {
    doctorRole: Number,
  },
  data() {
    return {
      pharmacies: [],
      filter: {
        rating: -1,
        pharmacy: -1,
      }
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/pharmacies/all`)
      .then(response => {
        this.pharmacies = response.data
      })
  },
  methods: {
    handleFilterClick() {
      const filterString = this.filter.rating == -1 ? "false" : "true"
      const pharmacyString = this.filter.pharmacy == -1 ? "false" : "true"
      const params =  filterString + "+" + pharmacyString
      const values = this.filter.rating + "+" + this.filter.pharmacy

      const filter = {
        params: params,
        values: values,
      }

      this.$emit('clicked', filter)
    }
  }
}
</script>

<style>

</style>