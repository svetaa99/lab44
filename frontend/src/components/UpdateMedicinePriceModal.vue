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
          <div class="container">
            <div class="row">
              <div class="col col-lg-12">
                <h3>{{pharmacyMedicine.medicine.name}}</h3>
              </div>
            </div>
            <div class="row justify-content-md-center">
              <div class="col col-lg-12">
                Price:
                <input
                  type="number"
                  placeholder="Price"
                  min="1"
                  v-model="pharmacyMedicine.price"
                >
              </div>
            </div> <br/>
            <div class="row">
              <div class="col col-lg-6">
                Start date:
                <Datepicker placeholder="Date" v-model="pharmacyMedicine.startDate"/>
              </div>
              <div class="col col-lg-6">
                End date:
                <Datepicker placeholder="Date" v-model="pharmacyMedicine.endDate"/>
              </div>
            </div> <br/>
            <button
              type="button"
              class="btn btn-primary"
              @click="handleUpdateClick"
            >
              Update
            </button> <br/><br/>
            <button
              type="button"
              class="btn btn-outline-danger"
              @click="handleDeleteClick"
            >
              Delete medicine
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import Datepicker from "vuejs-datepicker";
import axios from 'axios'
import { config } from '@/config.js'

const API_URL = config.API_URL

export default {
  components: {
    Datepicker,
  },
  props: {
    pharmacyMedicine: Object
  },
  methods: {
    handleUpdateClick() {
      const startDate = this.pharmacyMedicine.startDate.constructor.name == "Number" ? 
          this.pharmacyMedicine.startDate : this.pharmacyMedicine.startDate.getTime()

      const endDate = this.pharmacyMedicine.endDate.constructor.name == "Number" ? 
          this.pharmacyMedicine.endDate : this.pharmacyMedicine.endDate.getTime()

      const putObj = {
        pharmacyId: this.pharmacyMedicine.pharmacy.id,
        medicineId: this.pharmacyMedicine.medicine.id,
        price: this.pharmacyMedicine.price,
        quantity: this.pharmacyMedicine.quantity,
        startDate: startDate,
        endDate: endDate,
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
        .catch(err => {
            Swal.fire({
              title: 'Error', 
              text: `${err.response.data}`,
              icon: 'error'
            })
        })
    },
    handleDeleteClick() {
      Swal.fire({
        title: 'Are you sure?',
        text: 'You won\'t be able to undo this operation. Medicine will be deleted permanently.',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
      }).then(result => {
        if (result.isConfirmed) {
          const deleteObj = {
            pharmacyId: this.pharmacyMedicine.pharmacy.id, 
            medicineId: this.pharmacyMedicine.medicine.id
          }
          console.log(deleteObj)
          axios
            .delete(`${API_URL}/pharmacies/delete-medicine`, {data: deleteObj})
            .then(response => {
              if (response.status === 200) {
                Swal.fire({
                  title: 'Success',
                  text: 'Successfully deleted medicine!',
                  icon: 'success',
                  confirmButtonText: 'Ok'
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
};
</script>

<style>
</style>