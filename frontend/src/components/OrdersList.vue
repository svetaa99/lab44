<template>
  <div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col" v-for="o in orders" :key="o.id">
        <OrderCard :order="o"/>
      </div>
    </div>
  </div>
</template>

<script>
import OrderCard from '@/components/OrderCard.vue'

export default {
  components: {
    OrderCard,
  },
  props: {
    orders: Array,
  }
}
</script>

<style>

</style>