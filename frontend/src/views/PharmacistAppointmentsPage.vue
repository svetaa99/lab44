<template>
  <div>
    <PharmacistAppointmentsList
      v-bind:pharmacistAppointments="this.pharmacistAppointments"
    />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import PharmacistAppointmentsList from "../components/PharmacistAppointmentsList.vue";

const API_URL = config.API_URL;

export default {
  components: {
    PharmacistAppointmentsList,
  },
  data() {
    return {
      pharmacistAppointments: [],
    };
  },
  mounted() {
    axios.get(`${API_URL}/appointments/to-pharmacist`).then((response) => {
      this.pharmacistAppointments = response.data;
    });
  },
};
</script>

<style>
</style>