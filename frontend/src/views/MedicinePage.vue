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
          <select v-model="selectedPM">
            <option v-for="option in options" :key="option.id" :value="option">{{option.pharmacy.name}}</option>
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
            :max="this.selectedPM.quantity"
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
import Swal from 'sweetalert2'

const API_URL = config.API_URL;

export default {
  name: "MedicinePage",
  components: {
    Datepicker,
    // "v-select": vSelect,
  },
  data() {
    return {
      options: [],
      selectedPM: {}, 
      medicine: {},
      reservation: {
        pharmacy: {},
        patient: {},
        medicine: this.medicine,
        date: "",
        quantity: "",
        totalPrice: "",
      }
    };
  },
  methods: {
    reserve() {
      this.reservation.pharmacy = this.selectedPM.pharmacy
      this.reservation.medicine = this.selectedPM.medicine
      this.reservation.totalPrice = this.selectedPM.price * this.reservation.quantity
      this.reservation.date = this.reservation.date.getTime()

      console.log(this.reservation)
      axios
        .post(`${API_URL}/reservations/`, this.reservation)
        .then(response => {
          if (response.status === 200) {
            Swal.fire({
              title: 'Success',
              text: 'Posted reservation!',
              icon: 'success',
            })
          }
        })
    }
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 1];
    axios
      .get(`${API_URL}/patients/registered-patient`)
      .then(response => {
        this.reservation.patient = response.data;
        console.log(this.reservation.patient)
      })
      
    axios.get(`${API_URL}/medicines/${id}`).then((response) => {
      this.medicine = response.data;
      axios
        .get(`${API_URL}/pharmacy-medicines/get-pharmacies/${this.medicine.id}`)
        .then((response) => {
          this.options = response.data;
          console.log(this.options)
        })
    });
  },
};
</script>

<style>
</style>