Vue.component('patients-list', {
    data : function(){
		return{
			patients: []
		}
	},
    /*
    Iterate through patients list received upon loading page using v-for
    Display basic information about patients in table
    */
	template:
	`<div>
	<h1>List of patients</h1>
	<table style="table-layout: fixed; width: 100%">
	<tr bgcolor = "cyan"><th style="width:25%">Name</th><th style="width:25%">Surname</th><th style="width:25%">Address</th><th style="width:25%">Category</th></tr>
		<tr v-for="patient in patients">
			<td style="width:25%">{{patient.name}}</td>
			<td style="width:25%">{{patient.surname}}</td>
			<td style="width:25%">{{patient.address}}</td>
			<td style="width:25%">{{patient.category}}</td>
		</tr>
	</table>
	</div>`,
	//Send request upon loading page
	mounted: function(){
		axios
			.get('http://localhost:8080/patients/all')
			.then(response => {this.patients = response.data; console.log(this.patients)})
	}
  })
  