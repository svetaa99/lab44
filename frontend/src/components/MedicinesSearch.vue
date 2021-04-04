<template>
  <div class="input-group">
    <div class="form-outline">
      <input
        type="search"
        id="form1"
        class="form-control"
        v-model="searchName"
      />
    </div>
    <button type="button" class="btn btn-primary" v-on:click="searchMedicine()">
      <i class="fas fa-search">Pretrazi</i>
    </button>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  name: "MedicinesSearch",
  data() {
    return {
      searchName: "",
    };
  },
  methods: {
    searchMedicine: function () {
      if (this.searchName == "") {
        axios.get(`${API_URL}/medicines/all`).then((response) => {
          this.pharmacies = response.data;
          this.$emit("clicked", this.pharmacies);
        });
      } else {
        axios
          .get(`${API_URL}/medicines/search/${this.searchName}`)
          .then((response) => {
            this.medicines = response.data;
            this.$emit("clicked", this.medicines);
          });
      }
    },
  },
};
</script>

<style>
</style>