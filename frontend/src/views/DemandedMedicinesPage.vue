<template>
  <div>
    <ol>
      <li v-for="d in demands" :key="d.id">{{d.quantity}}</li>
    </ol>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  data() {
    return {
      demands: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/demandmedicines/all`)
      .then(response => {
        this.demands = response.data
      })
  }
}
</script>

<style>

</style>