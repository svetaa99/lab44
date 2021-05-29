<template>
  <div>
        <table
        id="dtBasicExample"
        class="table table-striped table-bordered table-sm"
        cellspacing="0"
        width="100%"
        >
            <thead>
                <tr>
                <th class="th-sm">Name</th>
                <th class="th-sm">Surname</th>
                <th class="th-sm">Date</th>
                <th class="th-sm">Time</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="sVisit in scheduledVisits" :key="sVisit.id">
                <td>{{ sVisit.patient.name }}</td>
                <td>{{ sVisit.patient.surname }}</td>
                <td>{{ formatDate(sVisit.start) }}</td>
                <td>{{ formatTime(sVisit.start) }}</td>
                <td><a :href="'http://localhost:8080/employee-appointments/' + sVisit.id" v-if="isTime(sVisit)">Start</a></td>
                </tr>
            </tbody>
        </table>
  </div>
</template>

<script>
import axios from 'axios';

import { config } from "@/config.js";
const API_URL = config.API_URL;

export default {
    name: "DoctorScheduledVisits",
    props: {
        scheduledVisits: Array,
    },
    data(){
        return {
        };
    },
    methods: {
        isTime: function(visit){
            var currentTime = new Date();
            var startingTime = new Date(visit.start);
            if(currentTime > startingTime){
                return true;
            }
            return false;
        },
        formatDate(dateInStr){
            var dateTokens = dateInStr.split("T")[0].split("-");
            return dateTokens[2] + "-" + dateTokens[1] + "-" + dateTokens[0]
        },
        formatTime(timeInStr){
            var timeTokens = timeInStr.split("T")[1].split(":");
            return timeTokens[0] + ":" + timeTokens[1]
        }
    },
    mounted: function() {
        axios.get(`${API_URL}/appointments/td`).then((response) => {
        this.scheduledVisits = response.data;
    });
    },
    beforeRouteEnter (to, from, next) {
      const tokenItem = JSON.parse(localStorage.getItem('jwt'));
      var flag = false;
      if(tokenItem == null)
        next('/');
      tokenItem.token.roles.forEach(role => {
          if(role.id == 2 || role.id == 3)
            flag = true;
      })
      if(!flag)
        next('/');
      else
        next();
    }
}
</script>

<style>

</style>