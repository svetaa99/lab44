<template>
  <div
    class="modal fade"
    id="updateDoctorModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="updateDoctorModalTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="updateDoctorModalTitle">Edit {{doctor.name}}</h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <input
            type="time"
            class="ml-1"
            v-model="doctor.startTime"
          >

          <input
            type="time"
            class="ml-1"
            v-model="doctor.finishTime"
          >
          <br/> <br/>
          <button type="button" class="btn btn-primary" @click="handleSetClick">Set new work time</button>
          <br/> <br/>
          <button type="button" class="btn btn-outline-danger" @click="handleRemoveClick">Remove doctor from pharmacy</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  props: {
    doctor: Object,
    doctorRole: Number,
  },
  methods: {
    handleSetClick() {
      const putObject = {
        startTime: this.doctor.startTime,
        finishTime: this.doctor.finishTime,
      }
      if (this.doctorRole === 3) {
        axios
          .put(`${API_URL}/pharmacist/update-work-hours/${this.doctor.id}`, putObject)
          .then(response => {
            const {startTime, finishTime} = response.data
            this.doctor.startTime = startTime
            this.doctor.finishTime = finishTime
            Swal.fire({
              title: 'Success',
              text: 'Successfully updated work time of the doctor.',
              icon:'success'
            })
          })
      } else if (this.doctorRole === 2) {
        axios
          .put(`${API_URL}/dermatologists/update-work-hours/${this.doctor.id}`, putObject)
          .then(response => {
            const {startTime, finishTime} = response.data
            this.doctor.startTime = startTime
            this.doctor.finishTime = finishTime
            Swal.fire({
              title: 'Success',
              text: 'Successfully updated work time of the doctor.',
              icon:'success'
            })
          })
      }
    },
    handleRemoveClick() {
      Swal.fire({
        title: 'Are you sure?',
        text: 'You won\'t be able to undo this operation. Doctor will be removed permanently.',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
      }).then(result => {
        if (result.isConfirmed) {
          if (this.doctorRole === 3) {
            axios
              .delete(`${API_URL}/labadmins/remove-pharmacist/${this.doctor.id}`)
              .then(response => {
                if (response.status === 200) {
                  Swal.fire({
                    title: 'Success',
                    text: 'Successfully deleted pharmacist',
                    icon: 'success'
                  }).then(result => {
                    if (result.isConfirmed) {
                      window.location.reload()
                    }
                  })
                }
              })

          } else {
            axios
              .delete(`${API_URL}/labadmins/remove-dermatologist/${this.doctor.id}`)
              .then(response => {
                if (response.status === 200) {
                  Swal.fire({
                    title: 'Success',
                    text: 'Successfully deleted dermatologist',
                    icon: 'success'
                  }).then(result => {
                    if (result.isConfirmed) {
                      window.location.reload()
                    }
                  })
                }
              })

          }
        }
      })
    },
  }
}
</script>

<style>

</style>