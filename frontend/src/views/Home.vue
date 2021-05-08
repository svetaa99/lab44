<template>
  <div>
    <HelloWorld />
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import Footer from "@/components/FooterComponent.vue";

export default {
  name: "Home",
  components: {
    HelloWorld,
    Footer,
  },
};
</script>
