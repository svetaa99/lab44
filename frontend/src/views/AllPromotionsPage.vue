<template>
  <div class="container">
    <h1>All promotions</h1>
    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col" v-for="p in promotions" :key="p.id">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{{p.id}}</h5>
            <p class="card-text">Medicines: {{[p.medicines.map(m => {return m.name})]}}</p>
            <p class="card-text">Discount: {{p.discount}}</p>
            <p class="card-text">Start date: {{new Date(p.startDate).toDateString()}}</p>
            <p class="card-text">End date: {{new Date(p.endDate).toDateString()}}</p>
            <button class="btn btn-outline-danger" @click="handleDeleteClick(p)">Delete promotion</button>
          </div>
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
  data() {
    return {
      promotions: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/promotions/all`)
      .then(response => {
        this.promotions = response.data.retObj
      })
  },
  methods: {
    handleDeleteClick(promotion) {
      console.log(promotion)
      Swal.fire({
        title: 'Are you sure?',
        text: 'You won\'t be able to undo this operation!',
        icon: 'warning',
        showCancelButton: true,
      }).then(result => {
        if (result.isConfirmed) {
          axios
            .delete(`${API_URL}/promotions/delete-promotion/${promotion.id}`)
            .then(response => {
              if (response.status === 200) {
                Swal.fire({
                  title: 'Success',
                  text: 'Successfully deleted promotion',
                  icon: 'success'
                }).then(result => {
                  if (result.isConfirmed) {
                    window.location.reload()
                  }
                })
              }
            })
        }
      })
    }
  }
}
</script>

<style>

</style>