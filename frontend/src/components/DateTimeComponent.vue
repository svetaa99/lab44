<template>
  <div class="container" align="center" style="width: 750px">
    <form class="form-inline">
      <Datepicker placeholder="Datum" v-model="date" />
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
  data() {
    return {
      date: "",
      time: "",
      currentSortDir: "asc",
    };
  },
  methods: {
    // searchPharmacy: function () {
    //   console.log(this.date, this.time);
    //   axios.post(`${API_URL}/pharmacies/freeTerms`, {
    //     date: this.date,
    //     time: this.time,
    //   }).then((response) => {
    //     this.pharmacies = response.data;
    //   })
    // },
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