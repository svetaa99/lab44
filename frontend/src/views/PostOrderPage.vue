<template>
  <div>
    <div class="padding-bottom">
      <h3>Available medicines</h3>
      <medicines-list :medicines="this.medicines" @clicked="onRowDblClick"></medicines-list>

    </div>

    <div class='container'>
      <div>
        <h4>
          Selected medicine:
        </h4>
        <h5>{{this.selectedMedicine.name}}</h5>
      </div>
      <div class='row justify-content-md-center align-items-center padding-bottom'>
        <h6>
          Quantity:
        </h6>
        <div class="col col-lg-2">
          <input
                placeholder="Kolicina"
                type="number"
                class="form-control"
                id="quantity"
                name="quantity"
                min="1"
                v-model="quantity"
              />
        </div>
        <div class="col col-lg-4">
          <button type="button" class="btn btn-primary" v-on:click="addMedicine()">Add medicine to the order</button>
        </div>
      </div>
    </div>
    <div class='container'>
      <div class='row justify-content-md-center'>
            <h3>Selected medicines for order</h3>
      </div>
      <div class="row justify-content-md-center">
        <table>
          <tr>
            <th>Medicine name</th>
            <th>Quantity</th>
          </tr>
          <tr v-for="m in this.selectedMedicines" :key="m.medicineId">
            <td>{{m.medicine.name}}</td>
            <td>{{m.quantity}}</td>
            <td><button class="btn btn-primary" v-on:click="handleDeleteClick(m)">Delete</button></td>
          </tr>
        </table>
      </div>
      <div class='row padding-bottom'>
          <div class="col">
            <h5>Deadline:</h5>
            <Datepicker class="text-center" placeholder="Date" v-model="date"/>
          </div>
      </div>
      <div>
          <button type="button" class="btn btn-primary" v-on:click="finishOrder()">Finish order</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from "@/config.js";
import MedicinesList from '@/components/MedicinesList.vue'
import Datepicker from "vuejs-datepicker";
import Swal from 'sweetalert2'

const API_URL = config.API_URL;

export default {
  name: 'PostOrderPage',
  components: {
    'medicines-list': MedicinesList,
    Datepicker,
  },
  data() {
    return {
      medicines: [],
      selectedMedicine: {
        name: "Medicine not selected"
      },
      admin: {},
      quantity: 1,
      date: "",
      selectedMedicines: [],
    }
  },
  mounted() {
    axios
      .get(`${API_URL}/medicines/all`)
      .then(response => {
        this.medicines = response.data
      })

    axios
      .get(`${API_URL}/labadmins/registered-admin`)
      .then(response => {
        this.admin = response.data;
      })
  },
  methods: {
    onRowDblClick(medicine) {
      this.selectedMedicine = medicine
    },
    addMedicine() {
      var medicineQuantity = {
        medicine: this.selectedMedicine,
        quantity: parseInt(this.quantity),
      }

      this.selectedMedicines.push(medicineQuantity);
    },
    finishOrder() {

      if (this.selectedMedicines.length === 0) {
        Swal.fire({
          title: 'Error',
          text: 'Medicine list for order is empty!',
          icon: 'error',
          confirmButtonText: 'Back'
        })
      } else if (this.date === "") {
        Swal.fire({
          title: 'Error',
          text: 'Selected date is empty!',
          icon: 'error',
          confirmButtonText: 'Back'
        })
      } else {
          const postObj = {
            id: 1,
            pharmacy: this.admin.pharmacy,
            orderMedicines: this.selectedMedicines,
            deadline: this.date.getTime()
          }

          axios
            .post(`${API_URL}/orders/create-order`, postObj)
            .then(response => {
              if (response.status === 200) {
                Swal.fire({
                  title: 'Success!',
                  text: 'Successfully posted order.',
                  icon: 'success',
                })
              } else if (response.status === 400) {
                Swal.fire({
                  title: 'Error!',
                  text: 'Error when trying to post order.',
                  icon: 'order',
                })
              }
            })
      }
    },
    handleDeleteClick(medicine) {
      for (var i = 0; i < this.selectedMedicines.length; i++) {
        if (this.selectedMedicines[i] == medicine) {
          this.selectedMedicines.splice(i, 1);
        }
      }
    }
  }

}
</script>

<style>

.selected-medicine-div {
  height: 30ex;
  align-items: center;
}

.padding-bottom {
  padding-bottom: 5ex;
}

.text-center {
  text-align: center;
}



</style>