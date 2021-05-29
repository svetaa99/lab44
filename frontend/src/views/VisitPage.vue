<template>
  <div>
      <VisitDetails/>
  </div>
</template>

<script>
import axios from 'axios';
import VisitDetails from '../components/VisitDetails.vue'
import { config } from "@/config.js";
const API_URL = config.API_URL;

export default {
    name: "VisitPage",
    components: { VisitDetails },
    data() {
        return {
            visit: this.$route.params.id,
        }
    },
    beforeRouteEnter (to, from, next) {
      var id = to.params.id;
      console.log(id);
      console.log(from);

      axios
      .get(`${API_URL}/appointments/valid-check/${id}`)
      .then(response => {
          if(!response.data){
              next('/');
          }
          else 
            next();
      });

      
    }
}
</script>

<style>

</style>