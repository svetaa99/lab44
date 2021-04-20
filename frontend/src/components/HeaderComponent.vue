<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark bg-primary"
    aria-label="Eighth navbar example"
  >
    <div class="container">
      <router-link to="/" class="navbar-brand">
        <img height="50" width="50em" src="../assets/logo.png"/>
        Laboni
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarsExample07"
        aria-controls="navbarsExample07"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto mb-4 mb-lg-0">
          <li :class="$route.path === '/' ? 'nav-item active' : 'nav-item'">
            <router-link to="/" class="nav-link">Home</router-link>
          </li>
          <li
            :class="$route.path === '/about' ? 'nav-item active' : 'nav-item'"
          >
            <router-link to="/about" class="nav-link">About</router-link>
          </li>
          <li
            :class="
              $route.path === '/pharmacies' ? 'nav-item active' : 'nav-item'
            "
          >
            <router-link to="/pharmacies" class="nav-link"
              >Pharmacies</router-link
            >
          </li>
          <li
            :class="
              $route.path === '/employee-patients'
                ? 'nav-item active'
                : 'nav-item'
            "
          >
            <router-link to="/employee-patients" class="nav-link"
              >Patients</router-link
            >
          </li>
          <li
            :class="
              $route.path === '/medicines' ? 'nav-item active' : 'nav-item'
            "
          >
            <router-link to="/medicines" class="nav-link"
              >Medicines</router-link
            >
          </li>
          <li
            :class="
              $route.path === '/employee-reservation'
                ? 'nav-item active'
                : 'nav-item'
            "
          >
            <router-link to="/employee-reservation" class="nav-link"
              >Reservation</router-link
            >
          </li>
          <li
            :class="
              $route.path === '/patient-pharmacist-appointment'
                ? 'nav-item active'
                : 'nav-item'
            "
          >
            <router-link to="/patient-pharmacist-appointment" class="nav-link"
              >Pharmacist appointment</router-link
            >
          </li>
          <li
            :class="
              $route.path === '/profile'
                ? 'nav-item active'
                : 'nav-item'
            "
          >
            <router-link to="/profile" class="nav-link"
              >Profile</router-link
            >
          </li>
        </ul>
      </div>

      <router-link to="/login" class="btn btn-custom"
        >Login</router-link
      >
      <!-- <button v-if="isLoggedIn" @click="logout()" class="btn btn-custom">
        Logout
      </button> -->
    </div>
  </nav>
</template>

<script>
export default {};
</script>

<style>
body {
  display: block;
  padding: 0;
  margin: 0%;
}

.navbar {
  margin-bottom: 20px;
}
.btn-custom,
.btn-custom:visited {
  background-color: #2c56a0 !important;
  color: white;
}
.btn-custom:hover,
.btn-custom:active {
  background-color: #2c4b80 !important;
  color: white;
}
</style>