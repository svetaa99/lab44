<template>
  <div class="container">
		<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      <thead>
        <tr>
          <th class="th-sm">Name</th>
          <th class="th-sm">Address</th>
          <th class="th-sm">Rating</th>
          <th class="th-sm" v-if="!appointment">Description</th>
          <th class="th-sm" v-if="appointment">Price</th>
        </tr>
			</thead>
			<tbody>
				<tr v-for="pharmacy in pharmacies" :key="pharmacy.id">
					<td>{{pharmacy.name}}</td>
					<td>{{pharmacy.addressId}}</td>
          <td>{{pharmacy.rating}}</td>
          <td v-if="!appointment">{{pharmacy.description}}</td>
          <td v-if="appointment">{{pharmacy.pharmacistPrice}}</td>
          <td>
            <router-link v-if="!appointment"
              :to="`/pharmacies/${pharmacy.id}`"
            >
            Prikazi
            </router-link>
            <router-link v-if="appointment"
              :to="`/pharmacies/${pharmacy.id}/pharmacists`"
            >
            Choose
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
    pharmacies: Array,
    appointment: Boolean,
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