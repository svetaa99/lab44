<template>
  <div class="container">
    <div class="main-body">
      <br /><br />

      <edit-profile-modal-component></edit-profile-modal-component>

      <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
          <div class="card">
            <div class="card-body">
              <div class="d-flex flex-column align-items-center text-center">
                <div class="mt-1">
                  <h4>{{ user.name }} {{user.surname}}</h4>
                  <p class="text-secondary mb-1">Tip</p>
                  <button
                    type="button"
                    class="btn btn-primary"
                    data-toggle="modal"
                    data-target="#editProfileModal"
                  >
                    Edit
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div class="card mt-3">
            <table class="table">
              <tbody>
                <tr>
                  <td>Points</td>
                  <td>100</td>
                </tr>
              </tbody>
            </table>
            <button class="btn btn-outline-primary">Loyality program</button>
          </div>
        </div>
        <div class="col-md-8">
          <div class="card mb-3">
            <div class="card-body">
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Name</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{user.name}}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Surname</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{user.surname}}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Email</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{user.email}}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Phone number</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{user.phoneNum}}</div>
              </div>
              <hr />
              <div class="row">
                <div class="col-sm-3">
                  <h6 class="mb-0">Address</h6>
                </div>
                <div class="col-sm-9 text-secondary">{{user.address}}</div>
              </div>
            </div>
          </div>
          <div class="card mb-3">
            <div class="card-body">
              <h3>Alergije</h3>
              <br />
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  placeholder="New alergy"
                  aria-label="New alergy"
                  aria-describedby="basic-addon2"
                />
                <div class="input-group-append">
                  <button class="btn btn-outline-primary" type="button">
                    Add
                  </button>
                </div>
              </div>
              <table class="table table-sm">
                <tbody>
                  <tr>
                    <td>Alergija 1</td>
                    <td>
                      <button class="btn btn-outline-danger">Delete</button>
                    </td>
                  </tr>
                  <tr>
                    <td>Alergija 2</td>
                    <td>
                      <button class="btn btn-outline-danger">Delete</button>
                    </td>
                  </tr>
                  <tr>
                    <td>Alergija 3</td>
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
import axios from 'axios';
import { config } from '@/config.js'
import ProfileEditModalComponent from "../components/ProfileEditModalComponent.vue";

const API_URL = config.API_URL

export default {
  components: {
    "edit-profile-modal-component": ProfileEditModalComponent,
  },
  data() {
    return {
      user: {},
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/users/1`)
      .then(response => {this.user = response.data; console.log(this.user)})
  }
};
</script>

<style>
</style>