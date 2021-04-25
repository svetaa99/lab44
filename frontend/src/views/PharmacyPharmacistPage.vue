<template>
  <div>
    <div class="container"></div>
    <pharmacists-list :pharmacists="this.pharmacists"></pharmacists-list>
    <br />
    <!-- lista farmaceuta -->
  </div>
</template>

<script>
import axios from 'axios';

import { config } from "@/config.js";
import PharmacistsList from "@/components/PharmacistsList.vue";
const API_URL = config.API_URL;
export default {
  components: {
    "pharmacists-list": PharmacistsList,
  },
  data() {
    return {
      pharmacists: [],
    };
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 2];
    axios.get(`${API_URL}/pharmacist/${id}`).then((response) => {
      this.pharmacists = response.data;
      console.log(this.pharmacists)
    });
  },
};
</script>

<style>
</style>