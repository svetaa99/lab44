<template>
  <div
    class="modal fade"
    id="editProfileModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="editProfileModalTitle"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editProfileModalTitle">Edit Profile</h5>
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
          <registration-form :edit="true" :user="user"></registration-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RegistrationForm from '@/components/RegistrationForm.vue'
export default {
  props: ["user"],
  components: {
    "registration-form": RegistrationForm,
  },
};
</script>

<style>
</style>