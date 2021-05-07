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
export default {
  props: {
    reservedDrugs: Array,
  },
  methods: {
    cancel(reservationId) {
      axios
        .get(`${API_URL}/reservations/cancel-reservation/${reservationId}`)
        .then((response) => {
          this.reservedDrugs = response.data;
          this.$emit("clicked", this.reservedDrugs);
        });
    },
  },
};
</script>

<style>
</style>