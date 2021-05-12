<template>
  <div class="padding-top padding-bottom">
    <h3>Search {{doctorType}}:</h3>
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
  props: {
    doctorType: String,
  },
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
  .padding-top {
    padding-top: 10px;
  }
</style>