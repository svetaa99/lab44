<template>
  <div>
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
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    doctors: Array,
    doctorType: String,
  },
  methods: {
    printPharmacies(doctor) {
      return (
        doctor.pharmacies.length === 0 ? "Not employed" :
          "" + doctor.pharmacies.map(p => {return " " + p.name})
      )
    }
  }
}
</script>

<style>

</style>