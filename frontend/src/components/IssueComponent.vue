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
      <div id = "wrapperDiv">
      <div class="col-md-4 mb-3" v-if="found" id="patientDiv">
        <div class="card">
        <div class="card-body">
            <div class="d-flex flex-column align-items-center text-center">
            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
            <div class="mt-3">
                <h4>{{reservation.patient.name}} {{reservation.patient.surname}}</h4>
                <p class="text-secondary mb-1">{{reservation.patient.email}}</p>
                <p class="text-muted font-size-sm">{{reservation.patient.category}}</p>
            </div>
            </div>
        </div>
        </div>
    </div>
    <div class="col-md-4 mb-3" v-if="found" id="medicineDiv">
        <div class="card">
        <div class="card-body">
            <div class="d-flex flex-column align-items-center text-center">
            <div class="mt-3">
                <h4>{{reservation.medicine.name}}</h4>
                <p class="text-secondary mb-1">{{reservation.medicine.specification}}</p>
                <p class="text-secondary mb-1">Quantity: {{reservation.quantity}}</p>
                <p class="text-muted font-size-sm">{{reservation.medicine.type}}</p>
            </div>
            </div>
        </div>
        </div>
        <button type="button" class="btn btn-success" v-on:click="confirmReservation()" id="confirmButton">Confirm</button>
    </div>
    <div v-if="found" if="priceDiv"><h4>Total price: {{reservation.totalPrice}}</h4></div>
  </div>
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
            found: false,
            savedReservationId: "",
        };
    },
    methods:{
        searchReservation: function(){
            axios
            .get(`http://localhost:8000/reservations/${this.reservationId}`)
            .then((response) => {this.reservation = response.data;
                console.log(response.data);
                if(this.reservation.date !== 0){
                    this.found = true;
                    this.savedReservationId = this.reservationId;
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
        },
        confirmReservation: function(){
            axios
            .get(`http://localhost:8000/reservations/confirm/${this.savedReservationId}`)
            .then(
                Swal.fire({
                    title: "Reservation confirmed",
                    icon: "success",
                    confirmButtonText: 'Ok'
                }))
            this.found = false;
        }
    },
    beforeRouteEnter (to, from, next) {
      const tokenItem = JSON.parse(localStorage.getItem('jwt'));
      var flag = false;
      if(tokenItem == null)
        next('/');
      tokenItem.token.roles.forEach(role => {
          if(role.id == 3)
            flag = true;
      })
      if(!flag)
        next('/');
      else
        next();
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
    margin: auto;
    position: static;
}
#patientDiv, #medicineDiv, #priceDiv{
    float:left; display:inline;
}
#wrapperDiv{
    margin: auto;
    margin-top: 20px;
}
#confirmButton{
    margin-top: 10px;
}
</style>