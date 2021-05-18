<template>
  <div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col" v-for="v in vacations" :key="v.id">
        <VacationCard :vacation="v" />
      </div>
    </div>
  </div>
</template>

<script>
import VacationCard from './VacationCard.vue'
export default {
  components: { VacationCard },
  props: {
    vacations: Array,
  }
}
</script>

<style>

</style>