<template>
  <div class="container">
    <div class="container" align="center" style="width: 200px">
      <form class="form-inline">
        <button type="button" class="btn btn-primary" v-on:click="sortPrice()">
          Sort by price
        </button>
      </form>
    </div>
    <br />
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Dermatologist</th>
          <th class="th-sm">Pharmacy</th>
          <th class="th-sm">Price</th>
          <th class="th-sm">Date and Time</th>
          <th class="th-sm"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="dt in this.dermatologistTerms" :key="dt.id">
          <td>{{ dt.dermatologistName }} {{ dt.dermatologistSurname }}</td>
          <td>{{ dt.pharmacy.name }}</td>
          <td>{{ dt.pharmacy.pharmacistPrice }}</td>
          <td>{{ dt.date }}</td>
          <td>
            <button class="btn btn-primary" v-on:click="reserve(dt.id)">
              Choose
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
import Swal from "sweetalert2";

const API_URL = config.API_URL;

export default {
  props: {
    dermatologistTerms: Array,
  },
  data() {
    return {
      currentSortDir: "asc",
    };
  },
  methods: {
    reserve: function(termId) {
      axios
        .get(`${API_URL}/doctorterms/reserve-dermatologist/${termId}`)
        .then((response) => {
          this.dermatologistTerms = response.data;
          this.$emit("clicked", this.dermatologistTerms);
        });
      Swal.fire({
        title: "Successfully",
        text: "Appointment made successfully",
        icon: "success",
        button: null,
        timer: 2000,
      });
    },
    sortPrice: function () {
      this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      axios
        .get(`${API_URL}/doctorterms/sort/price/${this.currentSortDir}`)
        .then((response) => {
          this.dermatologistTerms = response.data;
          this.$emit("clicked", this.dermatologistTerms);
        });
    },
  },
};
</script>

<style>
</style>