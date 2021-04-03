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
    }
}

</script>

<style>

</style>