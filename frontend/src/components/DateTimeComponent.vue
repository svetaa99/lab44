<template>
  <div class="container" align="center" style="width: 750px">
    <form class="form-inline">
      <b-form-datepicker id="datepicker-lg" size="lg" locale="en" v-model="date"></b-form-datepicker>
      <input class="ml-1" type="time" name="from" v-model="time" />
      <button
        type="button"
        class="btn btn-primary"
        v-on:click="searchPharmacy()"
      >
        Search
      </button>
      <button type="button" class="btn btn-primary" v-on:click="sortPrice()">
        Sort by price
      </button>
      <button type="button" class="btn btn-primary" v-on:click="sortRating()">
        Sort by rating
      </button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Datepicker from "vuejs-datepicker";

const API_URL = config.API_URL;

export default {
  components: {
    Datepicker,
  },
  props: {
    pharmacies: Array,
  },
  data() {
    return {
      date: "",
      time: "",
      currentSortDir: "asc",
    };
  },
  methods: {
    searchPharmacy: function () {
      
      axios.get(`${API_URL}/pharmacies/freeTerms/${this.time}`).then((response) => {
        const retObj = {
          date: this.date,
          time: this.time,
          pharmacies: response.data
        }
        this.$emit("clicked", retObj);
      })
    },
    sortPrice: function () {
      this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      axios
        .get(`${API_URL}/pharmacies/sort/price/${this.currentSortDir}`)
        .then((response) => {
          this.pharmacies = response.data;
          this.$emit("clicked", this.pharmacies);
        });
    },
    sortRating: function () {
      this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      axios
        .get(`${API_URL}/pharmacies/sort/rating/${this.currentSortDir}`)
        .then((response) => {
          this.pharmacies = response.data;
          this.$emit("clicked", this.pharmacies);
        });
    },
  },
};
</script>

<style>
</style>