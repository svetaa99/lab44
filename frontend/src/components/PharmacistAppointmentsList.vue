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
          <th class="th-sm">Pharmacist</th>
          <th class="th-sm">Start</th>
          <th class="th-sm">Finish</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="ph in this.pharmacistAppointments" :key="ph.id">
          <td>{{ ph.doctor.name }} {{ ph.doctor.surname }}</td>
          <td>{{ ph.start | date }}</td>
          <td>{{ ph.finish | date }}</td>
          <td>
            <button class="btn btn-primary" v-on:click="cancel(ph.id)">
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
    pharmacistAppointments: Array,
  },
  methods: {
    cancel(appointmentId) {
      axios
        .get(
          `${API_URL}/appointments/cancel-my-reservation/${appointmentId}`
        )
        .then((response) => {
          this.pharmacistAppointments = response.data;
          this.$emit("clicked", this.pharmacistAppointments);
          Swal.fire({
            title: "Appointment canceled successfully.",
            icon: "success",
            showConfirmButton: false,
            timer: 1500,
          })
        }).catch((error) => {
          Swal.fire({
            title: "Error",
            text: "You can't cancel appointment 24 hours before choosen date.",
            icon: "error",
          });
        });
    },
  },
};
</script>

<style>
</style>