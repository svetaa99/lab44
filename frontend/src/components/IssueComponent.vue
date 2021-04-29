<template>
  <div>
      <div class="form-outline">
      <input
        type="search"
        id="form1"
        class="form-control"
        placeholder="Reservation ID"
        v-model="reservationId"
      >
      <button type="button" id="button1" class="btn btn-primary" v-on:click="searchReservation()">
      <i class="fas fa-search">Find</i>
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';
export default {
    data(){
        return{
            reservationId: "",
            reservation: null,
        };
    },
    methods:{
        searchReservation: function(){
            axios
            .get(`http://localhost:8000/reservations/${this.reservationId}`)
            .then((response) => {this.reservation = response.data; console.log(this.reservation)
                if(this.reservation.date !== 0){
                    Swal.fire({
                        title: "Reservation found",
                        icon: "success",
                        confirmButtonText: 'Display'
                    })
                }
                else{
                    Swal.fire({
                        title: "ID not found",
                        text: "Make sure reservation is made in this pharmacy",
                        icon: "error",
                        confirmButtonText: 'Ok'
                    })
                }
                })
        }
    }
}
</script>

<style scoped>
#form1, #button1{
    float:center; 
}
#form1{
    width:25%;
    max-width: 25%;
    margin: auto;
}
#button1{
    margin-top: 10px;
}
</style>