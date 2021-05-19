<template>
  <div class="input-group">
    <div class="form-outline">
      <input
        type="search"
        id="form1"
        class="form-control"
        placeholder="Name"
        v-model="searchName"
      />
    </div>
    <button type="button" class="btn btn-primary text-white" v-on:click="searchPharmacy()">
      <i class="fas fa-search">Search</i>
    </button>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  name: "PharmaciesSearch",
  data() {
    return {
      searchName: "",
    };
  },
  methods: {
    searchPharmacy: function () {
      if (this.searchName == "") {
        axios.get(`${API_URL}/pharmacies/all`).then((response) => {
          this.pharmacies = response.data;
          this.$emit("clicked", this.pharmacies);
        });
      } else {
        axios
          .get(`${API_URL}/pharmacies/search/${this.searchName}`)
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
.text-white {
  color: white;
}
</style>