<template>
  <div>
      <DoctorReservation v-bind:freeTerms="this.freeTerms"/>
  </div>
</template>

<script>
import DoctorReservation from '../components/DoctorReservation.vue'

export default {
    name: "employee-reservation",
    components: { DoctorReservation },
    data() {
        return {
            freeTerms: []
        }
    },
    methods: {

    },
    mounted () {
        // axios
        //     .get(`${API_URL}/terms/{doctorToken})`)
        //     .then(response => {this.pharmacies = response.data; console.log(this.pharmacies)})
    }
}

</script>

<style>

</style>