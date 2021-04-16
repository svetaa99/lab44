<template>
  <div>
      <DoctorScheduledVisits v-bind:scheduledVisits="this.scheduledVisits"/>
  </div>
</template>

<script>
import axios from 'axios'
import DoctorScheduledVisits from '../components/DoctorScheduledVisits.vue'
export default {
    name: "employee-visits",
    components: {DoctorScheduledVisits},
    data(){
        return {
            scheduledVisits: []
        }
    }

}
</script>

<style>

</style>