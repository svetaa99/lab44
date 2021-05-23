<template>
  <div class="container">
    <h1>Reports about pharmacy</h1>
    <br/>
    
    <div class="row">
      <h3>Pharmacy rating: {{ratings.pharmacy}}</h3>
    </div>

    <div class="row">
      <div v-if="showDermatologists" class="col">
        <h3>Dermatologist ratings</h3><br/>
        <DoctorListComponent v-bind:doctors="dermatologists" :doctorRole="2" :action="'ratings'" />
      </div>

      <div class="col">
        <h3>Pharmacist ratings</h3><br/>
        <DoctorListComponent :doctors="pharmacists" :doctorRole="3" :action="'ratings'"/>
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
      showDermatologists: false,
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
          .get(`${API_URL}/labadmins/all-pharmacists`)
          .then(response => {
            this.pharmacists = response.data;
            this.pharmacists.map(p => {
              this.getRating(p, 3).then(data => p.rating = data);
            })
          })

        axios
          .get(`${API_URL}/labadmins/all-dermatologists`)
          .then(response => {
            const dermas = response.data
            dermas.map(d => {
              this.getRating(d, 2).then(data => d.rating = data);
            })
            this.dermatologists = dermas
            console.log(this.dermatologists)
            this.showDermatologists = true
          })
      })
  },
  methods: {
    getRating(doctor, role) {
      return axios
        .get(`${API_URL}/ratings/get-rating/${doctor.id}/${role}`)
        .then(response => response.data)
    }
  }
}
</script>

<style>

</style>