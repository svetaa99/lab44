<template>
  <div class="container">
    <div class="row">
      <!-- Post Content Column -->
      <div class="col-lg-8">
        <PharmacyMainComponent :pharmacy="pharmacy" :admin="admin" />
      </div>
      <div class="col-md-4" v-if="userRoles.length !== 0">
        <div class="card my-4" >
          <h5 class="card-header">Rate this pharmacy</h5>
          <div class="card-body">
            <div class="input-group">
              <select
                class="custom-select mr-sm-2"
                id="inlineFormCustomSelect"
                v-model="mark"
              >
                <option selected>Choose...</option>
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
                  v-on:click="ratePharmacy()"
                >
                  Submit
                </button>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import PharmacyMainComponent from "@/components/PharmacyMainComponent.vue";

const API_URL = config.API_URL;

export default {
  props: {
    pharmacy: Object,
    admin: Object,
  },
  components: {
    PharmacyMainComponent,
  },
  data() {
    return {
      rating: 0,
      mark: 0,
      message: "",
      userRoles: []
    };
  },
  mounted() {
    const arr = window.location.href.split("/");
    const id = arr[arr.length - 1];
    axios
        .get(`${API_URL}/ratings/get-rating/${id}/${1}`)
        .then((response) => {
          this.rating = response.data;
        });
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });
  },
  methods: {
    ratePharmacy() {
      const arr = window.location.href.split("/");
      const id = arr[arr.length - 1];
      axios
        .get(`${API_URL}/ratings/rate-pharmacy/${id}/${this.mark}`)
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
            confirmButtonText: "Ok",
          });
        });
    },
  },
};
</script>

<style>
</style>