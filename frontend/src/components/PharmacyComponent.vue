<template>
  <div class="container">
    <div class="row">
      <!-- Post Content Column -->
      <div class="col-lg-8">
        <PharmacyMainComponent :pharmacy="pharmacy" :admin="admin" />
      </div>
      <div class="col-md-4">
        <div class="card my-4">
          <h5 class="card-header">Rate this pharmacy</h5>
          <div class="card-body">
            <div class="input-group">
              <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                <option selected>Choose...</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="3">4</option>
                <option value="3">5</option>
              </select>
              <span class="input-group-append">
                <button
                  class="btn btn-primary"
                  type="button"
                  style="color: white"
                >
                  Submit
                </button>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PharmacyMainComponent from "@/components/PharmacyMainComponent.vue";
export default {
  props: {
    pharmacy: Object,
    admin: Object
  },
  components: {
    PharmacyMainComponent,
  },

};
</script>

<style>
</style>