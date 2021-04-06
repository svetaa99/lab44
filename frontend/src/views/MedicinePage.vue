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
          <!-- <v-select :options="options"></v-select> -->
          <!-- <select class="form-select" aria-label="Default select example">
            <option selected v-for="option in options" v-bind:key="option.id">Odaberi apoteku</option>
            <option v-for="option in options" v-bind:key="option.id">{{option.name}}</option>
          </select> -->
          <label for="pharmacy">Apoteka: </label>
          <select v-model="reservation.pharmacy">
            <option v-for="option in options" :key="option">{{option}}</option>
          </select>
        </div>
        <div class="col-sm">
          <Datepicker placeholder="Datum" v-model="reservation.date"/>
        </div>
        <div class="col-sm">
          <input
            placeholder="Kolicina"
            type="number"
            class="form-control"
            id="quantity"
            name="quantity"
            min="1"
            v-model="reservation.quantity"
          />
        </div>
        <div class="col-sm">
          <button type="button" class="btn btn-primary" @click="reserve()">Rezervisi</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Datepicker from "vuejs-datepicker";
// import vSelect from "vue-select";

const API_URL = config.API_URL;

export default {
  name: "MedicinePage",
  components: {
    Datepicker,
    // "v-select": vSelect,
  },
  data() {
    return {
      options: [
        "apoteka1",
        "apoteka2",
        "apoteka3",
      ],
      medicine: {},
      reservation: {
        medicine: this.medicine,
        pharmacy: "",
        date: "",
        quantity: ""
      }
    };
  },
  methods: {
    reserve() {
      console.log(this.reservation)
    }
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