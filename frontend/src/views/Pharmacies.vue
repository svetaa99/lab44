<template>
  <div>
    <hr />
    <h1>List of pharmacies</h1>
    <br />
    <div class="container">
      <div class="row">
        <div class="col-lg">
          <PharmaciesSearch v-model="pharmacies" @clicked="onSearchClick" />
        </div>
        <div class="col-lg">
          <PharmaciesFilter v-model="pharmacies" @clicked="onFilterClick"/>
        </div>
      </div>
    </div>
    <br />
    <PharmaciesList :pharmacies="this.pharmacies" />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import PharmaciesList from "@/components/PharmaciesList.vue";
import PharmaciesSearch from "@/components/PharmaciesSearch.vue";
import PharmaciesFilter from "@/components/PharmaciesFilter.vue";

const API_URL = config.API_URL;

export default {
  name: "Pharmacies",
  components: {
    PharmaciesList,
    PharmaciesSearch,
    PharmaciesFilter,
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
    onSearchClick(pharmacies) {
      this.pharmacies = pharmacies;
    },
    onFilterClick(pharmacies) {
      this.pharmacies = pharmacies;
    },
  },
};
</script>

<style>
</style>