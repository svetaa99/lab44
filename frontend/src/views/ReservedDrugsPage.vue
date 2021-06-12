<template>
  <div>
    <ReservedDrugsComponent v-bind:reservedDrugs="this.reservedDrugs" @clicked="handleCancelClick"/>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import ReservedDrugsComponent from "../components/ReservedDrugsComponent.vue";

const API_URL = config.API_URL;

export default {
  components: {
    ReservedDrugsComponent,
  },
  data() {
    return {
      reservedDrugs: [],
    };
  },
  mounted() {
    axios.get(`${API_URL}/reservations/reservations-my`).then((response) => {
      this.reservedDrugs = response.data;
    });
  },
  methods: {
    handleCancelClick(reservationId) {
      axios
        .get(`${API_URL}/reservations/cancel-reservation/${reservationId}`)
        .then((response) => {
          this.reservedDrugs = response.data;
        });
    }
  }
};
</script>

<style>
</style>