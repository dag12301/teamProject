<template>
  <div class="container">
    <center>
      <div class="row">
        <div style="border-style: solid; border-width: 2px 0px 2px 2px">
          <ProfileSide></ProfileSide>
        </div>
      </div>
    </center>
  </div>
</template>

<script>
import ProfileSide from "@/components/myPage/ProfileSide.vue";

export default {
  components: {
    ProfileSide,
  },
};
</script>

<style></style>
