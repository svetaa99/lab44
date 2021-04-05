import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Pharmacies from '../views/Pharmacies.vue'
import PharmacyPage from '../views/PharmacyPage.vue'
import Patients from '../views/Patients.vue'
import Medicines from '../views/MedicinesPage.vue'
import Reservation from '../views/Reservation.vue'

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
    name: 'Medicines',
    component: Medicines
  },
  {
    path: '/employee-reservation',
    name: 'Reservation',
    component: Reservation
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
