<template>
  <div class="container">
    <h1>Reports about pharmacy</h1>
    <br/>
    
    <div class="row">
      <h3>Pharmacy rating: {{ratings.pharmacy}}</h3>
    </div>

    <div class="row">
      <div class="col">
        <h3>Dermatologist ratings</h3><br/>
        <table width="80%" class="table table-striped table-bordered table-sm">
          <thead>
            <tr>
              <th class="th-sm">Name</th>
              <th class="th-sm">Surname</th>
              <th class="th-sm">Email</th>
              <th class="th-sm">Rating</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="d in this.dermatologists" :key="d.id">
              <td>{{d.name}}</td>
              <td>{{d.surname}}</td>
              <td>{{d.email}}</td>
              <td>{{d.rating}}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="col">
        <h3>Pharmacist ratings</h3><br/>
        <table width="80%" class="table table-striped table-bordered table-sm">
          <thead>
            <tr>
              <th class="th-sm">Name</th>
              <th class="th-sm">Surname</th>
              <th class="th-sm">Email</th>
              <th class="th-sm">Rating</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="d in this.pharmacists" :key="d.id">
              <td>{{d.name}}</td>
              <td>{{d.surname}}</td>
              <td>{{d.email}}</td>
              <td>{{d.rating}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

  </div>
</template>

<script>
import DoctorListComponent from '../components/DoctorListComponent.vue';
import axios from "axios";
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  components: {
    DoctorListComponent,
  },
  data() {
    return {
      admin: null,
      ratings: {
        pharmacy: -1,
      },
      dermatologists: [],
      pharmacists: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data

        axios
          .get(`${API_URL}/ratings/get-rating/${this.admin.pharmacy.id}/1`)
          .then(response => {
            this.ratings.pharmacy = response.data
          })

        axios
          .get(`${API_URL}/ratings/get-pharmacists-rating`)
          .then(response => {
            this.pharmacists = response.data.retObj;
          })

        axios
          .get(`${API_URL}/ratings/get-dermatologists-rating`)
          .then(response => {
            this.dermatologists = response.data.retObj;
          })
      })
  },
}
</script>

<style>

</style>