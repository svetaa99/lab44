<template>
  <div class="container">
    <h1>List of patients</h1>
    <br>
    <div class="container" align="center" style="width: 350px">
      <div class="row" align="center">
        <div class="input-group">
          <div class="form-outline">
            <input
              type="search"
              id="form1"
              class="form-control"
              placeholder="Name"
              v-model="searchName"
            />
          </div>
          <button
            type="button"
            class="btn btn-primary"
            v-on:click="searchPatients()"
          >
            <i class="fas fa-search">Search</i>
          </button>
        </div>
      </div>
    </div>
    <br>
    <table
      id="dtBasicExample"
      class="table table-striped table-bordered table-sm"
      cellspacing="0"
      width="100%"
    >
      <thead>
        <tr>
          <th class="th-sm" v-on:click="sortPatients('name', sortOrder++ % 2)">
            Name
          </th>
          <th
            class="th-sm"
            v-on:click="sortPatients('surname', sortOrder++ % 2)"
          >
            Surname
          </th>
          <th class="th-sm">Address</th>
          <th
            class="th-sm"
            v-on:click="sortPatients('category', sortOrder++ % 2)"
          >
            Category
          </th>
          <th class="th-sm" v-on:click="sortPatients('date', sortOrder++ % 2)">
            Date
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="patient in patients" :key="patient.id">
          <td>{{ patient.name }}</td>
          <td>{{ patient.surname }}</td>
          <td>{{ patient.address }}</td>
          <td>{{ patient.category }}</td>
          <td>{{ formatDate(patient.date) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
import { config } from "@/config.js";
const API_URL = config.API_URL;

export default {
  name: "PatientsList",
  data() {
    return {
      searchName: "",
      lastSearchRes: "no-search",
      sortOrder: 0,
      sortColumn: "",
      patients: [],
    };
  },
  methods: {
    formatDate: function (dateInJson) {
      if (dateInJson == undefined) {
        return "-";
      }
      return (
        "" +
        dateInJson.day.toLocaleString("en-US", {
          minimumIntegerDigits: 2,
          useGrouping: false,
        }) +
        "." +
        dateInJson.month.toLocaleString("en-US", {
          minimumIntegerDigits: 2,
          useGrouping: false,
        }) +
        "." +
        dateInJson.year +
        "."
      );
    },
    searchPatients: function () {
      if (this.searchName == "") {
        axios.get(`${API_URL}/patients/all`).then((response) => {
          this.patients = response.data;
        });
      } else {
        axios
          .get(`${API_URL}/patients/${this.searchName}`)
          .then((response) => {
            this.patients = response.data;
            this.lastSearchRes = this.searchName;
          });
      }
    },
    sortPatients: function (param, order) {
      axios
        .get(
          `${API_URL}/patients/${param}/${order}/${this.lastSearchRes}`
        )
        .then((response) => {
          this.patients = response.data;
        });
    },
  },
  mounted: function () {
    axios.get(`${API_URL}/patients/all`).then((response) => {
      this.patients = response.data;
    });
  },
};
</script>

<style>
</style>
