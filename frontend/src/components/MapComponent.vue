<template>
  <div class="container">
    <div id="map">

    </div>
  </div>
</template>

<script>

const access_token = 'pk.eyJ1Ijoic3ZldGFhIiwiYSI6ImNra3BzZjkzeTJoMXQycXRkbmRlbWxmbngifQ.XtANzSax6vFWwmkr5WoCIg'

export default {
  /* eslint-disable no-undef */ 
  /* eslint-disable no-unused-vars */ // do not delete these comments!!!
  props: {
    address: Object,
  },
  mounted() {
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

    var marker = L.marker([latitude, longitude]).addTo(mymap);
    marker.bindPopup(`<b>Adresa:</b><br>${street}, ${number}, ${city}, ${country}`).openPopup();
  }
}
</script>

<style>

  #map {
    height: 300px;
  }

</style>