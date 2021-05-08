<template>
  <div class="container">
    <div class="col-md-12 order-md-1">
      <form class="needs-validation" action="#" novalidate>
        <!-- Name and last name form -->
        <div class="row g-3">
          <div class="col-6">
            <label for="firstName" class="form-label">First name</label>
            <input
              type="text"
              class="form-control"
              id="firstName"
              placeholder=""
              value=""
              v-model="user.name"
              required
            />
            <div v-if="submitted && !nameValid" class="text-danger">
              Please insert a valid name.
            </div>
          </div>

          <div class="col-6">
            <label for="lastName" class="form-label">Last name</label>
            <input
              type="text"
              class="form-control"
              id="lastName"
              placeholder=""
              value=""
              v-model="user.surname"
              required
            />
            <div v-if="submitted && !surnameValid" class="text-danger">
              Please insert a valid surname.
            </div>
          </div>
        </div>

        <br />

        <div class="row g-3">
          <div class="col-6">
            <label for="phoneNumber" class="form-label">Email</label>
            <input
              type="text"
              class="form-control"
              id="email"
              placeholder=""
              value=""
              v-model="user.email"
              required
            />
            <div v-if="submitted && !email" class="text-danger">
              Please insert a valid email.
            </div>
          </div>
        
          <br />

          <div class="col-6">
            <label for="phoneNumber" class="form-label">Phone number</label>
            <input
              type="phoneNumber"
              class="form-control"
              id="phoneNumber"
              placeholder=""
              value=""
              v-model="user.phoneNum"
              required
            />
            <div v-if="submitted && !phoneNumberValid" class="text-danger">
              Please insert a valid phone number.
            </div>
          </div>
        </div>

        <br/>

        <div class="row g-3">
          <div class="col-6">
            <label for="address" class="form-label">Address</label>
            <input
              type="text"
              class="form-control"
              id="address"
              placeholder=""
              value=""
              v-model="user.address"
              required
            />
            <div v-if="submitted && !address" class="text-danger">
              Please insert a valid address.
            </div>
          </div>
      
        </div>
        
        <br />
        <hr class="mb-4" />
        <button type="button" class="btn btn-primary btn-block" @click="register()">
          Register
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  data() {
    return {
      user: {},
      admin: {},
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data;
      })
  },
  methods: {
    register() {
      this.user.pharmacy = this.admin.pharmacy
      axios
        .post(`${API_URL}/pharmacist/create-new`, this.user)
        .then(response => {
          console.log(response.data)
        })
    }
  }
}
</script>

<style>

</style>