<template>
  <v-app  id="inspire">
    <router-view/>
  </v-app>
</template>

<script>
export default {
  name: 'EmptyLayout',
  computed: {
    error () {
      return this.$store.getters.error
    }
  },
  watch: {
    error (error) {
      if (error) {
        this.$toast(error, {
          'x': 'right',
          'y': 'top',
          'timeout': 4000,
          icon: 'mdi-flash',
          'color': 'error'
        })
      }
      this.$store.commit('clearError')
    }
  }
}
</script>

<style scoped>

</style>
