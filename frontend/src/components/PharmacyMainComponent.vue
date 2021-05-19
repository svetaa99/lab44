<template>
  <div>
    <!-- Title -->
    <h1 class="mt-4 align-left">{{ pharmacy.name }}</h1>
    <p class="small align-left">{{ pharmacy.address.street }} {{ pharmacy.address.number }}, {{ pharmacy.address.city }}</p>
    <hr />
    <p class="align-left">{{ pharmacy.description }}</p>

    <!-- Preview Image -->
    <img
      class="main-pharmacy-image"
      src="https://image.freepik.com/free-vector/pharmacy-background-design_1300-137.jpg"
      alt=""
    />
  </div>
</template>

<script>
export default {
  props: ["pharmacy"],
};
</script>

<style>
.align-left {
  text-align: left;
}

.main-pharmacy-image {
  height: 550px;
  width: 100%;
  overflow: hidden;
}
</style>