<template>
  <div>
    <h1>List of medicines</h1>
    <br />
    <div class="container" align="center" style="width: 350px">
      <div class="row" align="center">
        <MedicinesSearch v-model="searchName" @clicked="onSearchClick" />
      </div>
    </div>
    <br />
    <MedicinesList :medicines="this.medicines" :extra="extra" />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import MedicinesList from "@/components/MedicinesList";
import MedicinesSearch from "@/components/MedicinesSearch";

const API_URL = config.API_URL;

export default {
  name: "MedicinesPage",
  components: {
    MedicinesList,
    MedicinesSearch,
  },
  data() {
    return {
      medicines: [],
      searchName: "",
      extra: "add"
    };
  },
  mounted() {
    axios.get(`${API_URL}/medicines/all`).then((response) => {
      this.medicines = response.data;
    });
  },
  methods: {
    onSearchClick(searchName) {
      console.log(searchName);
      if (searchName == "") {
        axios.get(`${API_URL}/medicines/all`).then((response) => {
          this.medicines = response.data;
        });
      } else {
        axios
          .get(`${API_URL}/medicines/search/${searchName}`)
          .then((response) => {
            this.medicines = response.data;
          });
      }
    },
  },
};
</script>

<style>
</style>