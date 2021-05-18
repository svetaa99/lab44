<template>
  <div>
    <DermatologistVisitsList
      v-bind:dermatologistVisits="this.dermatologistVisits"
    />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import DermatologistVisitsList from "../components/DermatologistVisitList.vue";

const API_URL = config.API_URL;

export default {
  components: {
    DermatologistVisitsList,
  },
  data() {
    return {
      dermatologistVisits: [],
    };
  },
  mounted() {
    axios.get(`${API_URL}/appointments/visits-to-dermatologists`).then((response) => {
      this.dermatologistVisits = response.data;
    });
  },
};
</script>

<style>
</style>