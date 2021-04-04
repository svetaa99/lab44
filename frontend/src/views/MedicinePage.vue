<template>
  <div>
    <hr />
    <br />
    <h1>{{ medicine.name }}</h1>
    <h6>{{ medicine.type }}</h6>
    <h6>{{ medicine.specification }}</h6>
    <br />
    <hr />
    <div class="container">
      <div class="row">
        <div class="col-sm">
          <select class="form-select" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
        </div>
        <div class="col-sm">
          <Datepicker />
        </div>
        <div class="col-sm">
          <input
            type="number"
            class="form-control"
            id="quantity"
            name="quantity"
            min="1"
          />
        </div>
        <div class="col-sm">
          <button type="button" class="btn btn-primary">Rezervisi</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Datepicker from "vuejs-datepicker";

const API_URL = config.API_URL;

export default {
  name: "MedicinePage",
  components: {
    Datepicker,
  },
  data() {
    return {
      options: [],
      medicine: {},
    };
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 1];
    axios.get(`${API_URL}/medicines/${id}`).then((response) => {
      this.medicine = response.data;
    });
  },
};
</script>

<style>
</style>