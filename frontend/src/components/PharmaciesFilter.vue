<template>
  <div class="input-group">
    <div class="form-outline">
      <select id="inputFilter" class="custom-select" placeholder="Izaberi" v-model="filterType">
        <option value="" disabled selected>Choose rating...</option>
        <option value="all">All</option>
        <option value="10">10</option>
        <option value="9">9</option>
        <option value="8">8</option>
        <option value="7">7</option>
        <option value="6">6</option>
      </select>
    </div>
      <div class="input-group-append">
        <button
          type="button"
          class="btn btn-primary input-group-append"
          v-on:click="filterPharmacy()"
        >
          <i class="fas fa-filter">Filter</i>
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
      console.log(this.filterType)
      if (this.filterType == "all") {
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