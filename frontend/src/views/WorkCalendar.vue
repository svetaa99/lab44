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
}
</script>

<style>

</style>