<template>
  <div>
    <table>
      <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
      </tr>
      <tr v-for="d in doctors" :key="d.id">
        <td>{{d.name}}</td>
        <td>{{d.surname}}</td>
        <td>{{d.email}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    doctors: Array,
  }
}
</script>

<style>

</style>