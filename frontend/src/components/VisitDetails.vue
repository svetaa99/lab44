<template>
    <div id="completeDiv"> 
        <h1>Visit details</h1>
        <hr>
        <button type="button" class="btn btn-outline-danger" v-on:click="penaltyForPatient()">Patient didn't show up</button>
        <br>
        <div class="form-group" id="informationDiv">
            <label for="exampleFormControlTextarea3">Information</label>
            <textarea class="form-control" id="exampleFormControlTextarea3" rows="10" cols="60" v-model="visitInformation"></textarea>
        </div>

        <div class="card mb-3">
            <h1 class="display-16">Medicine prescription</h1>
            <div class="card-body" id="prescriptionDiv">
                <div class="input-group">
                    <input type="search" class="form-control rounded" placeholder="Search medicine name" aria-label="Search"
                        aria-describedby="search-addon" id="searchMedicine" v-model="searchMedicine">
                    <button type="button" class="btn btn-outline-primary" id="smButton" v-on:click="searchForMedicine()">Search</button>
                </div>
                <div class="grid-container">
                    <div id="tableDiv">
                    <table class="table table-sm" id="medicineTable">
                        <thead>
                            <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Type</th>
                            <th scope="col">Specification</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="sm in searchedMedicines" :key="sm.id" v-on:dblclick="appendMedicine(sm)">
                                <td>{{ sm.name }}</td>
                                <td>{{ sm.type }}</td>
                                <td>{{ sm.specification }}</td>
                            </tr>
                        </tbody>
                    </table>
                    </div>

                    <div id="ulDiv">
                    <p>Selected</p>
                    <ul class="list-group list-group-flush" id="reservedMed">
                        <li class="list-group-item" v-for="pm in prescribedMedicine" :key="pm.medicine.id" v-on:dblclick="removeMedicine(pm)">{{pm.medicine.name}} for {{pm.days}} days</li>
                    </ul>
                    </div>
                </div>

            </div>
        </div>
        <button type="button" class="btn btn-outline-primary" v-on:click="redirectToReservation()">Reserve new term</button>
        <button type="button" class="btn btn-success" id="saveButton" v-on:click="save()">Save</button>
    </div>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';
import { config } from "@/config.js";
const API_URL = config.API_URL;

