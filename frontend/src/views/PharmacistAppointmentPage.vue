<template>
  <div>
    <div class="container" align="center" style="width: 450px">
      <date-time-component v-model="pharmacies" @clicked="updatePharmacies"></date-time-component>
      <!-- <pharmacies-search></pharmacies-search> -->
    </div>
    <br />
    <pharmacies-list :appointment="true" :pharmacies="this.pharmacies"></pharmacies-list>
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
    };
  },
  mounted() {
    axios.get(`${API_URL}/pharmacies/all`).then((response) => {
      this.pharmacies = response.data;
    });
  },
  methods: {
    updatePharmacies(pharmacies) {
      this.pharmacies = pharmacies
    }
  }
};
</script>

<style>
</style>