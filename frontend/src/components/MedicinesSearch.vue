<template>
  <div class="container" align="center" style="width: 350px">
    <div class="input-group">
      <div class="form-outline">
        <input
          type="search"
          id="form1"
          class="form-control"
          placeholder="Name"
          v-model="searchName"
        />
      </div>
      <button
        type="button"
        class="btn btn-primary"
        v-on:click="searchMedicine()"
      >
        <i class="fas fa-search">Search</i>
      </button>
    </div>
    <br>
  </div>
</template>

<script>
export default {
  name: "MedicinesSearch",
  data() {
    return {
      searchName: "",
    };
  },
  methods: {
    searchMedicine: function () {
      this.$emit("clicked", this.searchName);
    },
  },
};
</script>

<style>
</style>