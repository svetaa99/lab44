<template>
  <div class="card shadow-sm">
    <img
      class="pharmacy-image"
      src="https://image.freepik.com/free-vector/pharmacy-background-design_1300-137.jpg"
      alt=""
    />
    <div class="card-body">
      <h4>{{ pharmacy.name }}</h4>
      <p class="card-text">This is pharmacy.</p>
      <div class="d-flex justify-content-between align-items-center">
        <p class="card-text">
          {{ pharmacy.address.street }} {{ pharmacy.address.number }}, {{ pharmacy.address.city }}
        </p>
        <p class="card-text">Rating: {{ pharmacy.rating }}</p>
      </div>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <router-link
            :to="`/pharmacies/${pharmacy.id}`"
            class="btn btn-sm btn-outline-secondary"
            >View</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["pharmacy"],
};
</script>

<style>
.pharmacy-image {
  height: 270px;
  width: 100%;
  overflow: hidden;
}
</style>