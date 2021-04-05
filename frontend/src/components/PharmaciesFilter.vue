<template>
  <div class="input-group mb-3">
    <div class="form-outline">
      <select id="inputFilter" class="form-control" v-model="filterType">
        <option selected>Choose...</option>
        <option value="10">10</option>
        <option value="9">9</option>
        <option value="8">8</option>
        <option value="7">7</option>
        <option value="6">6</option>
      </select>
      <button
        type="button"
        class="btn btn-primary input-group-append"
        v-on:click="filterPharmacy()"
      >
        <i class="fas fa-filter">Filtriraj</i>
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  name: "PharmaciesFilter",
  data() {
    return {
      filterType: "",
    };
  },
  methods: {
    filterPharmacy: function () {
      if (this.filterType == "Choose...") {
        axios.get(`${API_URL}/pharmacies/all`).then((response) => {
          this.pharmacies = response.data;
          this.$emit("clicked", this.pharmacies);
        });
      } else {
        axios
          .get(`${API_URL}/pharmacies/filter/${this.filterType}`)
          .then((response) => {
            this.pharmacies = response.data;
            this.$emit("clicked", this.pharmacies);
          });
      }
    },
  },
};
</script>

<style>
</style>