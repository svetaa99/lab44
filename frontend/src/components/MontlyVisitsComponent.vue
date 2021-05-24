<template>
  <div>
    <h3>Montly visits</h3>
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
      visits: {
        1: -1,
        2: -1,
        3: -1,
        4: -1,
        5: -1,
        6: -1,
        7: -1,
        8: -1,
        9: -1,
        10: -1,
        11: -1,
        12: -1,
      },
      visitsArray: [],
      monthlyData: null,
      options: null,
    }
  },
  async mounted() {
    for (let month = 1; month <= 12; month++) {
      const response = await axios.get(`${API_URL}/appointments/get-monthly-visits/${month}`)
      this.visitsArray.push(response.data.retObj)
    }

    this.monthlyData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'October', 'November', 'December'],
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
    console.log(this.visitsArray)
    console.log(this.visits)
  },

}
</script>

<style>

</style>