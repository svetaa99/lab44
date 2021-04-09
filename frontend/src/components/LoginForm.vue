<template>
  <main class="form-signin">
    <form @submit.prevent="login">
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

      <br />

      <label for="inputUsername" class="visually-hidden">Username</label>
      <input
        @keyup.enter="login()"
        type="username"
        v-model="username"
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
        The username or the password are incorrect
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

      <p class="mt-5 mb-3 text-muted">&copy; labnoni 2021</p>
    </form>
  </main>
</template>

<script>
export default {
  data: () => {
    return {
      username: "",
      password: "",
    };
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