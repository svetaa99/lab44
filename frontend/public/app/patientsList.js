Vue.component('patients-list', {
    data : function(){
		return{
			patients: [],
			searchName: "Filip"
		}
	},
    /*
    Iterate through patients list received upon loading page using v-for
    Display basic information about patients in table
    */
	template:
	`<div>
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
				<tr v-for="patient in patients">
					<td>{{patient.name}}</td>
					<td>{{patient.surname}}</td>
					<td>{{patient.address}}</td>
					<td>{{patient.category}}</td>
				</tr>
			</tbody>
		</table>
	</div>`,
	//Send request upon loading page
	mounted: function(){
		axios
			.get('http://localhost:8080/patients/all')
			.then(response => {this.patients = response.data; console.log(this.patients)})
	},
	methods: {
		searchPatients: function(){
			if(this.searchName == "")
			{
				axios
				.get('http://localhost:8080/patients/all')
				.then(response => {this.patients = response.data; console.log(this.patients)})
			}
			else
			{
				axios
				.get(`http://localhost:8080/patients/${this.searchName}`)
				.then(response => {this.patients = response.data; console.log(this.patients);})
			}
			
		}
	}
  })
  