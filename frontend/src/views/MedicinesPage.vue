<template>
  <div>
    <MedicinesSearch v-model="medicines" @clicked="onSearchClick"/>
    <MedicinesList v-bind:medicines="this.medicines" />
  </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
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
    };
  },
  mounted() {
    axios.get(`${API_URL}/medicines/all`).then((response) => {
      this.medicines = response.data;
    });
  },
  methods: {
    onSearchClick (medicines) {
      this.medicines = medicines;
    }
  }
};
</script>

<style>
</style>