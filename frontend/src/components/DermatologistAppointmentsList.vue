<template>
  <div class="container">
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Dermatologist</th>
          <th class="th-sm">Start</th>
          <th class="th-sm">Finish</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="dt in this.dermatologistAppointments" :key="dt.id">
          <td>{{ dt.doctor.name }} {{ dt.doctor.surname }}</td>
          <td>{{ dt.start }}</td>
          <td>{{ dt.finish }}</td>
          <td>
            <button class="btn btn-primary" v-on:click="cancel(dt.id)">
              Cancel
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
const API_URL = config.API_URL;
export default {
  props: {
    dermatologistAppointments: Array,
  },
  methods: {
    cancel(appointmentId) {
      axios
        .get(
          `${API_URL}/appointments/cancel-my-reservation-to-dermatologists/${appointmentId}`
        )
        .then((response) => {
          this.dermatologistAppointments = response.data;
          this.$emit("clicked", this.dermatologistAppointments);
        });
    },
  },
};
</script>

<style>
</style>