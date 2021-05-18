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
          <th class="th-sm">Dermatologist</th>
          <th class="th-sm">Start</th>
          <th class="th-sm">Finish</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="dt in this.dermatologistVisits" :key="dt.id">
          <td>{{ dt.doctor.name }} {{ dt.doctor.surname }}</td>
          <td>{{ dt.start }}</td>
          <td>{{ dt.finish }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    dermatologistVisits: Array,
  },
  methods: {
    
  },
};
</script>

<style>
</style>