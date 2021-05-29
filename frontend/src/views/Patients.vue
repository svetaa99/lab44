<template>
  <div>
      <PatientsList v-bind:patients="this.patients"/>
  </div>
</template>

<script>
import axios from 'axios'
import PatientsList from '../components/PatientsList.vue'
export default {
    name: "employee-patients",
    components: { PatientsList },
    data() {
        return {
            patients: []
        }
    },
    mounted () {
        axios
            .get('http://localhost:8000/patients/all')
            .then(response => {this.pharmacies = response.data; console.log(this.pharmacies)})
    },
    beforeRouteEnter (to, from, next) {
      const tokenItem = JSON.parse(localStorage.getItem('jwt'));
      var flag = false;
      if(tokenItem == null)
        next('/');
      tokenItem.token.roles.forEach(role => {
          if(role.id == 2 || role.id == 3)
            flag = true;
      })
      if(!flag)
        next('/');
      else
        next();
    }
}

</script>

<style>

</style>