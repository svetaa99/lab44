<template>
  <div>
    <update-doctor-work-time-modal
      :doctorRole="doctorRole"
      :doctor="selectedDoctor"
    ></update-doctor-work-time-modal>
    <rate-modal :doctorId="doctorId" @clicked="rateDoctor"></rate-modal>

    <table width="80%" class="table table-striped table-bordered table-sm">
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Surname</th>
          <th class="th-sm">Email</th>
          <th v-if="doctorRole == 3">Pharmacy name</th>
          <th v-if="doctorRole == 3">Rating</th>
          <th v-if="doctorRole == 2">Pharmacies</th>
          <th v-if="userRoles.includes(1)">Rate</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="d in this.doctors"
          :key="d.id"
          @dblclick="handleRowDblClick(d)"
        >
          <td>{{ d.name }}</td>
          <td>{{ d.surname }}</td>
          <td>{{ d.email }}</td>
          <td v-if="doctorRole == 3">
            {{ d.pharmacy ? d.pharmacy.name : "Not employed" }}
          </td>
          <td v-if="doctorRole == 3">{{ d.rating }}</td>
          <td v-if="doctorRole == 2">{{ printPharmacies(d) }}</td>
          <td v-if="userRoles.includes(1)">
            <button
              type="button"
              class="btn btn-primary mr-4"
              data-toggle="modal"
              data-target="#rateModal"
              @click="handleRateClick(d.id)"
            >
              Rate
            </button>
          </td>

          <td v-if="userRoles.includes(4) && action == 'update'">
            <button
              type="button"
              class="btn btn-primary"
              data-toggle="modal"
              data-target="#updateDoctorModal"
              @click="handleUpdateClick(d)"
            >
              Update
            </button>
          </td>

          <td v-if="userRoles.includes(4) && action == 'add'">
            <button
              type="button"
              class="btn btn-primary"
              @click="handleAddClick(d)"
            >
              Add
            </button>
          </td>

          <td v-if="userRoles.includes(4) && action == 'select'">
            <button
              type="button"
              class="btn btn-primary"
              @click="handleSelectClick(d)"
            >
              Select
            </button>
          </td>

          <td v-if="doctorRole == 2 && action == 'ratings'">
            {{ d.rating }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import UpdateDoctorWorkTimeModal from "./UpdateDoctorWorkTimeModal.vue";
import RateModalComponent from "./RateModalComponent.vue";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  components: {
    UpdateDoctorWorkTimeModal,
    "rate-modal": RateModalComponent,
  },
  props: {
    doctors: Array,
    doctorRole: Number,
    action: String,
  },
  data() {
    return {
      userRoles: [],
      edit: false,
      doctorId: -1,
      selectedDoctor: {
        name: "",
        surname: "",
        email: "",
        phoneNum: "",
        address: "",
        startTime: "",
        finishTime: "",
      },
    };
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem("jwt"));
    tokenItem.token.roles.map((el) => {
      this.userRoles.push(el.id);
    });
  },
  methods: {
    printPharmacies(doctor) {
      return doctor.pharmacies.length === 0
        ? "Not employed"
        : "" +
            doctor.pharmacies.map((p) => {
              return " " + p.name;
            });
    },
    handleRateClick(doctorId) {
      this.doctorId = doctorId;
    },

    rateDoctor(retObj) {
      const {doctorId, mark} = retObj;
      axios
        .get(`${API_URL}/ratings/rate-doctor/${doctorId}/${mark}`)
        .then((response) => {
          Swal.fire({
            title: response.data,
            icon: "success",
            showConfirmButton: false,
            timer: 1500,
          });
          
        })
        .catch((error) => {
          console.log(this.message);
          Swal.fire({
            title: "Rate error",
            text: error.response.data,
            icon: "error",
            confirmButtonText: "Ok",
          });
        });
    },

    handleUpdateClick(doctor) {
      this.selectedDoctor = doctor;
    },

    handleAddClick(doctor) {
      this.$emit("clicked", doctor);
    },

    handleSelectClick(doctor) {
      this.$emit("clicked", doctor);
    },

    handleRowDblClick(doctor) {
      this.$emit("clicked", doctor);
    },


  },
};
</script>

<style>
</style>