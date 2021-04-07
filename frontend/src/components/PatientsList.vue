<template>
    <div>
		<h1>List of patients</h1>

		<div class="input-group">
			<div class="form-outline">
				<input type="search" id="form1" class="form-control" v-model="searchName"/>
			</div>
			<button type="button" class="btn btn-primary" v-on:click="searchPatients()">
				<i class="fas fa-search"></i>
			</button>
		</div>

		<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>
                <tr>
				<th class="th-sm" v-on:click="sortPatients('name',(sortOrder++)%2)">Name
				</th>
				<th class="th-sm" v-on:click="sortPatients('surname',(sortOrder++)%2)">Surname
				</th>
				<th class="th-sm">Address
				</th>
				<th class="th-sm" v-on:click="sortPatients('category',(sortOrder++)%2)">Category
				</th>
                <th class="th-sm" v-on:click="sortPatients('date',(sortOrder++)%2)">Date
                </th>
            </tr>
			</thead>
			<tbody>
				<tr v-for="patient in patients" :key="patient.id">
					<td>{{patient.name}}</td>
					<td>{{patient.surname}}</td>
					<td>{{patient.address}}</td>
					<td>{{patient.category}}</td>
                    <td>{{formatDate(patient.date)}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
import axios from 'axios'
export default ({
    
    name: "PatientsList",
    props: {
        patients: Array,
    },
    data() {
        return {
            searchName: '',
            lastSearchRes: 'no-search',
            sortOrder: 0,
            sortColumn: '',
        }
    },
    methods: { 
        formatDate: function(dateInJson){
            if(dateInJson == undefined){
                return "-"
            }
            return "" + dateInJson.day.toLocaleString('en-US', {
                minimumIntegerDigits: 2,
                useGrouping: false
            }) + "." + dateInJson.month.toLocaleString('en-US', {
                minimumIntegerDigits: 2,
                useGrouping: false
            }) + "." + dateInJson.year + "." 
         },
        searchPatients: function(){ 
            if(this.searchName == "") { 
                axios 
                .get('http://localhost:8000/patients/all') 
                .then(response => {this.patients = response.data; console.log(this.patients)}) 
            } 
            else { 
                axios 
                .get(`http://localhost:8000/patients/${this.searchName}`) 
                .then(response => {this.patients = response.data; console.log(this.patients); this.lastSearchRes = this.searchName;}) 
            } 
        } ,
        sortPatients: function(param, order){
            axios
            .get(`http://localhost:8000/patients/${param}/${order}/${this.lastSearchRes}`)
            .then(response => {this.patients = response.data; console.log(response.data);})
        }
    }, 
    mounted: function(){ 
        axios 
        .get('http://localhost:8000/patients/all') 
        .then(response => {this.patients = response.data; console.log(this.patients)}) 
    }
})
</script>

<style>

</style>
