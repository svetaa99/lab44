<template>
    <div class="container">
    <div class="main-body">
    
          <!-- Breadcrumb -->
          <!-- <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="index.html">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Reservation</li>
            </ol>
          </nav> -->
          <!-- /Breadcrumb -->
            <!-- User information -->
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                        <!-- Once we have information about user in session this information will not be static -->
                      <h4>Name Surname</h4>
                      <p class="text-secondary mb-1">filip.kresa@gmail.com</p>
                      <p class="text-muted font-size-sm">Address</p>
                      <button class="btn btn-outline-primary">Scheduled terms</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- Search form and table with free terms -->
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="input-group">
                    <input type="search" class="form-control rounded" placeholder="YYYY-MM-dd" aria-label="Search"
                        aria-describedby="search-addon" v-model="searchDateTimeObject.searchDate"/>
                    <input type="search" class="form-control rounded" placeholder="Time" aria-label="Search"
                        aria-describedby="search-addon" v-model="searchDateTimeObject.searchTime"/>
                    <button type="button" class="btn btn-outline-primary" v-on:click="searchDateTime()">Search</button>
                    </div>
                  </div>
                  <hr>
                  <table class="table">
                      <!-- Replace with v-for once you get data from server -->
                    <thead class="thead-dark">
                        <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Start</th>
                        <th scope="col">Finish</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="term in freeTerms" :key="term.id" v-on:click="selectTerm(term)" v-bind:class="{selected : selectedTerm == term}">
                          <td>{{formatDate(term.start.date)}}</td>
                          <td>{{formatTime(term.start.time)}}</td>
                          <td>{{formatTime(term.finish.time)}}</td>
                        </tr>
                    </tbody>
                    </table>
                    <hr>
                    <button class="btn btn-primary" v-on:click="newTermShow=true" id="newTermButton">New term</button>
                    <button type="button" class="btn btn-primary" :disabled='isDisabled' id="finishButton" v-on:click="makeReservation()">Finish</button>
                </div>
                </div>
                <div class="card mb-3">
                    <div class="card-body" v-if="newTermShow" id="newTermDiv">
                        <b-form-datepicker id="datepicker-lg" size="lg" locale="en" v-model="newStartDate"></b-form-datepicker>
                        <hr>
                        <div class="mob"> <label class="text-grey mr-1">From</label> <input class="ml-1" type="time" name="from" v-model="newStartTime"> </div>
                        <hr>
                        <div class="mob mb-2"> <label class="text-grey mr-4">To</label> <input class="ml-1" type="time" name="to" v-model="newFinishTime"> </div>
                        <hr>
                        <button type="button" class="btn btn-success" v-on:click="saveNewTerm()">Save</button> <button type="button" class="btn btn-outline-danger" id="cancelButton" v-on:click="newTermShow=false">Cancel</button>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "DoctorReservation",
    props: {
        freeTerms: Array,
    },
    data() {
        return {
            newTermShow: false,
            patientToken: '',  //this data will be hardcoded for now because there is no authentification ATM
            doctorToken: '',
            pharmacyId: '',
            newStartDate: '',
            newStartTime: '', //these attributes will be selected by doctor
            newFinishTime: '',
            selectedTerm: null,
            searchDateTimeObject : {
              searchDate: '',
              searchTime: '',
            }
        }
    },
    computed: {
      isDisabled: function(){
        return this.selectedTerm==null;
      }
    },
    methods : {
      formatDate: function(dateInJson){
        return "" + dateInJson.day.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "." + dateInJson.month.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "." + dateInJson.year + "." 
      },
      formatTime: function(timeInJson){
        return "" + timeInJson.hour.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + ":" + timeInJson.minute.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          })
      },
      formatDateTimeForReq: function(dateTimeJson){
        return "" + dateTimeJson.date.year + "-" 
        + dateTimeJson.date.month.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "-" 
        + dateTimeJson.date.day.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + "T" 
        + dateTimeJson.time.hour.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          }) + ":" 
        + dateTimeJson.time.minute.toLocaleString('en-US', {
            minimumIntegerDigits: 2,
            useGrouping: false
          })
      },
      selectTerm: function(tableTerm){
        this.selectedTerm = tableTerm;
      },
      saveNewTerm: function(){
        //axios request to write new term into database
        var newTerm = {doctorId: 1, pharmacyId: 1, start: (this.newStartDate + "T" + this.newStartTime), finish: (this.newStartDate + "T" + this.newFinishTime)}  //for now values for doctor id and pharmacy id will be hardcoded, once login is implemented these information will be extracted from session
        axios
        .post('http://localhost:8000/doctorterms/createnew', newTerm)
        .then(response => {this.handleResponse(response.data)})
      },
      handleResponse: function(respData){
        respData == "Taken term" ? alert("Term is taken!") : respData == "Not in your working hours" ? alert("Not in your working hours") : this.freeTerms = respData;
      },
      makeReservation: function(){
        //dialog for are you sure first - once clicked yes action preforms
        var newReservation = {patientId: 1, doctorId: 1, start: this.formatDateTimeForReq(this.selectedTerm.start), finish: this.formatDateTimeForReq(this.selectedTerm.finish)}
        axios
        .post('http://localhost:8000/appointments/make-appointment', newReservation)
        .then(response => {response.data === "Patient unavailable" ? alert("Patient is unavailable in given term!") : alert("New appointment added to work calendar!");})
      },
      searchDateTime: function(){
        console.log(this.searchDateTimeObject.searchDate + " " + this.searchDateTimeObject.searchTime);
        axios
        .post('http://localhost:8000/doctorterms/search-date-time', this.searchDateTimeObject)
        .then(response => {this.freeTerms = response.data})
      }
    },
    mounted: function(){
        //TO DO: axios call to get all free terms for current DOCTOR
        axios 
        .get('http://localhost:8000/doctorterms/definedterms') 
        .then(response => {this.freeTerms = response.data; console.log(this.freeTerms)}) 
    }

}
</script>

<style>
#cancelButton{
    margin: 0 10px;
}
.selected{
  background-color:deepskyblue;
}
#finishButton{
  margin: 0 20px
}
#newTermButton{
  margin: 0 20px
}
</style>