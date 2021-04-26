<template>
  <div>
    <h3>Available medicines</h3>
    <medicines-list :medicines="this.medicines" @clicked="onRowDblClick"></medicines-list>

    <div class='selected-medicine-div'>
      Odbrani lek:
      <h5>{{this.selectedMedicine.name}}</h5>
      Kolicina:
      <div class="quantity">
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
      <div class="col-sm">
        <Datepicker placeholder="Datum" v-model="date"/>
      </div>
      <button type="button" class="btn btn-primary" v-on:click="addMedicine()">Add medicine to the order</button>
      <button type="button" class="btn btn-primary" v-on:click="finishOrder()">Finish order</button>
    </div>

    <h3>Selected medicines for order</h3>
    <table>
      <tr>
        <th>Lek</th>
        <th>Kolicina</th>
      </tr>
      <tr v-for="m in this.selectedMedicines" :key="m.medicineId">
        <td>{{m.medicineId}}</td>
        <td>{{m.quantity}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
import { config } from "@/config.js";
import MedicinesList from '@/components/MedicinesList.vue'
import Datepicker from "vuejs-datepicker";

const API_URL = config.API_URL;

export default {
  name: 'OrdersPage',
  components: {
    'medicines-list': MedicinesList,
    Datepicker,
  },
  data() {
    return {
      medicines: [],
      selectedMedicine: {},
      quantity: 0,
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
  },
  methods: {
    onRowDblClick(medicine) {
      this.selectedMedicine = medicine
    },
    addMedicine() {
      var medicineQuantity = {
        medicineId: this.selectedMedicine.id,
        quantity: parseInt(this.quantity),
      }

      this.selectedMedicines.push(medicineQuantity);
    },
    finishOrder() {
      const postObj = {
        id: 1, 
        orderMedicines: this.selectedMedicines,
        deadline: this.date.getTime()
      }

      console.log(postObj)

      axios
        .post(`${API_URL}/orders/create-order`, postObj)
        .then(response => {
          console.log(response.data)
        })
    }

  }

}
</script>

<style>

.selected-medicine-div {
  height: 20ex;
  align-items: center;
}

.quantity {
  display: inline;
  max-width: 100px;
}


</style>