<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button @click.prevent="submit">qjdd</button>
  </div>
</template>



<script>
export default {
  components: {
    
  },
  methods: {
    submit () {
      this.$store.dispatch('user/testing')
    }
  }
}
</script>

<style>

</style>
