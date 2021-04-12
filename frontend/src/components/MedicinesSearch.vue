<template>
  <div class="input-group">
    <div class="form-outline">
      <input
        type="search"
        id="form1"
        class="form-control"
        placeholder="Naziv leka"
        v-model="searchName"
      />
    </div>
    <button type="button" class="btn btn-primary" v-on:click="searchMedicine()">
      <i class="fas fa-search">Pretrazi</i>
    </button>
  </div>
</template>

<script>
export default {
  name: "MedicinesSearch",
  data() {
    return {
      searchName: '',
    };
  },
  methods: {
    searchMedicine: function () {
      this.$emit("clicked", this.searchName)
    },
  },
};
</script>

<style>
</style>