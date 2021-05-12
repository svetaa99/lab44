<template>
  <div>
    <update-doctor-work-time-modal :pharmacist="selectedDoctor"></update-doctor-work-time-modal>

    <table width="80%" class="table table-striped table-bordered table-sm">
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Surname</th>
          <th class="th-sm">Email</th>
          <th v-if="doctorType == 'pharmacist'">Rating</th>
          <th v-if="doctorType == 'pharmacist'">Pharmacy name</th>
          <th v-if="doctorType == 'dermatologist'">Pharmacies</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="d in doctors" :key="d.id">
          <td>{{d.name}}</td>
          <td>{{d.surname}}</td>
          <td>{{d.email}}</td>
          <td v-if="doctorType == 'pharmacist'">{{d.rating}}</td>
          <td v-if="doctorType == 'pharmacist'">{{d.pharmacy.name}}</td>
          <td v-if="doctorType == 'dermatologist'">{{printPharmacies(d)}}</td>
          <td v-if="userRoles.includes(4)">
            <button 
              type="button"
              class="btn btn-primary" 
              data-toggle="modal"
              data-target="#updateDoctorModal"
              @click="handleUpdateClick(d)">Update</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import UpdateDoctorWorkTimeModal from './UpdateDoctorWorkTimeModal.vue';

export default {
  components: {
    UpdateDoctorWorkTimeModal,
  },
  props: {
    doctors: Array,
    doctorType: String,
  },
  data() {
    return {
      userRoles: [],
      edit: false,
      selectedDoctor: {
        name: '',
        surname: '',
        email: '',
        phoneNum: '',
        address: '',
        startTime: '',
        finishTime: '',
      },
    }
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });
  },
  methods: {
    printPharmacies(doctor) {
      return (
        doctor.pharmacies.length === 0 ? "Not employed" :
          "" + doctor.pharmacies.map(p => {return " " + p.name})
      )
    },
    handleUpdateClick(doctor) {
      this.selectedDoctor = doctor
    }
  }
}
</script>

<style>

</style>