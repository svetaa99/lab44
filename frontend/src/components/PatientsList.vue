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
				<th class="th-sm">Name
				</th>
				<th class="th-sm">Surname
				</th>
				<th class="th-sm">Address
				</th>
				<th class="th-sm">Category
				</th>
            </tr>
			</thead>
			<tbody>
				<tr v-for="patient in patients" :key="patient.id">
					<td>{{patient.name}}</td>
					<td>{{patient.surname}}</td>
					<td>{{patient.address}}</td>
					<td>{{patient.category}}</td>
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
            searchName: ''
        }
    },
    methods: { 
        searchPatients: function(){ 
            if(this.searchName == "") { 
                axios 
                .get('http://localhost:8000/patients/all') 
                .then(response => {this.patients = response.data; console.log(this.patients)}) 
            } 
            else { 
                axios 
                .get(`http://localhost:8000/patients/${this.searchName}`) 
                .then(response => {this.patients = response.data; console.log(this.patients);}) 
            } 
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
