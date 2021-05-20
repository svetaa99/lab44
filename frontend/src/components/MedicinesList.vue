<template>
  <div>
    <update-medicine-price-modal
      :pharmacyMedicine="pharmacyMedicine"
    ></update-medicine-price-modal>

    <div class="container">
      <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="100%"
      >
        <thead>
          <tr>
            <th class="th-sm">Name</th>
            <th class="th-sm">Type</th>
            <th class="th-sm">Specification</th>

            <th v-if="extra == ''"></th>
            <th v-if="extra == 'quantity'">Quantity</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="m in this.medicines"
            :key="m.id"
            v-on:dblclick="handleRowDblClick(m)"
          >
            <td>{{ m.name }}</td>
            <td>{{ m.type }}</td>
            <td>{{ m.specification }}</td>
            <td v-if="extra == 'add'">
              <router-link :to="`/medicines/${m.id}`"> Choose </router-link>
            </td>
            <td v-if="extra == 'update' && userRoles.includes(4)">
              <button
                type="button"
                class="btn btn-primary"
                data-toggle="modal"
                data-target="#updateMedicinePriceModal"
                @click="handleUpdateClick(m)"
              >
                Update
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import UpdateMedicinePriceModal from "./UpdateMedicinePriceModal.vue";
import axios from "axios";
import { config } from "@/config.js";
import Swal from 'sweetalert2';

const API_URL = config.API_URL;

export default {
  name: "MedicinesList",
  components: {
    "update-medicine-price-modal": UpdateMedicinePriceModal,
  },
  props: {
    medicines: Array,
    extra: String,
  },
  data() {
    return {
      pharmacyMedicine: {
        medicine: {
          name: "",
        },
      },
      userRoles: [],
    };
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });
  },
  methods: {
    handleRowDblClick(medicine) {
      this.$emit("clicked", medicine);
    },

    handleInputOnChange() {
      this.$emit("change", this.quantity);
    },

    handleUpdateClick(medicine) {
      axios.get(`${API_URL}/labadmins/registered-admin`).then((response) => {
        const admin = response.data;
        axios
          .get(
            `${API_URL}/pharmacy-medicines/get-by-ids/${admin.pharmacy.id}/${medicine.id}`
          )
          .then((response) => {
            this.pharmacyMedicine = response.data;
          });
      });
    },
  },
};
</script>

<style>
</style>