<template>
  <div class="container">
    <h1>Edit pharmacy</h1>
    <form>
      <label for="name">Name: </label>
      <input id="name" type="text" class="form-control" v-model="pharmacy.name">

      <br/>

      <label for="desc">Description: </label>
      <br/>
      <textarea id="desc" rows="10" v-model="pharmacy.description" class="form-control more-height" placeholder="Descrioption about pharmacy"></textarea>

      <br/>

      <h3>Address</h3>
      <MapComponent v-if="pharmacy.address" :address="pharmacy.address" :edit="true" @clicked="updateAddress"/>
      
      <br/>

      <label for="street">Street</label>
      <input type="text" class="form-control" id="street" v-model="pharmacy.address.street">

      <label for="city">City</label>
      <input type="text" class="form-control" id="city" v-model="pharmacy.address.city">

      <label for="country">Country</label>
      <input type="text" class="form-control" id="country" v-model="pharmacy.address.country">

      <label for="longitude">Longitude</label>
      <input type="text" class="form-control" id="longitude" v-model="pharmacy.address.longitude">

      <label for="lat">Latitude</label>
      <input type="text" class="form-control" id="lat" v-model="pharmacy.address.latitude">

      <br/>
      <br/>

      <button type="button" class="btn btn-success" @click="handleSaveChangesClick">Save changes</button>

    </form>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import MapComponent from '../components/MapComponent.vue';
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  components: { MapComponent },
  data() {
    return {
      pharmacy: {},
    }
  },
  mounted() {
    const arr = window.location.href.split('/');
    const id = arr[arr.length - 1];

    axios
      .get(`${API_URL}/pharmacies/${id}`)
      .then(response => {
        this.pharmacy = response.data
      })
  },
  methods: {
    updateAddress(address) {
      this.pharmacy.address = address;
    },
    handleSaveChangesClick() {
      try {
        this.validateInputs()
        axios
          .put(`${API_URL}/pharmacies/update-pharmacy`, this.pharmacy)
          .then(response => {
            if (response.status === 200) {
              Swal.fire({
                title: 'Success',
                text: `Successfully updated pharmacy`,
                icon: 'success'
              })
              this.pharmacy = response.data.retObj
            }
          })
          .catch(err => {
            if (err.response.status === 400) {
              Swal.fire({
                title: 'Error',
                text: `${err.response.data.message}`,
                icon: 'error'
              })
            }
          })
      } catch (err) {
        Swal.fire({
          title: 'Error',
          text: `${err.message}`,
          icon: 'error'
        })
      }
    },
    validateInputs() {
      if (this.pharmacy.name === "") {
        throw Error("Pharmacy name cannot be empty")
      } else if (this.pharmacy.description === "") {
        throw Error("Pharmacy description cannot be empty")
      } else if (this.pharmacy.address.city === "") {
        throw Error("City cannot be empty")
      } else if (this.pharmacy.address.street === "") {
        throw Error("Street cannot be empty")
      } else if (this.pharmacy.address.number === "") {
        throw Error("Number cannot be empty")
      } else if (this.pharmacy.address.longitude === "" || this.pharmacy.address.longitude === 0) {
        throw Error("Longitude cannot be empty")
      } else if (this.pharmacy.address.latitude === "" || this.pharmacy.address.latitude === 0) {
        throw Error("Latitude cannot be empty")
      }
    }
  }
}
</script>

<style>
  .more-height {
    height: 300px;
  }
</style>