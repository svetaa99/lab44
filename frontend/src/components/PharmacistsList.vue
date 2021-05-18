<template>
  <div class="container">
    <button type="button" class="btn btn-primary" v-on:click="sortRating()">
      Sort by rating
    </button>
    <br />
    <br>
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Surname</th>
          <th class="th-sm">Rating</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pharmacist in pharmacists" :key="pharmacist.id">
          <td>{{ pharmacist.name }}</td>
          <td>{{ pharmacist.surname }}</td>
          <td>{{ pharmacist.rating }}</td>
          <td>
            <button class="btn btn-primary" v-on:click="reserve(pharmacist.id)">Choose</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Swal from 'sweetalert2'

const API_URL = config.API_URL;
export default {
  name: "pharmacists-list",
  props: {
    pharmacists: Array,
    id: String,
    date: String,
    time: String,
  },
  data() {
    return {
      currentSortDir: "asc",
    };
  },
  methods: {
    sortRating() {
      this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      axios
        .get(`${API_URL}/pharmacist/sort/rating/${this.currentSortDir}/${this.id}`)
        .then((response) => {
          this.pharmacists = response.data;
          this.$emit("clicked", this.pharmacists);
        });
    },
    reserve(id) {
      var newReservation = {doctorId: id, pharmacy: this.$route.params.id, start: this.date + "T" + this.time}
      
      axios
      .post(`${API_URL}/appointments/make-appointment-patient`, newReservation)
      .then(
        Swal.fire({
        title: 'Successfully',
        text: 'Appointment made successfully',
        icon: 'success',
        button: null,
        timer: 2000
      })
      )
      
    }
  }
};
</script>

<style>
</style>