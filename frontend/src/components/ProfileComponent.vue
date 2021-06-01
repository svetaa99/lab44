<template>
  <div class="container">
    <div class="main-body">
      <br /><br />

      <edit-profile-modal-component :user="user"></edit-profile-modal-component>
      <change-password-modal-component
        :user="user"
      ></change-password-modal-component>

      <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
          <div class="card">
            <div class="card-body">
              <div class="d-flex flex-column align-items-center text-center">
                <div class="mt-1">
                  <h4>{{ user.name }} {{ user.surname }}</h4>
                  <p class="text-secondary mb-1"></p>
                  <button
                    type="button"
                    class="btn btn-primary mr-4"
                    data-toggle="modal"
                    data-target="#editProfileModal"
                  >
                    Edit
                  </button>
                  <button
                    type="button"
                    class="btn btn-primary"
                    data-toggle="modal"
                    data-target="#changePasswordModal"
                  >
                    Change password
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div class="card mt-3">
            <table class="table">
              <tbody>
                <tr>
                  <td>Penalty</td>
                  <td>{{ penalty }}</td>
                </tr>
              </tbody>
            </table>
            <button class="btn btn-outline-primary">Loyality program</button>
          </div>
          <br />
          <div class="card mt-3" v-if="userRoles.includes(1)">
            <table class="table">
              <thead>
                <th>Appointments</th>
              </thead>
              <tbody>
                <tr>
                  <td>
                    <button
                      @click="$router.push('my-pharmacist-reservations')"
                      class="btn btn-primary"
                    >
                      Pharmacist
                    </button>
                  </td>
                  <td>
                    <button
                      @click="$router.push('my-dermatologist-reservations')"
                      class="btn btn-primary"
                    >
                      Dermatologist
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="card mt-3" v-if="userRoles.includes(1)">
            <table class="table">
              <thead>
                <th>Visit history</th>
              </thead>
              <tbody>
                <tr>
                  <td>
                    <button
                      @click="$router.push('my-pharmacist-visits')"
                      class="btn btn-primary"
                    >
                      To Pharmacist
                    </button>
                  </td>
                  <td>
                    <button
                      @click="$router.push('my-dermatologist-visits')"
                      class="btn btn-primary"
                    >
                      To Dermatologist
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="col-md-8">
          <div class="card mb-3">
            <div class="card-body">
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Name</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{ user.name }}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Surname</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{ user.surname }}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Email</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{ user.email }}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Phone number</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{ user.phoneNum }}</div>
              </div>
            </div>
          </div>
          <div class="card mb-3" v-if="userRoles.includes(1)">
            <div class="card-body">
              <h3>Alergije</h3>
              <br />
              <div class="input-group mb-3">
                <!-- string value -->
                <model-select
                  :options="options"
                  v-model="item2"
                  placeholder="Select medicine"
                  class="form-control"
                  style="width: 90%;"
                >
                </model-select>
                <!-- <input
                  type="text"
                  class="form-control"
                  placeholder="New alergy"
                  aria-label="New alergy"
                  aria-describedby="basic-addon2"
                /> -->
                <div class="input-group-append">
                  <button class="btn btn-outline-primary" type="button">
                    Add
                  </button>
                </div>
              </div>
              <table class="table table-sm">
                <tbody>
                  <tr>
                    <td>Lek1</td>
                    <td>
                      <button class="btn btn-outline-danger">Delete</button>
                    </td>
                  </tr>
                  <tr>
                    <td>Lek2</td>
                    <td>
                      <button class="btn btn-outline-danger">Delete</button>
                    </td>
                  </tr>
                  <tr>
                    <td>Lek3</td>
                    <td>
                      <button class="btn btn-outline-danger">Delete</button>
                    </td>
                  </tr>
                </tbody>
              </table>
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
import ProfileEditModalComponent from "../components/ProfileEditModalComponent.vue";
import ChangePasswordModalComponent from "../components/ChangePasswordModalComponent.vue";
import { ModelSelect } from "vue-search-select";

const API_URL = config.API_URL;

export default {
  components: {
    "edit-profile-modal-component": ProfileEditModalComponent,
    "change-password-modal-component": ChangePasswordModalComponent,
    "model-select": ModelSelect,
  },
  data() {
    return {
      user: {},
      penalty: 0,
      userRoles: [],
      options: [],
      item2: ''
    };
  },
  mounted() {
    axios.get(`${API_URL}/users/user`).then((response) => {
      this.user = response.data;
      console.log(response.data);
    });
    axios.get(`${API_URL}/penalty/my`).then((response) => {
      this.penalty = response.data;
    });
    axios.get(`${API_URL}/medicines/all`).then((response) => {
      this.options = response.data;
      console.log(this.options);
    });

    const tokenItem = JSON.parse(localStorage.getItem("jwt"));

    tokenItem.token.roles.map((el) => {
      this.userRoles.push(el.id);
    });
  },
  methods: {
    reset2 () {
        this.item2 = ''
      },
  }
};
</script>

<style>
</style>