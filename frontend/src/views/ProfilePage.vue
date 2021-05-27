<template>
  <ProfileComponent />
</template>

<script>
import ProfileComponent from "../components/ProfileComponent.vue";
export default {
  name: "ProfilePage",
  components: {
    ProfileComponent,
  },
  data: () => {
    return {
      password: '',
    };
  },
  beforeRouteLeave(to, from, next) {
    this.password = localStorage.getItem('pw');
    if (this.password === "chang3m3") {
      alert("Cannot redirect until you change password!");
    } else {
      next();
    }
  }
};
</script>

<style>
</style>