<template>
  <div
    class="modal fade"
    id="updateMedicinePriceModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="updateMedicinePriceModalTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="updateMedicinePriceModalTitle">Update price</h5>
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
          <h3>{{pharmacyMedicine.medicine.name}}</h3>
          <input
            type="number"
            placeholder="Price"
            min="1"
            v-model="pharmacyMedicine.price"
          >
          <button
            type="button"
            class="btn btn-primary"
            @click="handleUpdateClick"
          >
            Update
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import axios from 'axios'
import { config } from '@/config.js'

const API_URL = config.API_URL

export default {
  props: {
    pharmacyMedicine: Object
  },
  methods: {
    handleUpdateClick() {
      const putObj = {
        pharmacyId: this.pharmacyMedicine.pharmacy.id,
        medicineId: this.pharmacyMedicine.medicine.id,
        price: this.pharmacyMedicine.price,
        quantity: this.pharmacyMedicine.quantity,
      }

      axios
        .put(`${API_URL}/pharmacies/update-price`, putObj)
        .then(response => {
          if (response.status === 200) {
            Swal.fire({
              title: 'Success',
              text: 'Successfully updated price!',
              icon: 'success',
              confirmButtonText: 'Ok'
            })
          } else {
            Swal.fire({
              title: 'Error',
              text: 'Error when trying to update price!',
              icon: 'error',
              confirmButtonText: 'Back'
            })
          }
        })
    }
  }
};
</script>

<style>
</style>