<template>
    <div data-app class="container" align="center" style="width: 80%">
        <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            outlined
            class="mr-4"
            color="grey darken-2"
            @click="setToday"
          >
            Today
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-toolbar-title v-if="!$refs.calendar">
            {{ noRefTitle }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu
            bottom
            right
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                outlined
                color="grey darken-2"
                v-bind="attrs"
                v-on="on"
              >
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day', isYear=false">
                <v-list-item-title>Day</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week', isYear=false">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month', isYear=false">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day', isYear=false">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
              <v-list-item @click="fillActiveDates()">
                <v-list-item-title>Year</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600" v-if="!isYear">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :event-text-color="getTextColor"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          @change="updateRange"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color="selectedEvent.color"
              dark
            >
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
              >
                Cancel
              </v-btn>
              <v-btn v-if="canStartAppointment()"
                text
                color="secondary"
                @click="startAppointment"
              >
                Start
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
      <v-sheet v-if="isYear">
        <YearCalendar
        v-model="year"
        :activeDates.sync="activeDates"
        @toggleDate="toggleDate"
        prefixClass="your_customized_wrapper_class"
        :activeClass="activeClass"
      ></YearCalendar>
      </v-sheet>
    </v-col>
  </v-row>
  </div>
</template>
<script>
import axios from "axios";
import YearCalendar from 'vue-material-year-calendar'
  export default {
    components: {
      YearCalendar
    },
    data: () => ({
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      colors: ['blue', 'green', 'orange'],
      names: [],
      isYear: false,
      year: 2021,
      activeDates: [
        { date: '2021-05-13' }],
      activeClass: 'blue',
      noRefTitle: '',
    }),
    mounted () {
      //this.$refs.calendar.checkChange()
    },
    methods: {
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      getEventColor (event) {
        return event.color
      },
      getTextColor(){
        return "FFFFFF"
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent ({ nativeEvent, event }) {
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          requestAnimationFrame(() => requestAnimationFrame(() => this.selectedOpen = true))
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          requestAnimationFrame(() => requestAnimationFrame(() => open()))
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      updateRange() {

      },
      startAppointment(){
        window.location.href=`http://localhost:8080/employee-appointments/${this.selectedEvent.id}`
      },
      canStartAppointment(){
        var currTime = new Date().getTime();
        var startTime = new Date(this.selectedEvent.start).getTime();
        var finishTime = new Date(this.selectedEvent.end).getTime();

        if(startTime < currTime && currTime < finishTime){
          return true
        }
        return false;
      },
      fillActiveDates(){
        this.events.forEach(event =>{
          const param = {date: event.start.split('T')[0]};
          this.activeDates.push(param);
        })
        this.isYear = true;
      },
      toggleDate(dateInfo){
        this.isYear = false;
        const param = {date: dateInfo.date};
        this.year = dateInfo.date.split('-')[0];
        var monthIndex = dateInfo.date.split('-')[1];
        this.noRefTitle = this.getMonthName(monthIndex) + " " + this.year;
        this.viewDay(param);
      },
      getMonthName(id){
        var res = parseInt(id
                           .replace(/^[0]+/g,""));
        console.log(res);
        switch(res){
          case 1: return "January"
          case 2: return "February"
          case 3: return "March"
          case 4: return "April"
          case 5: return "May"
          case 6: return "June"
          case 7: return "July"
          case 8: return "August"
          case 9: return "September"
          case 10: return "October"
          case 11: return "November"
          case 12: return "December"
          default: return "January"
        }
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
    },
    mounted: function(){
        axios
        .get('http://localhost:8000/events/all')
        .then((response) => {
            this.events = response.data;
            this.events.forEach(ev => ev.timed = false)
            console.log(this.events);
        })
    },
  }
</script>
<style lang="stylus">
.your_customized_wrapper_class
  background-color: #0aa
  color: white
  &.red
    background-color: red
    color: white
  &.blue
    background-color: #000000
    color: white
  &.your_customized_classname
    background-color: yellow
    color: black
</style>