<template>
  <main class="form-signin">
    <form @submit.prevent="login">
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

      <br />

      <label for="inputUsername" class="visually-hidden">Username</label>
      <input
        @keyup.enter="login()"
        type="email"
        v-model="email"
        id="inputUsername"
        class="form-control"
        placeholder="Username"
        required
        autofocus
      />

      <br />

      <label for="inputPassword" class="visually-hidden">Password</label>
      <input
        @keyup.enter="login()"
        type="password"
        v-model="password"
        id="inputPassword"
        class="form-control"
        placeholder="Password"
        required
      />

      <br />

      <p v-if="hasLoginError" class="text-danger">
        The email or the password are incorrect
      </p>

      <br />

      <button
        @click="login()"
        class="w-100 btn btn-lg btn-primary"
        type="button"
      >
        Sign in
      </button>

      <hr />

      <label for="inputPassword" class="visually-hidden">No Account?</label>

      <router-link to="/registration" class="w-100 btn btn-lg btn-success"
        >Create New Account</router-link
      >
      <!-- <button class="w-100 btn btn-lg btn-success" type="submit">
        Create New Account
      </button> -->

      <p class="mt-5 mb-3 text-muted">&copy; laboni 2021</p>
    </form>
  </main>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";

import { config } from "@/config.js";
const API_URL = config.API_URL;

export default {
  data: () => {
    return {
      email: "",
      password: "",
      loginObject: null,
      hasLoginError: false,
      jwt: null,
      userRoles: [],
    };
  },
  methods: {
    login() {
      var loginData = { email: this.email, password: this.password };
      axios.post(`${API_URL}/users/login-user`, loginData).then((response) => {
        this.jwt = response.data;
        this.printInfo();
      });
    },
    printInfo() {
      if (this.jwt.accessToken == "") {
        Swal.fire({
          title: "Login error",
          text: "Bad credentials!",
          icon: "error",
          showConfirmButton: false,
          timer: 1500,
        });
      } else {
        Swal.fire({
          title: "Successfully logged in",
          icon: "success",
          showConfirmButton: false,
          timer: 1500,
        }).then(() => {
          this.saveUserToLocalStorage(this.jwt); //save JWT and EXPIRATION
          this.addAxiosInterceptors(axios);

          this.jwt.roles.map((el) => {
            this.userRoles.push(el.id);
          });
          localStorage.setItem("pw", this.password);
          if (
            (this.userRoles.includes(2) || this.userRoles.includes(3)) &&
            this.password == "chang3m3"
          ) {
            localStorage.setItem("pw", this.password);
            window.location.href = "http://localhost:8080/profile";
          } else {
            localStorage.setItem("pw", "");
            window.location.href = "http://localhost:8080/";
          }
        });
      }
    },
    saveUserToLocalStorage(jwt) {
      const jwtExp = {
        token: jwt,
        expiry: new Date().getTime() + jwt.expiresIn,
      };
      localStorage.setItem("jwt", JSON.stringify(jwtExp));
    },
    addAxiosInterceptors(axios) {
      axios.interceptors.request.use((request) => {
        request.headers["Authorization"] =
          "Bearer " + JSON.parse(localStorage.getItem("jwt")).accessToken;
        return request;
      });
    },
  },
};
</script>

<style>
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}

.form-signin .form-control:focus {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>