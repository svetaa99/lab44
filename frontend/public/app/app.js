const IndexPage = { template: '<index-page></index-page>' }
const PatientViewEmployee = { template: '<patients-list></patients-list>' }
const PharmacyList = { template: '<pharmacy-list></pharmacy-list>' }

const router = new VueRouter({
  mode: 'hash',
  routes: [
    {path: '/', component: IndexPage},
	  {path: '/employee-patients', component: PatientViewEmployee},
    {path: '/pharmacy-list', component: PharmacyList}
  ]
});

var app = new Vue({
  router,
  el: '#lab44'
})