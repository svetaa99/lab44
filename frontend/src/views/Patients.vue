<template>
  <div>
      <PatientsList/>
  </div>
</template>

<script>
import PatientsList from '../components/PatientsList.vue'
export default {
    name: "employee-patients",
    components: { PatientsList },

    beforeRouteEnter (to, from, next) {
      const tokenItem = JSON.parse(localStorage.getItem('jwt'));
      var flag = false;
      if(tokenItem == null)
        next('/');
      tokenItem.token.roles.forEach(role => {
          if(role.id == 2 || role.id == 3)
            flag = true;
      })
      if(!flag)
        next('/');
      else
        next();
    }
}

</script>

<style>

</style>