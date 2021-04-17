<template>
  <div>
      <VisitDetails/>
  </div>
</template>

<script>
import axios from 'axios';
import VisitDetails from '../components/VisitDetails.vue'
export default {
    name: "employee-visit",
    components: { VisitDetails },
    data() {
        return {
            visit: null
        }
    },  
}
</script>

<style>

</style>