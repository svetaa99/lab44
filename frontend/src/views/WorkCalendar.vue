<template>
  <WorkCalendarComponent v-bind:events="this.events"/>
  
</template>

<script>
import { config } from "@/config.js";

import WorkCalendarComponent from "@/components/WorkCalendarComponent.vue"
export default {
    components : {
        WorkCalendarComponent,
    },
    data() {
        return {
            events: [],
        }
    },
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