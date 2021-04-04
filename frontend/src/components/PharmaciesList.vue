<template>
  <div>
		<h1>List of pharmacy</h1>
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
          <td>
            <router-link
              :to="`/pharmacies/${pharmacy.id}`"
            >
            Prikazi
            </router-link>
          </td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
import { config } from '@/config.js'

const api_url = config.API_URL 

export default {
  name: 'PharmaciesList',
  props: {
    pharmacies: Array
  },
  data () {
    return {
      API_URL: api_url,
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
  methods: {
    showPharmacy () {

    }
  }
}
</script>

<style>

</style>