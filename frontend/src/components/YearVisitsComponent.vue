<template>
  <div class="col col-sm-4">
    <h5>Yearly visits</h5>
    <ChartComponent v-if="loaded" :chartdata="yearData" :options="options" />
  </div>
</template>

<script>
import ChartComponent from "./ChartComponent.vue";
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  components: { ChartComponent }, 
  data() {
    return {
      loaded: false,
      yearSpan: 5,
      labelArray: [],
      visitsArray: [],
      yearData: null,
      options: null,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/appointments/get-year-visits/${this.yearSpan}`)
    const visitData = response.data.retObj;
    for (const entry of Object.entries(visitData)) {
      this.labelArray.push(entry[0]);
      this.visitsArray.push(entry[1]);
    }

    this.yearData = {
        labels: [...this.labelArray],
        datasets: [
          {
            label: 'Visits',
            backgroundColor: '#f87979',
            data: [...this.visitsArray]
          }
        ]
    }

    this.options = {
      responsive: true, 
      maintainAspectRatio: false
    }

    this.loaded = true;
    
  },

}
</script>

<style>

</style>