<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">{{vacation.doctor.name}}</h5>
      <p class="card-text">Start date: {{vacation.start}}</p>
      <p class="card-text">Finish date: {{vacation.finish}}</p>
      <p class="card-text">Vacation type: {{vacation.type}}</p>
      <p class="card-text"><small class="text-muted">Status: {{vacation.status}}</small></p>
      <div v-if="vacation.status === 'ON_HOLD'">
        <button type="button" class="btn btn-success" @click="acceptRequest">Accept</button>
        <button type="button" class="btn btn-outline-danger" @click="declineRequest">Decline</button>
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
    vacation: Object,
  },
  methods: {
    acceptRequest() {
      axios
        .put(`${API_URL}/vacation/update-vacation/${this.vacation.id}/1/accept`)
        .then(response => {
          if (response.status === 200) {
            Swal.fire({
              title: 'Successfully confirmed request',
              icon: 'success'
            }).then(result => {
              if (result.isConfirmed) {
                window.location.reload();
              }
            })
          }
        })
    },
    declineRequest() {
      Swal.fire({
        title: 'Why did you declined this request?',
        icon: 'question',
        input: 'text',
        showCancelButton: true,
        confirmButtonText: 'Send answer',
      }).then(result => {
        axios
          .put(`${API_URL}/vacation/update-vacation/${this.vacation.id}/2/${result.value}`)
          .then(response => {
            if (response.status === 200) {
              Swal.fire({
                title: 'Successfully declined request',
                icon: 'success'
              }).then(result => {
                if (result.isConfirmed) {
                  window.location.reload();
                }
              })
            }
          })
      })
    }
  }
}
</script>

<style>

</style>