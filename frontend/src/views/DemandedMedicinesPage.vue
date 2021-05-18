<template>
  <div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col" v-for="d in demands" :key="d.id">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{{d.medicine.name}}</h5>
            <p class="card-text">Quantity: {{d.quantity}}</p>
          </div>
        </div>
      </div>
    </div>
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