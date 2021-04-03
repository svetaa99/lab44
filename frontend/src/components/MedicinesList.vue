<template>
  <div>
    <ul v-for="m in this.medicines" :key="m.id">
      <li>{{m.name}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'MedicinesList',
  props: {
    medicines: Array
  },
}
</script>

<style>

</style>