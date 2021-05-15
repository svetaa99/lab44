<template>
  <WorkCalendarComponent :events="events"/>
  
</template>

<script>
import axios from "axios";
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