<template>
  <div>
    <OrdersList :orders="orders"/>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";

import OrdersList from "@/components/OrdersList.vue"

const API_URL = config.API_URL;

export default {
  name: 'AllOrdersPage',
  components: {
    OrdersList,
  },
  data() {
    return {
      orders: [],
      admin: {},
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data;
        console.log(this.admin)
        axios
          .get(`${API_URL}/orders/get-all-orders/${this.admin.pharmacy.id}`)
          .then(response => {this.orders = response.data; console.log(this.orders)})
      })
  }
}
</script>

<style>

</style>