<template>
  <div class="container">
    <div class="col-md-12 order-md-1">
      <form class="needs-validation" action="#" novalidate>
        <!-- Name and last name form -->
        <div class="row g-3">
          <div class="col-6">
            <label for="firstName" class="form-label">First name</label>
            <input
              type="text"
              class="form-control"
              id="firstName"
              placeholder=""
              value=""
              v-model="user.name"
              required
            />
            <div v-if="!validation.name" class="text-danger">
              Please insert a valid name.
            </div>
          </div>

          <div class="col-6">
            <label for="lastName" class="form-label">Last name</label>
            <input
              type="text"
              class="form-control"
              id="lastName"
              placeholder=""
              value=""
              v-model="user.surname"
              required
            />
            <div v-if="!validation.surname" class="text-danger">
              Please insert a valid surname.
            </div>
          </div>
        </div>

        <br />

        <div class="row g-3">
          <div class="col-6">
            <label for="phoneNumber" class="form-label">Email</label>
            <input
              type="text"
              class="form-control"
              id="email"
              placeholder=""
              value=""
              v-model="user.email"
              required
            />
            <div v-if="!validation.email" class="text-danger">
              Please insert a valid email.
            </div>
          </div>
        
          <br />

          <div class="col-6">
            <label for="phoneNumber" class="form-label">Phone number</label>
            <input
              type="number"
              class="form-control"
              id="phoneNumber"
              placeholder=""
              value=""
              v-model="user.phoneNum"
              required
            />
            <div v-if="!validation.phoneNum" class="text-danger">
              Please insert a valid phone number.
            </div>
          </div>
        </div>

        <br/>

        <div class="row g-3">
          <div class="col-6">
            <label for="address" class="form-label">Address</label>
            <input
              type="text"
              class="form-control"
              id="address"
              placeholder=""
              value=""
              v-model="user.address"
              required
            />
            <div v-if="!validation.address" class="text-danger">
              Please insert a valid address.
            </div>
          </div>

          <div class="col-3">
            <label for="startTime" class="form-label">Start time:</label><br/>
            <input 
              class="ml-1" 
              type="time" 
              name="from" 
              v-model="user.startTime"
            >
            <div v-if="!validation.address" class="text-danger">
              Please insert a valid address.
            </div>
          </div>

          <div class="col-3">
            <label for="finishTime" class="form-label">Finish time:</label><br/>
            <input 
              class="ml-1" 
              type="time" 
              name="from" 
              v-model="user.finishTime"
            >
            <div v-if="!validation.address" class="text-danger">
              Please insert a valid address.
            </div>
          </div>
      
        </div>
        
        <br />
        <hr class="mb-4" />
        <button type="button" class="btn btn-primary btn-block" @click="handleRegisterClick()">
          Register
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2';
export default {
  name: 'pharmacist-registration-form',
  data() {
    return {
      user: {
        name: '',
        surname: '',
        email: '',
        phoneNum: '',
        address: '',
        startTime: '',
        finishTime: '',
      },
      validation: {
        name: true,
        surname: true,
        email: true,
        phoneNum: true,
        address: true,
        startTime: true,
        finishTime: true,
      }
    }
  },
  methods: {
    handleRegisterClick() {
      if (this.validateInputs()) {
        this.$emit('clicked', this.user)
      }
    },
    validateInputs() {
      this.validation.name = this.user.name === '' ? false : true;
      this.validation.surname = this.user.surname === '' ? false : true;
      this.validation.email = this.validateEmail()
      this.validation.phoneNum = this.validatePhoneNum() ? false : true;
      this.validation.address = this.user.address === '' ? false : true;
      this.validation.startTime = this.user.startTime === '' ? false : true;
      this.validation.finishTime = this.user.finishTime === '' ? false : true;

      if (this.user.startTime > this.user.finishTime) {
        Swal.fire({
          title: 'Error',
          text: 'Invalid times selected',
          icon: 'error'
        })

        return false
      }

      return this.validation.name && this.validation.surname && this.validation.email && 
            this.validation.phoneNum && this.validation.address 
    },
    validateEmail() {
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(this.user.email).toLowerCase());
      
    },
    validatePhoneNum() {
      const phoneNum = parseInt(this.user.phoneNum)
      if (!phoneNum) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>

<style>

</style>