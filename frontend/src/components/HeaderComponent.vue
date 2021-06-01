<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top py-1"
    aria-label="Eighth navbar example"
  >
    <div class="container">
      <router-link to="/" class="navbar-brand">
        <img height="50" width="50em" src="../assets/logo.png" />
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

      <!-- Navbar -->
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto mb-4 mb-lg-0">
          <!-- Home -->
          <li :class="$route.path === '/' ? 'nav-item active' : 'nav-item'">
            <router-link to="/" class="nav-link">Home</router-link>
          </li>

          <!-- Pharmacies -->
          <li
            :class="
              $route.path === '/pharmacies' ? 'nav-item active' : 'nav-item'
            "
          >
            <router-link to="/pharmacies" class="nav-link"
              >Pharmacies</router-link
            >
          </li>

          <!-- Medicines -->
          <li
            :class="
              $route.path === '/medicines' ? 'nav-item active' : 'nav-item'
            "
          >
            <router-link to="/medicines" class="nav-link"
              >Medicines</router-link
            >
          </li>

          <!-- Patients -->
          <li
            :class="
              $route.path === '/employee-patients'
                ? 'nav-item active'
                : 'nav-item'
            "
            v-if="
              userRoles.includes(2) ||
              userRoles.includes(3) ||
              userRoles.includes(4)
            "
          >
            <router-link to="/employee-patients" class="nav-link"
              >Patients</router-link
            >
          </li>

          <!-- Appointments -->
          <li class="nav-item dropdown" v-if="userRoles.includes(1)">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Appointments
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <router-link
                to="/patient-pharmacist-appointment"
                class="dropdown-item"
                >Pharmacist</router-link
              >
              <router-link
                to="/patient-dermatologist-appointment"
                class="dropdown-item"
                >Dermatologist</router-link
              >
            </div>
          </li>

          <!-- Employee appointments -->
          <li
            :class="
              $route.path === '/employee-appointments'
                ? 'nav-item active'
                : 'nav-item'
            "
            v-if="userRoles.includes(2) || userRoles.includes(3)"
          >
            <router-link to="/employee-appointments" class="nav-link"
              >Appointments</router-link
            >
          </li>

          <!-- Orders and offers -->
          <li class="nav-item dropdown" v-if="userRoles.includes(4)">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Orders and offers
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <router-link to="/post-order" class="dropdown-item">New order</router-link>

              <router-link to="/all-orders" class="dropdown-item">Orders</router-link>

              <router-link to="/supplier-offers" class="dropdown-item">Offers</router-link>
            </div>
          </li>

          <!-- Medicine issue -->
          <li v-if="userRoles.includes(3)"
            :class="
              $route.path === '/issue-medicine'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/issue-medicine" class="nav-link"
              >Issue medicine</router-link
            >
          </li>

          <!-- Vacation request -->
          <li v-if="userRoles.includes(3) || userRoles.includes(2)"
            :class="
              $route.path === '/vacation'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/vacation" class="nav-link"
              >Vacation</router-link
            >

          <!-- Reserved drugs -->
          <li
            v-if="userRoles.includes(1)"
            :class="
              $route.path === '/reserved-drugs' ? 'nav-item active' : 'nav-item'
            "
          >
            <router-link to="/reserved-drugs" class="nav-link"
              >Reserved drugs</router-link
            >
          </li>

          <!-- Profile -->
          <li
            v-if="userRoles.length !== 0"
            :class="$route.path === '/profile' ? 'nav-item active' : 'nav-item'"
          >
            <router-link to="/profile" class="nav-link">Profile</router-link>
          </li>

          <!-- Dermatologist scheduling by admin -->
          <li v-if="userRoles.includes(4)"
            :class="
              $route.path === '/dermatologist-scheduling'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/dermatologist-scheduling" class="nav-link"
              >Dermatologist schedules</router-link
            >
          </li>

          <!-- Create, search and filter doctors -->
          <li class="nav-item dropdown" v-if="userRoles.includes(4) || userRoles.includes(1)">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Doctors
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <router-link to="/pharmacist-search-and-filter" class="dropdown-item">Pharmacists view</router-link>

              <router-link v-if="userRoles.includes(4)" to="/create-pharmacist" class="dropdown-item">Create pharmacist</router-link>

              <router-link to="/dermatologist-search-and-filter" class="dropdown-item">Dermatologists view</router-link>

              <router-link v-if="userRoles.includes(4)" to="/add-dermatologist-to-pharmacy" class="dropdown-item">Add dermatologist</router-link>
            </div>
          </li>

          <!-- Work calendar -->
          <li v-if="userRoles.includes(2) || userRoles.includes(3)"
            :class="
              $route.path === '/work-calendar'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/work-calendar" class="nav-link"
              >Calendar</router-link
            >
          </li>

          <!-- Vacation requests page -->
          <li v-if="userRoles.includes(4)"
            :class="
              $route.path === '/vacation-requests'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/vacation-requests" class="nav-link"
              >Vacation requests</router-link
            >
          </li>

          <!--Demanded medicines -->
          <li v-if="userRoles.includes(4)"
            :class="
              $route.path === '/demanded-medicines'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/demanded-medicines" class="nav-link"
              >Demanded medicines</router-link
            >
          </li>

          <!-- Promotions -->
          <li v-if="userRoles.includes(4)"
            :class="
              $route.path === '/promotions'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/promotions" class="nav-link"
              >Promotions</router-link
            >
          </li>

          <!-- Reports -->
          <li v-if="userRoles.includes(4)"
            :class="
              $route.path === '/reports'
                ? 'nav-item active'
                : 'nav-item'
            ">
            <router-link to="/reports" class="nav-link"
              >Reports</router-link
            >
          </li>
        </ul>
      </div>

      <router-link to="/login" class="btn btn-custom"
        v-if="!isLoggedIn">Login</router-link 
      >
      <button v-if="isLoggedIn" @click="logout()" class="btn btn-custom">
        Logout
      </button>

    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      userRoles: [],
      isLoggedIn: false,
    };
  },
  methods: {
    logout() {
      localStorage.removeItem('jwt');
      localStorage.removeItem('pw');
      window.location.href = "http://localhost:8080/";
    }  
  },
  mounted: function(){
    const tokenItem = JSON.parse(localStorage.getItem('jwt'));

    tokenItem.token.roles.map(el => {
      this.userRoles.push(el.id);
    });
    this.isLoggedIn = 
      tokenItem.token.accessToken != null
      &&
      new Date().getTime() < tokenItem.expiry ;

    console.log(this.userRoles);
  },
};
</script>

<style>
body {
  display: block;
  padding: 0;
  margin: 0%;
}

.navbar {
  margin-bottom: 0px;
}

.sticky {
  position: fixed;
  top: 0;
  width: 100%;
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