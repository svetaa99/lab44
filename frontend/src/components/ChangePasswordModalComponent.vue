<template>
  <div
    class="modal fade"
    id="changePasswordModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="changePasswordModalTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editProfileModalTitle">
            Change Password
          </h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row g-3">
            <div class="col-6">
              <label for="password" class="form-label">Password</label>
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
                Please insert a valid phone number.
              </div>
            </div>
            <div class="col-6">
              <label for="confirmPassword" class="form-label"
                >Confirm Password</label
              >
              <input
                type="confirm-password"
                class="form-control"
                id="confirmPassword"
                placeholder=""
                value=""
                v-model="confirmPassword"
                required
              />
              <div v-if="submitted && !passwordsMatch" class="text-danger">
                Please insert a valid password.
              </div>
            </div>
          </div>
          <br />
          <button class="btn btn-primary btn-block" @click="update()">
            Save changes
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
const API_URL = config.API_URL;
export default {
  props: ["user"],
  data() {
    return {
      password: "",
      confirmPassword: "",
      submitted: false,
    };
  },
  mounted() {
    this.password = this.user.password;
  },
  methods: {
    update() {
      axios.post(`${API_URL}/users/update-user`, this.user).then((response) => {
        this.user = response.data;
        if (this.user.password == "chang3m3")
          localStorage.setItem("pw", this.user.password);
        else localStorage.setItem("pw", "");
      });
    },
  },
};
</script>

<style>
</style>