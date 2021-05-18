<template>
  <div>
    <div class="container" align="center" style="width: 450px">
      <date-time-component :pharmacies="this.pharmacies" @clicked="updatePharmacies"></date-time-component>
      <!-- <pharmacies-search></pharmacies-search> -->
    </div>
    <br />
    <pharmacies-list :appointment="true" :date="this.date" :time="this.time" :pharmacies="this.pharmacies"></pharmacies-list>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import DateTimeComponent from "@/components/DateTimeComponent.vue";
import PharmaciesList from "@/components/PharmaciesList.vue";
import PharmaciesSearch from "@/components/PharmaciesSearch.vue";
const API_URL = config.API_URL;
export default {
  components: {
    "date-time-component": DateTimeComponent,
    "pharmacies-list": PharmaciesList,
    "pharmacies-search": PharmaciesSearch,
  },
  data() {
    return {
      pharmacies: [],
      date: "",
      time: "",
    };
  },
  mounted() {
    axios.get(`${API_URL}/pharmacies/all`).then((response) => {
      this.pharmacies = response.data;
    });
  },
  methods: {
    updatePharmacies(retObj) {
      const {date, time, pharmacies} = retObj
      this.pharmacies = pharmacies
      this.date = date
      this.time = time
    }
  }
};
</script>

<style>
</style>