export default {

    data(){
        return{
            visitId: this.$route.params.id,
            searchMedicine: "",
            searchedMedicines: [],
            selectedMedicine: null,
            prescribedMedicine: [],
            visitInformation: "",
            saved: false,
            medicineDTO: [],
        };
    },
    methods: {
        penaltyForPatient: function(){
            axios
            .get(`${API_URL}/patients/penalty/${this.visitId}`)
            .then(
                Swal.fire({
                title: 'User received penalty',
                icon: 'error',
                confirmButtonText: 'Ok'
            })
            )
            
        },
        searchForMedicine: function(){
            if (this.searchMedicine == "") {
                axios
                .get(`${API_URL}/medicines/all/${this.visitId}`)
                .then((response) => {
                    this.searchedMedicines = response.data;
                });
            } 
            else {
                axios
                .get(`${API_URL}/medicines/search/${this.searchMedicine}/${this.visitId}`)
                .then((response) => {
                    this.searchedMedicines = response.data;
            });
        }
        },
        save: function(){
            var medDay = []
            for(var i = 0; i < this.prescribedMedicine.length; i++){
                var medicine = this.prescribedMedicine[i].medicine.id;
                var days = this.prescribedMedicine[i].days;
                medDay.push({medicine, days})
            }
            var saveObj = {visitId: this.visitId, medicineDays: medDay, information: this.visitInformation};
            axios
            .post(`${API_URL}/reports/save`, saveObj)
            .then((response) => {
                this.medicineDTO = response.data;
                this.displayInfo();
            });
        },
        displayInfo: function(){
            var flag = false;
            for(var i = 0; i < this.medicineDTO.length; i++){
                var oneMedicineDTO = this.medicineDTO[i];
                if(oneMedicineDTO.allergic){
                    flag = true;
                    this.processAllergic(oneMedicineDTO);
                }
                else if (!oneMedicineDTO.available){
                    flag = true;
                    this.processUnavailable(oneMedicineDTO);
                }
            }
            if(!flag){
                this.saved = true;
                Swal.fire({
                    title: 'Report saved',
                    icon: 'success',
                    confirmButtonText: 'Ok'
                })
            }
        },
        redirectToReservation: function(){
            if(!this.saved){
                Swal.fire({
                text: "Are you sure you want to leave page without saving?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
                }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href=`http://localhost:8080/employee-reservation/${this.visitId}`;
                }
                })
            }
            else
                window.location.href=`http://localhost:8080/employee-reservation/${this.visitId}`;
        },
        appendMedicine: function(medicine){
            var flag = false;
            for(var i = 0; i < this.prescribedMedicine.length; i++){
                if(this.prescribedMedicine[i].medicine.id == medicine.id){
                    flag = true
                }
            }
            if(!flag){
                Swal.fire({
                    title: "Therapy lenght",
                    text: "Days:",
                    input: 'number',
                    showCancelButton: true   
                }).then((result) => {
                    if (result.value) {
                        var days = result.value;
                        this.prescribedMedicine.push({medicine, days});
                    }
                });
            }
            else{
                Swal.fire({
                    text: 'Medicine already added!',
                    icon: 'warning',
                    confirmButtonText: 'Ok'
                    })
            }
        },
        removeMedicine: function(medicine){
            const index = this.prescribedMedicine.indexOf(medicine);
            this.prescribedMedicine.splice(index, 1);
        },
        getSubstituteForMedicine: function(medicineId){
            this.removeById(medicineId);
            axios
            .get(`${API_URL}/medicines/substitute/${medicineId}/${this.visitId}`)
            .then(response => {
                var temp = this.searchedMedicines;
                this.searchedMedicines = response.data
                if(this.searchedMedicines.length > 0){
                    Swal.fire({
                        title: 'List updated!',
                        text: 'Medicine removed from prescribed list',
                        icon: 'success',
                        timer: 1000,
                        showConfirmButton: false,
                    })
                }
                else{
                    Swal.fire({
                        title: 'No match',
                        text: 'There is no fitting medicine for patient in this pharmacy!',
                        icon: 'warning',
                        timer: 2000,
                        showConfirmButton: true,
                    })
                    this.searchedMedicines = temp;
                }
            });
        },
        removeById: function(id){
            var objToRemove = null;
            this.prescribedMedicine.forEach(med => {
                if(med.medicine.id == id) {
                    objToRemove = med;
                }
            })
            this.removeMedicine(objToRemove);
        },
        processAllergic: function(prescribedMedicine){
            Swal.fire({
                title: 'Alergy',
                text: 'Patient is allergic to medicine: ' + prescribedMedicine.medicine.name,
                icon: 'error',
                showDenyButton: true,
                denyButtonText: `Delete`,
                confirmButtonText: `Show substitute`,
                })
                .then((result) => {
                    if(result.isConfirmed){
                        this.getSubstituteForMedicine(prescribedMedicine.medicine.id);
                    }
                    else if(result.isDenied){
                        this.removeById(prescribedMedicine.medicine.id);
                    }
                })
        },
        processUnavailable: function(prescribedMedicine){
            Swal.fire({
                title: 'Unavailable',
                text: 'Medicine ' + prescribedMedicine.medicine.name + ' is not available!',
                icon: 'error',
                confirmButtonText: 'Ok',
                showDenyButton: true,
                denyButtonText: `Delete`,
                confirmButtonText: `Show substitute`,
                })
                .then((result) => {
                    if(result.isConfirmed){
                        this.getSubstituteForMedicine(prescribedMedicine.medicine.id);
                    }
                    else if(result.isDenied){
                        this.removeById(prescribedMedicine.medicine.id);
                    }
                })
        }
    },
    mounted: function(){

    },
}
</script>

<style scoped>
.form-control {
    width:90%;
    max-width:90%;
    max-height:30%;
    margin: auto;
}
#informationDiv {
    padding: 30px
}
#completeDiv {
    padding-bottom: 50px;
}

#searchMedicine, #smButton{
    float:left; display:inline; 
}
#searchMedicine{
    width:25%;
    max-width: 25%;
    margin-left: 0px;
    margin-right: auto;
}
#smButton{
    width:10%;
    max-width: 10%;
    margin-right: auto;
}
#medicineTable {
    margin-top: 20px;
}
#ulDiv {
    margin-top: 20px;
}
.grid-container {
    display: grid;
    grid-template-columns: 1.5fr 0.5fr;
    grid-gap: 20px;
}
#saveButton {
    margin-left: 50px;
    margin-right: 0px;
}
</style>