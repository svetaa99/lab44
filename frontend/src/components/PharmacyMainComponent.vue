<template>
  <div>
    <!-- Title -->
    <h1 class="mt-4 align-left">{{ pharmacy.name }}<a v-if="userRoles.includes(4)" class="btn btn-primary" :href="`/edit-pharmacy/${pharmacy.id}`">Edit pharmacy</a></h1> 
    <!-- <p class="small align-left">{{ pharmacy.address.street }} {{ pharmacy.address.number }}, {{ pharmacy.address.city }}</p> -->
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
  data() {
    return {
      userRoles: [],
    }
  },
  mounted() {
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));
    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });
  },
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