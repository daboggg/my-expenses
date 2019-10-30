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
  }
}
</script>

<style scoped>

</style>
