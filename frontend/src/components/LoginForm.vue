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
import axios from 'axios';
export default {
  data: () => {
    return {
      email: "",
      password: "",
      loginObject: null,
      hasLoginError: false,
    };
  },
  methods: {
    login()
    {
      var loginData = {"email": this.email, "password": this.password};
      console.log(loginData);
      axios
      .post('http://localhost:8000/users/login-user', loginData)
      .then(response => {this.loginObject = response.data; this.printInfo()});
    },
    printInfo()
    {
      console.log(this.loginObject);
      if(this.loginObject.accessToken == ""){
        alert("Bad credentials!");
      }
      else{
        alert("Successfully logged in!");
        window.location.href="http://localhost:8080/";
      }
    }
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