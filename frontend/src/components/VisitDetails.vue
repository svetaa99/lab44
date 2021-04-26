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
import axios from "axios";
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
            alert("User received a penalty!")
        },
        searchForMedicine: function(){
            if (this.searchMedicine == "") {
                axios
                .get("http://localhost:8000/medicines/all")
                .then((response) => {
                    this.searchedMedicines = response.data;
                    console.log(this.searchedMedicines);
                });
            } 
            else {
                axios
                .get(`http://localhost:8000/medicines/search/${this.searchMedicine}`)
                .then((response) => {
                    this.searchedMedicines = response.data;
                    console.log(this.searchedMedicines);
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
            .post('http://localhost:8000/reports/save', saveObj)
            .then((response) => {
                console.log(response.data);
                this.medicineDTO = response.data;
                this.saved = true;
                this.displayInfo();
            });
        },
        displayInfo: function(){
            var flag = false;
            for(var i = 0; i < this.medicineDTO.length; i++){
                var oneMedicineDTO = this.medicineDTO[i];
                if(oneMedicineDTO.allergic){
                    flag = true;
                    alert("Patient is allergic to medicine: " + oneMedicineDTO.medicine.name);
                }
                else if (!oneMedicineDTO.available){
                    flag = true;
                    alert("Medicine " + oneMedicineDTO.medicine.name + "is not available!");
                }
            }
            if(!flag){
                alert("Report saved!");
            }
            else{
                for(var i = 0; i < this.prescribedMedicine.length; i++){
                    var one = this.prescribedMedicine[i]; // medicine - days
                    for(var j = 0; j < this.medicineDTO.length; j++){ 
                        var two = this.medicineDTO[j]; // full medicine info
                        if(two.allergic || !two.available){
                            // if(one.medicine.id == two.medicine.id)
                            // {
                            //     this.removeMedicine(one.medicine); 
                            // }
                        }
                    }
                }
                //alert("Unavailable or allergic medicines removed from list!");
            }
        },
        redirectToReservation: function(){
            // Are you sure you want to redirect without saving?
            window.location.href="http://localhost:8080/employee-reservation";
        },
        appendMedicine: function(medicine){
            var flag = false;
            for(var i = 0; i < this.prescribedMedicine.length; i++){
                if(this.prescribedMedicine[i].medicine.id == medicine.id){
                    flag = true
                }
            }
            if(!flag){
                var days = prompt("Therapy length in days", "7");
                this.prescribedMedicine.push({medicine, days});
            }
            else{
                alert("Medicine already added!");
            }
        },
        removeMedicine: function(medicine){
            const index = this.prescribedMedicine.indexOf(medicine);
            this.prescribedMedicine.splice(index, 1);
        }
    },
    mounted: function(){
    }
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