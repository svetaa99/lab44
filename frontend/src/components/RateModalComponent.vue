<template>
  <div
    class="modal fade"
    id="rateModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="rateModalTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="rateModalTitle">Rate Doctor</h5>
          <button
            type="button"
            class="close"
            data-dismiss="modal"
            aria-label="Close"
          >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="input-group">
            <select
              class="custom-select mr-sm-2"
              id="inlineFormCustomSelect"
              v-model="mark"
            >
              <option selected>Choose...</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
            <span class="input-group-append">
              <button
                class="btn btn-primary"
                type="button"
                style="color: white"
                v-on:click="rateDoctor()"
              >
                Submit
              </button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    doctorId: Number
  },
  data() {
    return {
      mark: 0,
    };
  },
  methods: {
    rateDoctor() {
      const retObj = {
        mark: this.mark,
        doctorId: this.doctorId
      }
      this.$emit("clicked", retObj);
      this.mark = 0;
    },
  },
};
</script>

<style>
</style>