<template>
  <div class="container">
    <div class="row justify-content-md-center">
      Start date:<br/>
      <Datepicker placeholder="Start date" v-model="startDate"/><br/>
      End date:<br/>
      <Datepicker placeholder="End date" v-model="endDate"/><br/>
    </div>
    <div class="row">
      <button type='button' class="btn btn-primary" @click="handleLoadClick">Load data</button><br/>
    </div>
    <div class="row">
      <LineChartComponent :chartData="profitsData" :options="options"/>
    </div>
  </div>
</template>

<script>
import LineChartComponent from './LineChartComponent.vue'
import Datepicker from "vuejs-datepicker";
import axios from 'axios'
import { config } from '@/config.js'
import Swal from 'sweetalert2';

const API_URL = config.API_URL

export default {
  components: {
    LineChartComponent,
    Datepicker
  },
  data() {
    return {
      startDate: '',
      endDate: '',
      loaded: false,
      profitsArr: [],
      labelsArr: [],
      profitsData: null,
      options: null,
    }
  },
  mounted() {
    this.options = {
      responsive: true, 
      maintainAspectRatio: false
    }
  },
  methods: {
    async handleLoadClick() {
      if (this.startDate === '' || this.endDate === '' || new Date(this.startDate) > new Date(this.endDate)) {
        Swal.fire({
          title: 'Error',
          text: 'Invalid dates',
          icon: 'error'
        })
      } else {
        this.profitsData = null;
        const response = await axios.get(`${API_URL}/pharmacies/get-profits/${new Date(this.startDate).getTime()}/${new Date(this.endDate).getTime()}`);
        const profits = response.data.retObj;
        const newLabels = [];
        this.profitsArr = [];
        for(const entry of Object.entries(profits)) {
          newLabels.push(new Date(parseInt(entry[0])).toDateString())
          this.profitsArr.push(entry[1])
        }
        
        this.profitsData = {
          labels: [...newLabels],
          datasets: [
            { 
              label: 'Daily profit',
              backgroundColor: '#f87979',
              data: [...this.profitsArr]
            }
          ]
        }

      }
    }
  }
  
}
</script>

<style>

</style>