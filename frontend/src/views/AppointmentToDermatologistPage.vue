<template>
  <div>
    <AppointmentDermatologistComponent
      v-bind:dermatologistTerms="this.dermatologistTerms" @clicked="updateTerms"
    />
  </div>
</template>

<script>
import axios from "axios";
import AppointmentDermatologistComponent from "../components/DermatologistAppointmentComponent.vue";
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
    updateTerms(dermatologistTerms) {
      this.dermatologistTerms = dermatologistTerms
    }
  }
};
</script>

<style>
</style>