<template>
  <div class="col col-sm-4">
    <h5>Monthly visits</h5>
    <ChartComponent v-if="loaded" :chartdata="monthlyData" :options="options" />
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
      monthlyData: null,
      options: null,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/appointments/get-monthly-visits`)
    const visitData = response.data.retObj;
    for (const entry of Object.entries(visitData)) {
      this.visitsArray.push(entry[1]);
    }
    console.log(this.visitsArray)
    this.monthlyData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
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