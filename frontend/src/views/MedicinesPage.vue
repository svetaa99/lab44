<template>
  <div>
    <MedicinesSearch v-model="searchName" @clicked="onSearchClick"/>
    <MedicinesList :medicines="this.medicines" />
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
      searchName: '',
    };
  },
  mounted() {
    axios.get(`${API_URL}/medicines/all`).then((response) => {
      this.medicines = response.data;
    });
  },
  methods: {
    onSearchClick (searchName) {
      console.log(searchName)
      if (searchName == '') {
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
    }
  }
};
</script>

<style>
</style>