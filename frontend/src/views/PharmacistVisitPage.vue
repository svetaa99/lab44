<template>
  <div>
    <PharmacistVisitsList
      v-bind:pharmacistVisits="this.pharmacistVisits"
    />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import PharmacistVisitsList from "../components/PharmacistVisitList.vue";

const API_URL = config.API_URL;

export default {
  components: {
    PharmacistVisitsList,
  },
  data() {
    return {
      pharmacistVisits: [],
    };
  },
  mounted() {
    axios.get(`${API_URL}/appointments/visits-to-pharmacist`).then((response) => {
      this.pharmacistVisits = response.data;
    });
  },
};
</script>

<style>
</style>