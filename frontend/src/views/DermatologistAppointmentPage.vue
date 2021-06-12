<template>
  <div>
    <DermatologistAppointmentsList
      v-bind:dermatologistAppointments="this.dermatologistAppointments"
      @clicked="update"
    />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import DermatologistAppointmentsList from "../components/DermatologistAppointmentsList.vue";

const API_URL = config.API_URL;

export default {
  components: {
    DermatologistAppointmentsList,
  },
  data() {
    return {
      dermatologistAppointments: [],
    };
  },
  mounted() {
    axios.get(`${API_URL}/appointments/to-dermatologists`).then((response) => {
      this.dermatologistAppointments = response.data;
    });
  },
  methods: {
    update(appointmentId) {
      axios
        .get(`${API_URL}/appointments/cancel-my-reservation/${appointmentId}`)
        .then((response) => {
          this.dermatologistAppointments = response.data;
        });
    },
  },
};
</script>

<style>
</style>