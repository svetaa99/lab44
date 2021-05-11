<template>
  <div>
    <AppointmentDermatologistComponent
      v-bind:dermatologistTerms="this.dermatologistTerms"
      @clicked="updateTerms"
    />
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Swal from "sweetalert2";
import AppointmentDermatologistComponent from "../components/DermatologistAppointmentComponent.vue";

const API_URL = config.API_URL;

export default {
  components: {
    AppointmentDermatologistComponent,
  },
  data() {
    return {
      dermatologistTerms: [],
    };
  },
  mounted() {
    axios
      .get("http://localhost:8000/doctorterms/dermatologist-all")
      .then((response) => {
        this.dermatologistTerms = response.data;
        console.log(this.dermatologistTerms);
      });
  },
  methods: {
    updateTerms(termId) {
      axios
        .get(`${API_URL}/doctorterms/reserve-dermatologist/${termId}`)
        .then((response) => {
          this.dermatologistTerms = response.data;
          console.log(response.data);
          Swal.fire({
            title: "Successfully",
            text: "Appointment made successfully",
            icon: "success",
          });
        });
    },
  },
};
</script>

<style>
</style>