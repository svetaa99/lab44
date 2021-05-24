<template>
  <div>
    <MonthlyVisitsComponent />
    <QuarterVisitsComponent />
  </div>
</template>

<script>
import MonthlyVisitsComponent from './MontlyVisitsComponent.vue'
import QuarterVisitsComponent from './QuarterVisitsComponent.vue'
export default {
  components: {
    MonthlyVisitsComponent,
    QuarterVisitsComponent,
  }
}
</script>

<style>

</style>
MontlyVisitsComponent