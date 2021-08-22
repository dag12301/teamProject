<template>
  <div id="wrapper" class="mt-2 mb-2 p-2">{{ foodInfo }}</div>
</template>

<script>
export default {
  props: ["foodInfo"],
};
</script>

<style scoped>
#wrapper {
  background-color: aliceblue;
  height: 200px;
  border-radius: 10px;
  border: 2px solid turquoise;
}
</style>
