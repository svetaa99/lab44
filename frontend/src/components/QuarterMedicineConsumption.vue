<template>
  <div class="col col-sm-4">
    <h5>Quarter medicine consumption</h5>
    <ChartComponent v-if="loaded" :chartdata="quarterData" :options="options" />
  </div>
</template>

<script>
import ChartComponent from './ChartComponent.vue'
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  components: { ChartComponent },
  data() {
    return {
      loaded: false,
      consArray: [],
      quarterData: null,
      options: null,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/medicines/get-quarter-medicine-consumption`)
    const consData = response.data.retObj;
    for (const entry of Object.entries(consData)) {
      this.consArray.push(entry[1]);
    }

    this.quarterData = {
        labels: ['1st', '2nd', '3rd'],
        datasets: [
          {
            label: 'Consumed medicines',
            backgroundColor: '#f87979',
            data: [...this.consArray]
          }
        ]
    }

    this.options = {
      responsive: true, 
      maintainAspectRatio: false
    }

    this.loaded = true;
  }
}
</script>

<style>

</style>