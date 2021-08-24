<template>
<div class="container">
  <center>
    <div class="row">
      <div class="col-3" style="align-self: start; border-style: solid; border-width: 2px 0px 2px 2px; ">
        <ProfileSide></ProfileSide>
      </div>
      <div class="col-9" style="border: 2px solid black;">
          <router-view></router-view>
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
}
</script>

<style>
.container {
  margin-top: 70px;
  width: 900px;
  display: grid;
}
</style>
