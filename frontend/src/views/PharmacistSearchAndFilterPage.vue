<template>
  <div class="container">
    <DoctorSearchComponent :doctorType="'pharmacist'" @clicked="handleSearchClick" />
    <DoctorFilterComponent :doctorType="'pharmacist'" @clicked="handleFilterClick" />
    <DoctorListComponent :doctorType="'pharmacist'" :doctors="pharmacists" />
  </div>
</template>

<script>
import DoctorSearchComponent from '@/components/DoctorSearchComponent.vue'
import DoctorFilterComponent from '@/components/DoctorFilterComponent.vue'
import DoctorListComponent from '../components/DoctorListComponent.vue';
import axios from 'axios'
import { config } from "@/config.js";

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
            console.log(response.data)
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
          this.pharmacists = response.data
        })
    },
    handleFilterClick(filter) {
      const {params, values} = filter

      axios
        .post(`${API_URL}/pharmacist/filter/${params}/${values}`, this.pharmacists)
        .then(response => {
          this.pharmacists = response.data
        })
    }
  }
}
</script>

<style>

</style>