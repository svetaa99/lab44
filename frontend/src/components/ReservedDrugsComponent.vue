<template>
  <div class="container">
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Type</th>
          <th class="th-sm">Specification</th>
          <th class="th-sm">Pharmacy</th>
          <th class="th-sm">Quantity</th>
          <th class="th-sm">Price</th>
          <th class="th-sm">Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="rd in this.reservedDrugs" :key="rd.id">
          <td>{{ rd.medicine.name }}</td>
          <td>{{ rd.medicine.type }}</td>
          <td>{{ rd.medicine.specification }}</td>
          <td>{{ rd.pharmacy.name }}</td>
          <td>{{ rd.quantity }}</td>
          <td>{{ rd.totalPrice }}</td>
          <td>{{ rd.date | date }}</td>
          <td>
            <button class="btn btn-primary" v-on:click="cancel(rd.id)">
              Cancel
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
const API_URL = config.API_URL;
export default {
  props: {
    reservedDrugs: Array,
  },
  methods: {
    cancel(reservationId) {
      this.$emit("clicked", reservationId);
    },
  },
};
</script>

<style>
</style>