<template>
  <div class="col col-sm-4">
    <h5>Monthly medicine consumption</h5>
    <ChartComponent v-if="loaded" :chartdata="monthlyData" :options="options" />
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
      monthlyData: null,
      options: null,
    }
  },
  async mounted() {
    const response = await axios.get(`${API_URL}/medicines/get-monthly-medicine-consumption`)
    const consData = response.data.retObj;
    for (const entry of Object.entries(consData)) {
      this.consArray.push(entry[1]);
    }
    
    this.monthlyData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
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