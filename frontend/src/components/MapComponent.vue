<template>
  <div class="container">
    <div id="map">
    </div>
    <br/>
    <button class="btn btn-primary" type="button" v-if="userRoles.includes(4) && edit" @click="handleSaveClick">Save new address</button>
  </div>
</template>

<script>

const access_token = 'pk.eyJ1Ijoic3ZldGFhIiwiYSI6ImNra3BzZjkzeTJoMXQycXRkbmRlbWxmbngifQ.XtANzSax6vFWwmkr5WoCIg'
var marker = null;  // global marker attribute, it will be accessed inside geoLocation function
var map = null; // global map attribute, it will be accessed inside geoLocation function

export default {
  /* eslint-disable no-undef */ 
  /* eslint-disable no-unused-vars */ // do not delete these comments!!!
  props: {
    address: Object,
    edit: Boolean,
  },
  data() {
    return {
      userRoles: [],
      newAddress: {},
    }
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });

    const {street, number, city, country, latitude, longitude} = this.address;
    var mymap = L.map('map').setView([latitude, longitude], 15); 

    L.tileLayer(`https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=${access_token}`, {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: `${access_token}`
    }).addTo(mymap);

    marker = L.marker([latitude, longitude]).addTo(mymap);
    marker.bindPopup(`<b>Adresa:</b><br>${street}, ${number}, ${city}, ${country}`).openPopup();

    map = mymap
    map.addressObj = {
      street: "",
      number: 0,
      city: "",
      country: "",
      latitude: 0,
      longitude: 0,
    }

    if (this.userRoles.includes(4) && this.edit) {
      var geocodeService = L.esri.Geocoding.geocodeService();
      
      map.on('click', function(e) {
        geocodeService.reverse().latlng(e.latlng).run(function(error, result) {
          if(error) {
            return;
          }

          if (marker != undefined) {
            map.removeLayer(marker)
          }

          marker = L.marker(result.latlng).addTo(map).bindPopup(result.address.Match_addr).openPopup();
          
          map.addressObj = {
            street: result.address.Address,
            number: result.address.AddNum === "" ? 0 : parseInt(result.address.AddNum),
            city: result.address.City,
            country: result.address.CountryCode,
            longitude: parseFloat(result.latlng.lng),
            latitude: parseFloat(result.latlng.lat),
          }
        })
      })
    }
  },
  methods: {
    handleSaveClick() {
      this.$emit('clicked', map.addressObj)
    }
  }
}
</script>

<style>

  #map {
    height: 300px;
  }

</style>