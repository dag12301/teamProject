<template>
  <div class="home">
    <HelloWorld />
    {{ isAuthenticated ? "로그인되었음" : "로그인되지 않음" }} from homevue
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import HelloWorld from "@/components/Home.vue";

export default {
  name: "Home",
  components: {
    HelloWorld,
  },
  computed: {
    ...mapGetters(["isAuthenticated"]),
  },
};
</script>
