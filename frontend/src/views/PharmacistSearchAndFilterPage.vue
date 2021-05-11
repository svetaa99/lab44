<template>
  <div>
    <DoctorSearchComponent @clicked="handleSearchClick" />
    <DoctorFilterComponent :doctorType="'pharmacist'" @clicked="handleFilterClick" />
    <DoctorListComponent :doctors="pharmacists" />
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
      pharmacists: []
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/pharmacist/all`)
      .then(response => {
        this.pharmacists = response.data;
      })
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