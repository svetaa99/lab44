<template>
  <div class="container">
    <DoctorSearchComponent :doctorRole="3" @clicked="handleSearchClick" />
    <DoctorFilterComponent :doctorRole="3" @clicked="handleFilterClick" />
    <div class="row justify-content-md-center">
      <div class="col align-self-center">
        <button type="button" class="btn btn-primary" @click="handleResetClick">Reset data</button>
      </div>
    </div><br/>
    <DoctorListComponent :doctorRole="3" :doctors="pharmacists" :action="'update'" />
  </div>
</template>

<script>
import DoctorSearchComponent from '@/components/DoctorSearchComponent.vue'
import DoctorFilterComponent from '@/components/DoctorFilterComponent.vue'
import DoctorListComponent from '../components/DoctorListComponent.vue';
import axios from 'axios'
import { config } from "@/config.js";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  components: {
    DoctorSearchComponent,
    DoctorFilterComponent,
    DoctorListComponent,
  },
  data() {
    return {
      pharmacists: [],
      userRoles: [],
    }
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });

    if (this.userRoles.includes(4)) {
        axios
          .get(`${API_URL}/labadmins/all-pharmacists`)
          .then(response => {
            this.pharmacists = response.data;
          })
      } else {
        axios
          .get(`${API_URL}/pharmacist/all`)
          .then(response => {
            this.pharmacists = response.data;
          })
      }
  },
  methods: {
    handleSearchClick(search) {
      axios
        .post(`${API_URL}/pharmacist/search`, search)
        .then(response => {
          if (Object.keys(response.data).length === 0) {
            Swal.fire({
              text: 'No results from the search',
              icon: 'info'
            })
          } else {
            this.pharmacists = response.data
          }
        })
    },
    handleFilterClick(filter) {
      const {params, values} = filter

      axios
        .post(`${API_URL}/pharmacist/filter/${params}/${values}`, this.pharmacists)
        .then(response => {
          this.pharmacists = response.data
        })
    },
    handleResetClick() {
      if (this.userRoles.includes(4)) {
        axios
          .get(`${API_URL}/labadmins/all-pharmacists`)
          .then(response => {
            this.pharmacists = response.data;
          })
      } else {
        axios
          .get(`${API_URL}/pharmacist/all`)
          .then(response => {
            this.pharmacists = response.data;
          })
      }
    }
  }
}
</script>

<style>

</style>