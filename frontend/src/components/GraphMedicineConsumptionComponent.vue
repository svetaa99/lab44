<template>
  <div class="row">
    <MonthlyMedicineConsumption />
    <QuarterMedicineConsumption />
    <YearMedicineConsumption />
  </div>
</template>

<script>
import MonthlyMedicineConsumption from './MonthlyMedicineConsumption.vue'
import QuarterMedicineConsumption from './QuarterMedicineConsumption.vue'
import YearMedicineConsumption from './YearMedicineConsumption.vue'

export default {
  components: {
    MonthlyMedicineConsumption,
    QuarterMedicineConsumption,
    YearMedicineConsumption
  }
}
</script>

<style>

</style>