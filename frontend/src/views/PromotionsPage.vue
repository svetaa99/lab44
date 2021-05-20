<template>
  <div class="container">
    <h1>Create new promotion</h1>
    <h3>Select available medicines</h3>
    <p>Double click on the row to select</p>
    <MedicinesList :medicines="allMedicines" @clicked="handleRowDblClick"/>

    <h3>Selected medicines</h3>
    <p>Double click on the row to delete</p>
    <MedicinesList :medicines="promotion.medicines" @clicked="handleDeleteDblClick"/>

    <hr/>

    <h3>Form to fill about promotion</h3>
    <textarea v-model="promotion.text" placeholder="Info about promotion"></textarea>

    <br/>

    <h3>Additional features:</h3>
    <br/>
    <div class="row justify-content-around padding-bottom">
      <div class="align-items-center">
        <label for="type">Type: </label>
        <select id="type" v-model="promotion.type">
          <option key=1>PROMOTION</option>
          <option key=2>ACTION</option>
        </select>
      </div>

      <div class="align-items-cente">
        <label for="discount">Discount: </label>
        <input type="number" v-model="promotion.discount" min=1 max=100>
      </div>
    </div>

    
    <h3>Period of validity</h3>
    <p>Select dates when this promotion will be active</p>
    <div class="row justify-content-around">
      <div class="lg-6">
        Start date:
        <Datepicker placeholder="Date" v-model="promotion.startDate"/><br/>
      </div>
      <div class="lg-6">
        End date:
        <Datepicker placeholder="Date" v-model="promotion.endDate"/>
      </div>
    </div>

    <br/>
    <button class="btn btn-primary" @click="handleCreateClick">Create new promotion</button>
    <br/>
  </div>
</template>

<script>
import MedicinesList from '../components/MedicinesList.vue'
import Datepicker from "vuejs-datepicker";

import axios from "axios";
import { config } from "@/config.js";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  components: { MedicinesList, Datepicker },
  data() {
    return {
      allMedicines: [],
      promotion: {
        medicines: [],
        text: "",
        type: -1,
        discount: 0,
        startDate: "",
        endDate: "",
      }
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        const admin = response.data

        axios
          .get(`${API_URL}/pharmacy-medicines/get-medicines/${admin.pharmacy.id}`)
          .then(response => {
            const pms = response.data
            pms.map(pm => {
              this.allMedicines.push(pm.medicine)
            })
          })
      })
  },
  methods: {
    handleRowDblClick(medicine) {
      const isMedicineInTheList = this.promotion.medicines.some(sm => sm === medicine)

      if (!isMedicineInTheList) {
        this.promotion.medicines.push(medicine)
      }
    },
    handleDeleteDblClick(medicine) {
      for (var i = 0; i < this.promotion.medicines.length; i++) {
        if (this.promotion.medicines[i] == medicine) {
          this.promotion.medicines.splice(i, 1);
        }
      }
    },
    handleCreateClick() {
      try {
        this.validateInputs()
        this.promotion.startDate = new Date(this.promotion.startDate).getTime()
        this.promotion.endDate = new Date(this.promotion.endDate).getTime()

        axios
          .post(`${API_URL}/promotions/create-promotion`, this.promotion)
          .then(response => {
            if (response.status === 201) {
              Swal.fire({
                title: 'Success',
                text: 'Successfully created and saved new promotion',
                icon: 'success'
              })
            }
          })
          .catch(err => {
            Swal.fire({
              title: `Error status: ${err.response.data.status}`,
              text: `${err.response.data.message}`,
              icon: 'error'
            })
          })

      } catch (error) {
        Swal.fire({
          title: 'Error',
          text: `${error.message}`,
          icon: 'error'
        })
      }
    },
    validateInputs() {
      if (Object.keys(this.promotion.medicines).length === 0) {
        throw Error("No medicine selected")
      } else if (this.promotion.text === "") {
        throw Error("No text info inputed")
      } else if (this.promotion.type === -1) {
        throw Error("No type selected")
      } else if (this.promotion.discount === 0) {
        throw Error("No discount set")
      } else if (this.promotion.startDate === "" || this.promotion.endDate === "") {
        throw Error("Empty date fields")
      } else if (new Date(this.promotion.startDate) > new Date(this.promotion.endDate)) {
        throw Error("Start date cannot be after end date")
      } else if (new Date(this.promotion.endDate) < new Date()) {
        throw Error("End date cannot be in the past")
      }
    }
  }
}
</script>

<style>
  textarea {
    width: 80%;
    height: 250px;
  }
</style>