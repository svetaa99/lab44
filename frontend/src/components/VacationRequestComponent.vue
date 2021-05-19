<template>
<div>
  <h1>Vacation</h1>
    <div class="card w-50 mx-auto" v-if="!requested">
      <div class="card-body">
        <div class="d-flex flex-column align-items-center text-center">
            <select class="form-select" aria-label="Default select example" v-model="type">
                <option selected value="ABSENCE">Absence</option>
                <option value="VACATION">Vacation</option>
            </select>
            <form class="form-inline">
                <h4 class="heading">Start</h4><Datepicker placeholder="Datum" class="datepick" v-model="startDate"/>
            </form>
            <form class="form-inline">
                <h4>Finish</h4><Datepicker placeholder="Datum" class="datepick2" v-model="finishDate"/>
            </form>
            <button type="button" class="btn btn-primary" id="buttonSend" @click="sendVacationRequest()">Request</button>
        </div>
      </div>
    </div>
  <h1 v-if="requested">Your request is saved. You will be notified soon!</h1>
</div>
</template>

<script>
import axios from 'axios';
import Datepicker from "vuejs-datepicker";
import Swal from 'sweetalert2'
export default {
    components: {
        Datepicker,
    },
    data() {
        return {
            startDate: null,
            finishDate: null,
            type: 1,
            requested: false,
            savedVac: null
        }
    },
    methods: {
        sendVacationRequest(){
            var newRequest = { type: this.type, start: this.startDate, finish: this.finishDate }
            if(this.startDate < this.finishDate){
                var today = new Date();
                var s = new Date(this.startDate);
                var e = new Date(this.startDate);
                console.log(today, s, e);
                if(today > s || today > e){
                    Swal.fire({
                    title: 'Incorrect values',
                    text: "Can't have vacation in the past",
                    icon: 'error',
                    confirmButtonText: 'Ok'
                    })
                }
                else{
                    axios
                    .post('http://localhost:8000/vacation/request', newRequest)
                    .then(response => {
                        console.log(response.data);
                        this.savedVac = response.data;
                        if(this.savedVac.status != "ON_HOLD")
                            Swal.fire({
                                title: 'Something went wrong',
                                text: 'Try again, make sure you dont have pending requests!',
                                icon: 'error',
                                confirmButtonText: 'Ok'
                            })
                        else
                            Swal.fire({
                                title: 'Successfully sent vacation request',
                                icon: 'success',
                                confirmButtonText: 'Ok'
                            })
                    })
                }
            }
            else{
                Swal.fire({
                title: 'Incorrect values',
                text: 'Finish date is before starting date',
                icon: 'error',
                confirmButtonText: 'Ok'
                })
                
            }
        }
    },
    mounted: function() {
        axios
        .get('http://localhost:8000/vacation/current')
        .then(response => {
            if(response.data === "YES"){
                this.requested = true;
            }
            else
                this.requested = false;
        })
    }
}
</script>

<style scoped>
.datepick {
    margin-left: 30px;
}
.datepick2 {
    margin-left: 17px;
}
.form-inline {
    margin-top: 20px;
}
#buttonSend {
    margin-top: 20px;
}
</style>