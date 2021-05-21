<template>
  <div>
    <br />
    <h1>List of pharmacies</h1>
    <br />
    <div class="container" align="center" style="width: 700px">
      <div class="row">
        <div class="col-lg">
          <PharmaciesSearch v-model="pharmacies" @clicked="onSearchClick" />
        </div>
        <div class="col-lg">
          <PharmaciesFilter v-model="pharmacies" @clicked="onFilterClick" />
        </div>
      </div>
    </div>
    <div class="album py-5">
      <div class="container">
        <hr />
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div
            class="col"
            v-for="pharmacy in pharmacies"
            v-bind:key="pharmacy.id"
          >
            <!-- <PharmaciesList :pharmacies="this.pharmacies" /> -->
            <CardComponent :pharmacy="pharmacy" />
            <br />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import PharmaciesList from "@/components/PharmaciesList.vue";
import PharmaciesSearch from "@/components/PharmaciesSearch.vue";
import PharmaciesFilter from "@/components/PharmaciesFilter.vue";
import CardComponent from "@/components/CardComponent.vue";

const API_URL = config.API_URL;

export default {
  name: "Pharmacies",
  components: {
    PharmaciesList,
    PharmaciesSearch,
    PharmaciesFilter,
    CardComponent,
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
body {
  text-align: center;
}
</style>