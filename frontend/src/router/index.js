import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Pharmacies from '../views/Pharmacies.vue'
import PharmacyPage from '../views/PharmacyPage.vue'
import Patients from '../views/Patients.vue'
import Medicines from '../views/MedicinesPage.vue'
import Reservation from '../views/Reservation.vue'
import MedicinePage from '../views/MedicinePage.vue'
import LoginPage from '../views/LoginPage.vue'
import RegistrationPage from '../views/RegistrationPage.vue'
import ProfilePage from '../views/ProfilePage.vue';
import DoctorScheduledVisits from '../components/DoctorScheduledVisits.vue'
import VisitPage from '../views/VisitPage'
import PharmacistAppointmentPage from '../views/PharmacistAppointmentPage.vue'
import PharmacyPharmacistPage from '../views/PharmacyPharmacistPage.vue';
import PostOrderPage from '../views/PostOrderPage.vue'
import AllOrdersPage from '../views/AllOrdersPage.vue'
import SupplierOffersPage from '../views/SupplierOffersPage.vue'
import IssueComponent from '../components/IssueComponent.vue'
import VacationRequestComponent from '../components/VacationRequestComponent.vue'
import AppointmentToDermatologistPage from '../views/AppointmentToDermatologistPage.vue'
import ReservedDrugs from '../views/ReservedDrugsPage.vue'
import DermatologistSchedulingPage from '../views/DermatologistSchedulingPage.vue'
import CreatePharmacistPage from '../views/CreatePharmacistPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/pharmacies',
    name: 'Pharmacies',
    component: Pharmacies
  },
  {
    path: '/pharmacies/:id',
    name: 'PharmacyPage',
    component: PharmacyPage
  },
  {
    path: '/employee-patients',
    name: 'Patients',
    component: Patients
  },
  {
    path: '/medicines',
    name: 'Medicine',
    component: Medicines
  },
  {
    path: '/employee-reservation/:id',
    name: 'Reservation',
    component: Reservation
  },
  {
    path: '/medicines/:id',
    name: 'MedicinesPage',
    component: MedicinePage
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/registration',
    name: 'RegistrationPage',
    component: RegistrationPage
  },
  {
    path: '/profile',
    name: 'ProfilePage',
    component: ProfilePage
  },
  {
    path: '/employee-appointments',
    name: 'DoctorScheduledVisits',
    component: DoctorScheduledVisits
  },
  {
    path: '/employee-appointments/:id',
    name: 'VisitDetails',
    component: VisitPage
  },
  {
    path: '/patient-pharmacist-appointment',
    name: 'PharmacistAppointmentPage',
    component: PharmacistAppointmentPage
  },
  {
    path: '/pharmacies/:id/pharmacists',
    name: 'PharmacyPharmacistPage',
    component: PharmacyPharmacistPage
  },
  {
    path: '/post-order',
    name: 'PostOrderPage',
    component: PostOrderPage
  },
  {
    path: '/supplier-offers',
    name: 'SupplierOffersPage',
    component: SupplierOffersPage
  },
  {
    path: '/issue-medicine',
    name: 'IsueMedicinePage',
    component: IssueComponent
  },
  {
    path: '/all-orders',
    name: 'AllOrdersPage',
    component: AllOrdersPage
  },
  {
    path: '/vacation',
    name: 'VacationRequestComponent',
    component: VacationRequestComponent
  },
  {
    path: '/patient-dermatologist-appointment',
    name: 'AppointmentToDermatologistPage',
    component: AppointmentToDermatologistPage
  },
  {
    path: '/reserved-drugs',
    name: 'ReservedDrugs',
    component: ReservedDrugs
  },
  {
    path: '/dermatologist-scheduling',
    name: 'DermatologistSchedulingPage',
    component: DermatologistSchedulingPage
  },
  {
    path: '/create-pharmacist',
    name: 'CreatePharmacistPage',
    component: CreatePharmacistPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
