<template>
  <div class="container">
    <h1>Vacation requests</h1>
    <VacationsList :vacations="vacations" />
  </div>
</template>

<script>
import VacationsList from '../components/VacationsList.vue';
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  components: {
    VacationsList,
  },
  data() {
    return {
      vacations: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/vacation/all`)
      .then(response => {
        this.vacations = response.data
        console.log(this.vacations)
      })
  }
}
</script>

<style>

</style>