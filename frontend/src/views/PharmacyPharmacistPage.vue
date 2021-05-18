<template>
  <div>
    <div class="container"></div>
    <pharmacists-list :id="id" :date="this.date" :time="this.time" :pharmacists="this.pharmacists"></pharmacists-list>
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
      id: "",
      time: "",
      date: "",
    };
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 4];
    this.time = arr[arr.length - 1];
    this.date = arr[arr.length - 2];
    this.id = id;
    axios.get(`${API_URL}/pharmacist/${id}`).then((response) => {
      this.pharmacists = response.data;
      console.log(this.pharmacists)
    });
  },
};
</script>

<style>
</style>