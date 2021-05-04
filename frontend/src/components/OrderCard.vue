<template>
  <div class="card" style="width: 18rem;">
    <div class="card-body">
      <h5 class="card-title">Order code: {{order.id}}</h5>
      <p class="card-text">List of medicines: {{"" + order.orderMedicines.map(om => {return om.medicine.name})}}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    order: Object,
  }
}
</script>

<style>

</style>