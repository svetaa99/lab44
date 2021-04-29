<template>
  <div>
    <h1>Offers page</h1>
    <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="100%"
      >
        <thead>
          <tr>
            <th class="th-sm">OrderId</th>
            <th class="th-sm">SupplierId</th>
            <th class="th-sm">Price</th>
            <th class="th-sm">Deadline</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="o in this.offers" :key="o.id">
            <td>{{ o.orderId }}</td>
            <td>{{ o.supplierId }}</td>
            <td>{{ o.price }}</td>
            <td>{{ new Date(o.deadline).toDateString() }}</td>
            <td><button class="btn btn-primary" v-on:click="handleAcceptClick(o)">Accept</button></td>
          </tr>
        </tbody>
      </table>
    </div>
</template>

<script>
import axios from 'axios'
import { config } from '@/config.js'
import Swal from 'sweetalert2'

const API_URL = config.API_URL;

export default {
  name: 'SupplierOffersPage', 
  data() {
    return {
      offers: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/orders/list-offers/1`)
      .then(response => {
        this.offers = response.data;
      })
  },
  methods: {
    handleAcceptClick(offer) {
      axios
        .post(`${API_URL}/orders/accept-offer`, { orderId: offer.orderId, supplierId: offer.supplierId })
        .then(response => {
          if (response.status === 200) {
            Swal.fire({
              title: 'Success',
              text: 'Accepted offer! Other offers are automatically declined.',
              icon: 'success',
            })
          }
        })
    }
  }
}
</script>

<style>

</style>