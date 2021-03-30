Vue.component('pharmacy-list', {
    data : function(){
		return{
			pharmacys: [],
			searchName: "Pharmacy",
            filter: {
                type: {
                    A: false,
                    B: false,
                    C: false,
                    D: false,
                    F: false,
                }
            }
		}
	},
    /*
    Iterate through patients list received upon loading page using v-for
    Display basic information about patients in table
    */
	template:
	`<div>
		<h1>List of pharmacy</h1>

		<div class="input-group">
			<div class="form-outline">
				<input type="search" id="form1" class="form-control" v-model="searchName"/>
			</div>
			<button type="button" class="btn btn-primary" v-on:click="searchPharmacy()">
				<i class="fas fa-search"></i>
			</button>
		</div>

        <div class="input-group">
			<div class="form-outline">
				<input type="search" id="form1" class="form-control" v-model="searchName"/>
			</div>
			<button type="button" class="btn btn-primary" v-on:click="searchPharmacy()">
				<i class="fas fa-search"></i>
			</button>
		</div>

        <input type="checkbox" id="A" v-model="filter.type.A">
        <label for="A">A</label>
        <button class="btn btn-primary" v-on:click = "filterPharmacy()">Filtriraj</button>

		<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
  			<thead>
			  <tr>
				<th class="th-sm">Name
				</th>
				<th class="th-sm">Address
				</th>
			  </tr>
			</thead>
			<tbody>
				<tr v-for="pharmacy in pharmacys">
					<td>{{pharmacy.name}}</td>
					<td>{{pharmacy.address}}</td>
				</tr>
			</tbody>
		</table>
	</div>`,
	//Send request upon loading page
	mounted: function(){
		axios
			.get('http://localhost:8080/pharmacies/all')
			.then(response => {this.pharmacys = response.data; console.log(this.pharmacys)})
	},
	methods: {
		searchPharmacy: function(){
			if(this.searchName == "")
			{
				axios
				.get('http://localhost:8080/pharmacies/all')
				.then(response => {this.pharmacys = response.data; console.log(this.pharmacys)})
			}
			else
			{
				axios
				.get(`http://localhost:8080/pharmacy/${this.searchName}`)
				.then(response => {this.pharmacys = response.data; console.log(this.pharmacys);})
			}
		},
        filterPharmacy: function() {
            const typeString = `${this.filter.type.A}+${this.filter.type.B}+${this.filter.type.C}+${this.filter.type.D}+${this.filter.type.F}`;
       
            const filterObj = {
              type: typeString,
            }
       
            this.$emit('clicked', filterObj);
        }
	}
  })
  