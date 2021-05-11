<template>
  <div>
    <input
      class="ml-1"
      type="text"
      v-model="search.name"
      placeholder="Name"
    >

    <input
      class="ml-1"
      type="text"
      v-model="search.surname"
      placeholder="Surname"
    >

    <button type="button" class="btn btn-primary" @click="handleSearchClick">Search</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: {
        name: "",
        surname: "",
      }
    }
  },
  methods: {
    handleSearchClick() {
      this.$emit('clicked', this.search);
    }
  }
}
</script>

<style>

</style>