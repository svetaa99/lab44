<template>
  <div>
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Surname</th>
          <th class="th-sm">Rating</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pharmacist in pharmacists" :key="pharmacist.id">
          <td>{{ pharmacist.name }}</td>
          <td>{{ pharmacist.surname }}</td>
          <td>{{ pharmacist.rating }}</td>
          <td>
            <router-link> Choose </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pharmacists: [],
      pharmacy: {},
    };
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 1];

    console.log(id);

    axios.get(`${API_URL}/pharmacists/${id}`).then((response) => {
      this.pharmacists = response.data;
    });
  },
};
</script>

<style>
</style>