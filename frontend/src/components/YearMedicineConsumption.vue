<template>
  <div class="col col-sm-4">
    <h5>Yearly medicine consumption</h5>
    <ChartComponent v-if="loaded" :chartdata="yearlyData" :options="options" />
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
      labelArray: [],
      consArray: [],
      yearlyData: null,
      options: null,
      yearSpan: 5,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/medicines/get-year-medicine-consumption/${this.yearSpan}`)
    const consData = response.data.retObj;
    for (const entry of Object.entries(consData)) {
      this.labelArray.push(entry[0])
      this.consArray.push(entry[1]);
    }

    this.yearlyData = {
        labels: [...this.labelArray],
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