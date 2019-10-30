<template>
  <v-app-bar
    app
    color="teal darken-1"
    dark
    clipped-left
  >
    <v-app-bar-nav-icon @click.stop="drawerCtrl"></v-app-bar-nav-icon>
    <v-toolbar-title>Application</v-toolbar-title>
  </v-app-bar>
</template>

<script>
export default {
  name: 'Navbar',
  props: ['drawerCtrl']
}
</script>

<style scoped>

</style>
