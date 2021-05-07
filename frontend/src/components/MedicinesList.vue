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
          
          <th v-if="extra == ''"></th>
          <th v-if="extra == 'quantity'">Quantity</th>

        </tr>
      </thead>
      <tbody>
        <tr v-for="m in this.medicines" :key="m.id" v-on:dblclick="handleRowDblClick(m)">
          <td>{{ m.name }}</td>
          <td>{{ m.type }}</td>
          <td>{{ m.specification }}</td>
          <td v-if="extra == 'add'">
            <router-link
              :to="`/medicines/${m.id}`"
            >
            Odaberi
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "MedicinesList",
  props: {
    medicines: Array,
    extra: String,
  },
  methods: {
    handleRowDblClick(medicine) {
      this.$emit('clicked', medicine)
    },

    handleInputOnChange() {
      this.$emit('change', this.quantity)
    }
  }
};
</script>

<style>

</style>