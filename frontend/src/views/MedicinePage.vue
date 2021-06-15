<template>
  <div>
    <div class="container">
      <main>
        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">Your cart</span>
              <span
                class="badge bg-primary rounded-pill"
                v-if="Object.keys(selectedPM).length !== 0"
                >Cena: {{ selectedPM.price }}</span
              >
            </h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 class="my-0">Apoteka</h6>
                </div>
                <div>
                  <select class="form-control" v-model="selectedPM">
                    <option
                      v-for="option in options"
                      :key="option.id"
                      :value="option"
                    >
                      {{ option.pharmacy.name }}
                    </option>
                  </select>
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 class="my-0">Datum:</h6>
                </div>
                <div>
                  <Datepicker
                    placeholder="Datum"
                    class="form-control"
                    v-model="reservation.date"
                  />
                </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 class="my-0">Kolicina:</h6>
                </div>
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
              </li>
              <li class="list-group-item">
                <button
                  type="button"
                  class="btn btn-primary"
                  style="color: white"
                  @click="reserve()"
                >
                  Rezervisi
                </button>
              </li>
            </ul>
            <div>
              <h5 class="card-header">Rate this medicine</h5>
              <div class="card-body">
                <div class="input-group">
                  <select
                    class="custom-select mr-sm-2"
                    id="inlineFormCustomSelect"
                    v-model="mark"
                  >
                    <option disabled selected value="">Choose...</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </select>
                  <span class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      style="color: white"
                      v-on:click="rateMedicine()"
                    >
                      Submit
                    </button>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-7 col-lg-8">
            <table>
              <tr>
                <td><small class="text-muted">Name</small></td>
                <td>
                  <h3>{{ medicine.name }}</h3>
                </td>
              </tr>
              <tr>
                <td><small class="text-muted">Type</small></td>
                <td>{{ medicine.type }}</td>
              </tr>
              <tr>
                <td><small class="text-muted">Specification</small></td>
                <td>{{ medicine.specification }}</td>
              </tr>
              <tr>
                <td><small class="text-muted">Rating</small></td>
                <td>{{ this.rating }}</td>
              </tr>
            </table>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Datepicker from "vuejs-datepicker";
// import vSelect from "vue-select";
import Swal from "sweetalert2";

const API_URL = config.API_URL;

export default {
  name: "MedicinePage",
  components: {
    Datepicker,
  },
  data() {
    return {
      rating: 0,
      mark: 0,
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
      },
    };
  },
  methods: {
    reserve() {
      console.log(this.selectedPM.value)
      if (
        Object.keys(this.selectedPM).length === 0 ||
        this.reservation.pharmacy == "" ||
        this.reservation.date == "" ||
        this.reservation.quantity == ""
      ) {
        Swal.fire({
          title: "Error",
          text: "You need to select all values!",
          icon: "error",
          showConfirmButton: false,
          timer: 2000,
        });
        return;
      }
      axios
        .get(
          `${API_URL}/promotions/get-promotion-for-medicine/${this.selectedPM.pharmacy.id}/${this.medicine.id}`
        )
        .then((response) => {
          let promotion = response.data.retObj;
          if (promotion == null) {
            promotion = {};
          }

          if (promotion.endDate < new Date().getTime()) {
            promotion = {};
          }

          let price = this.selectedPM.price;

          if (Object.keys(promotion).length !== 0) {
            price -= (price * promotion.discount) / 100;
          }

          this.reservation.pharmacy = this.selectedPM.pharmacy;
          this.reservation.medicine = this.selectedPM.medicine;
          this.reservation.totalPrice = price * this.reservation.quantity;
          this.reservation.date = this.reservation.date.getTime();
          console.log(this.reservation);
          axios
            .post(`${API_URL}/reservations/`, this.reservation)
            .then((response) => {
              if (response.status === 200) {
                Swal.fire({
                  title: "Success",
                  text: "Posted reservation!",
                  icon: "success",
                });
              }
            });
        });
    },
    rateMedicine() {
      const arr = window.location.href.split("/");
      const id = arr[arr.length - 1];
      axios
        .get(`${API_URL}/ratings/rate-medicine/${id}/${this.mark}`)
        .then((response) => {
          Swal.fire({
            title: response.data,
            icon: "success",
            showConfirmButton: false,
            timer: 1500,
          });
        })
        .catch((error) => {
          console.log(this.message);
          Swal.fire({
            title: "Rate error",
            text: error.response.data,
            icon: "error",
            showConfirmButton: false,
            timer: 1500,
          });
        });
    },
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 1];
    axios.get(`${API_URL}/patients/registered-patient`).then((response) => {
      this.reservation.patient = response.data;
    });

    axios.get(`${API_URL}/ratings/get-rating/${id}/${4}`).then((response) => {
      this.rating = response.data;
    });

    axios.get(`${API_URL}/medicines/${id}`).then((response) => {
      this.medicine = response.data;
      axios
        .get(`${API_URL}/pharmacy-medicines/get-pharmacies/${this.medicine.id}`)
        .then((response) => {
          this.options = response.data;
        });
    });
  },
};
</script>

<style scoped>
main {
  margin-top: 5%;
  text-align: left;
}

table {
  margin-top: 5%;
  width: 70%;
}

td,
th {
  text-align: left;
  padding: 2%;
}

select:invalid {
  color: gray;
}
</style>