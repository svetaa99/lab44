<template>
  <div>
    <h1>List of medicines</h1>
    
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Id</th>
          <th class="th-sm">Name</th>
          <th class="th-sm">Type</th>
          <th class="th-sm">Specification</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="m in this.medicines" :key="m.id">
          <td>{{ m.id }}</td>
          <td>{{ m.name }}</td>
          <td>{{ m.type }}</td>
          <td>{{ m.specification }}</td>
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
  },
};
</script>

<style>
</style>