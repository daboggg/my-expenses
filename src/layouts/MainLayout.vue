<template>
  <v-app id="inspire">

    <sidebar :drawer="drawer"/>

    <navbar :drawerCtrl="drawerCtrl"/>

    <router-view/>
  </v-app>
</template>

<script>
import Sidebar from '../components/app/Sidebar'
import Navbar from '../components/app/Navbar'
export default {
  name: 'MainLayout',
  props: {
    source: String
  },
  data: () => ({
    drawer: true
  }),
  methods: {
    drawerCtrl () {
      this.drawer = !this.drawer
    }
  },
  components: {
    Sidebar, Navbar
  },
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
