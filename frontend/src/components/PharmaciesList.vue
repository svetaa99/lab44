<template>
  <div>
		<h1>List of pharmacy</h1>

		<div class="input-group">
			<div class="form-outline">
				<input type="search" id="form1" class="form-control" v-model="searchName"/>
			</div>
			<button type="button" class="btn btn-primary" v-on:click="searchPharmacy()">
				<i class="fas fa-search">Pretrazi</i>
			</button>
		</div>

    <input type="checkbox" id="A" v-model="filter.type.A">
    <label for="A">A</label>
    <button class="btn btn-primary" v-on:click = "filterPharmacy()">Filtriraj</button>

		<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Address</th>
          <th class="th-sm">Description</th>
          <th class="th-sm">Rating</th>
        </tr>
			</thead>
			<tbody>
				<tr v-for="pharmacy in pharmacies" :key="pharmacy.id">
					<td>{{pharmacy.name}}</td>
					<td>{{pharmacy.address}}</td>
          <td>{{pharmacy.description}}</td>
          <td>{{pharmacy.rating}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PharmaciesList',
  data () {
    return {
      pharmacies: [],
      searchName: '',
      filter: {
        type: {
            A: false,
            B: false,
            C: false,
            D: false,
            F: false,
        }
      },
    }
  },
  mounted () {
    axios
				.get('http://localhost:8000/pharmacies/all')
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies)})
  },
  methods: {
    searchPharmacy: function() {
      if(this.searchName == "")
			{
				axios
				.get('http://localhost:8000/pharmacies/all')
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies)})
			}
			else
			{
				axios
				.get(`http://localhost:8000/pharmacies/${this.searchName}`)
				.then(response => {this.pharmacies = response.data; console.log(this.pharmacies);})
			}
    }
  }
}
</script>

<style>

</style>