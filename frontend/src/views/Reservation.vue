<template>
  <div>
      <DoctorReservation v-bind:freeTerms="this.freeTerms"/>
  </div>
</template>

<script>
import DoctorReservation from '../components/DoctorReservation.vue'
import axios from 'axios';
import { config } from "@/config.js";
const API_URL = config.API_URL;
export default {
    name: "employee-reservation",
    components: { DoctorReservation },
    data() {
        return {
            freeTerms: []
        }
    },
    beforeRouteEnter (to, from, next) {
      var id = to.params.id;
      console.log(id);
      console.log(from);

      axios
      .get(`${API_URL}/appointments/valid-check/${id}`)
      .then(response => {
          console.log(response.data);
          if(!response.data){
              next('/');
          }
          else 
            next();
      })
    }
}

</script>

<style>

</style>