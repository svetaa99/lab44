<template>
  <div class="col col-sm-4">
    <h5>Quarter visits</h5>
    <ChartComponent v-if="loaded" :chartdata="quarterData" :options="options" />
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
      visitsArray: [],
      quarterData: null,
      options: null,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/appointments/get-quarter-visits`)
    const visitData = response.data.retObj;
    for (const entry of Object.entries(visitData)) {
      this.visitsArray.push(entry[1]);
    }

    this.quarterData = {
        labels: ['1st', '2nd', '3rd'],
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