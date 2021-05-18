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
          <th class="th-sm">Pharmacist</th>
          <th class="th-sm">Start</th>
          <th class="th-sm">Finish</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pv in this.pharmacistVisits" :key="pv.id">
          <td>{{ pv.doctor.name }} {{ pv.doctor.surname }}</td>
          <td>{{ pv.start }}</td>
          <td>{{ pv.finish }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    pharmacistVisits: Array,
  },
  methods: {
    
  },
};
</script>

<style>
</style>