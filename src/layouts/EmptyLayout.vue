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
    },
    message () {
      return this.$store.getters.message
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
    },
    message (message) {
      if (message) {
        this.$toast(message, {
          'x': 'right',
          'y': 'top',
          'timeout': 4000,
          icon: 'mdi-flash',
          'color': 'info'
        })
      }
      this.$store.commit('clearMessage')
    }
  }
}
</script>

<style scoped>

</style>
