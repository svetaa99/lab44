<template>
  <div>
    <PharmacistRegistrationForm @clicked="register"/>
  </div>
</template>

<script>
import PharmacistRegistrationForm from '@/components/PharmacistRegistrationForm.vue'
import Swal from 'sweetalert2';
import axios from 'axios'
import { config } from "@/config.js";

const API_URL = config.API_URL;

export default {
  components: {
    PharmacistRegistrationForm,
  },
  data() {
    return {
      admin: {},
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data;
      })
  },
  methods: {
    register(user) {
      user.pharmacy = this.admin.pharmacy
      axios
        .post(`${API_URL}/pharmacist/create-new`, user)
        .then(response => {
          if (response.status === 201) {
            Swal.fire({
              title: 'Success',
              text: 'Successfully registrated new pharmacist',
              icon: 'success',
              confirmButtonText: 'Ok'
            })
          }
        })
        .catch(error => {
          if (error.response.status === 400)
            Swal.fire({
                title: 'Error',
                text: `${error.response.data}`,
                icon: 'error',
                confirmButtonText: 'Ok'
            })
        })
    },
    
  }
}
</script>

<style>

</style>