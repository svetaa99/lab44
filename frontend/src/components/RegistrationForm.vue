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
              v-model="name"
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
              v-model="surname"
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
            <label for="firstName" class="form-label">Username</label>
            <input
              type="text"
              class="form-control"
              id="username"
              placeholder=""
              value=""
              v-model="username"
              required
            />
            <div v-if="submitted && !usernameValid" class="text-danger">
              Please insert a valid username.
            </div>
          </div>

          <div class="col-6">
            <label for="birthday" class="form-label">Birthday</label>
            <vuejs-datepicker
              input-class="form-control"
              v-model="dateOfBirth"
            ></vuejs-datepicker>
            <div v-if="submitted && !dateOfBirthValid" class="text-danger">
              Please insert a valid birthday.
            </div>
          </div>
        </div>
        <br />
        <div class="row g-3">
          <div class="col-6">
            <label for="firstName" class="form-label">Password</label>
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder=""
              value=""
              v-model="password"
              required
            />
            <div v-if="submitted && !passwordValid" class="text-danger">
              Please insert a valid password.
            </div>
          </div>

          <div class="col-6">
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
          </div>
        </div>
        <br />

        <div class="row g-3">
          <div class="col-12">
            <label for="gender" class="form-label">Gender</label>
            <div>
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  v-model="gender"
                  value="Male"
                />
                <label class="form-check-label">Male</label>
              </div>
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  v-model="gender"
                  value="Female"
                />
                <label class="form-check-label">Female</label>
              </div>
            </div>

            <div v-if="submitted && !genderValid" class="text-danger">
              Please select gender.
            </div>
          </div>
        </div>

        <hr class="mb-4" />
        <button class="btn btn-primary btn-block" @click="saveChanges($event)">
          Save changes
        </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    // vuejsDatepicker,
  },
  data: () => {
    return {
      name: "",
      surname: "",
      username: "",
      password: "",
    //   dateOfBirth: "",
      gender: "",
      confirmPassword: "",
      submitted: false,
    };
  },
};
</script>

<style>
</style>