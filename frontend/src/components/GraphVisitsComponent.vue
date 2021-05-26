<template>
  <div class="row">
    <MonthlyVisitsComponent />
    <QuarterVisitsComponent />
    <YearVisitsComponent />
  </div>
</template>

<script>
import MonthlyVisitsComponent from './MontlyVisitsComponent.vue'
import QuarterVisitsComponent from './QuarterVisitsComponent.vue'
import YearVisitsComponent from './YearVisitsComponent.vue'
export default {
  components: {
    MonthlyVisitsComponent,
    QuarterVisitsComponent,
    YearVisitsComponent,
  }
}
</script>

<style>

</style>
MontlyVisitsComponent