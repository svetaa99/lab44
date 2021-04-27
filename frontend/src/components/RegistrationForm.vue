<template>
  <div class="container">
    <div v-if="!edit" class="py-5 text-center">
      <h2>Registration</h2>
    </div>

    <!-- Form for edit profile -->
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
          <!-- <div class="col-6">
            <label for="birthday" class="form-label">Birthday</label>
            <vuejs-datepicker
              input-class="form-control"
              v-model="dateOfBirth"
            ></vuejs-datepicker>
            <div v-if="submitted && !dateOfBirthValid" class="text-danger">
              Please insert a valid birthday.
            </div>
          </div> -->
        </div>
        <br />
        <div class="row g-3">
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
          <div class="col-6">
            <label for="firstName" class="form-label">Password</label>
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder=""
              value=""
              v-model="user.password"
              required
            />
            <div v-if="submitted && !passwordValid" class="text-danger">
              Please insert a valid password.
            </div>
          </div>

          <!-- <div class="col-6">
            <label for="lastName" class="form-label">Confirm password</label>
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder=""
              value=""
              v-model="confirmPassword"
              required
            />
            <div v-if="!passwordsMatch" class="text-danger">
              Password did not match.
            </div>
          </div> -->
        </div>
        <br />
        <hr class="mb-4" />
        <button class="btn btn-primary btn-block" @click="update()">
          Save changes
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { config } from '@/config.js';
const API_URL = config.API_URL
export default {
  props: ["edit", "user"],
  data: () => {
    return {
      name: "",
      surname: "",
      password: "",
      phoneNum: "",
      submitted: false,
    };
  },
  mounted() {
    if(this.edit) {
      this.name = this.user.name;
      this.surname = this.user.surname;
      this.password = this.user.password;
      this.phoneNum = this.user.phoneNum;
    }
  },
  methods: {
    update() {
      axios
      .post(`${API_URL}/users/update-user`, this.user)
      .then(response => {this.user = response.data; this.name = this.user.name; console.log(this.user)})
    }
  }
};
</script>

<style>
</style>