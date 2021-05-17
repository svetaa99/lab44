<template>
  <div class="container">
    <DoctorSearchComponent :doctorRole="2" @clicked="handleSearchClick" />
    <DoctorFilterComponent :doctorRole="2" @clicked="handleFilterClick" />
    <DoctorListComponent :doctorRole="2" :doctors="dermatologists" :action="'update'" />
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
      dermatologists: [],
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
          .get(`${API_URL}/labadmins/all-dermatologists`)
          .then(response => {
            this.dermatologists = response.data;
            console.log(response.data)
          })
      } else {
        axios
          .get(`${API_URL}/dermatologists/all`)
          .then(response => {
            this.dermatologists = response.data;
          })
      }
  },
  methods: {
    handleSearchClick(search) {
      axios
        .post(`${API_URL}/dermatologists/search`, search)
        .then(response => {
          this.dermatologists = response.data
        })
    },
    handleFilterClick(filter) {
      const {params, values} = filter

      axios
        .post(`${API_URL}/dermatologists/filter/${params}/${values}`, this.dermatologists)
        .then(response => {
          this.dermatologists = response.data
        })
    }
  }
}
</script>

<style>

</style>