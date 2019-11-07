<template>
  <v-app-bar
    app
    color="teal darken-1"
    dark
    clipped-left
  >
    <v-app-bar-nav-icon @click.stop="drawerCtrl"></v-app-bar-nav-icon>
    <v-toolbar-title>Доходы и расходы</v-toolbar-title>
    <v-spacer/>

    <v-menu
    >
      <template v-slot:activator="{ on }">
        <v-btn
          text
          dark
          v-on="on"
        >
          {{name}}
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="(item, index) in items"
          :key="index"
          :to="item.link"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

  </v-app-bar>
</template>

<script>
export default {
  name: 'Navbar',
  data: () => ({
    items: [
      { title: 'профиль', link: '/profile' },
      { title: 'выйти', link: '/login?message=you are out' }
    ]
  }),
  props: ['drawerCtrl'],
  computed: {
    name () {
      return this.$store.getters.getName
    }
  }
}
</script>

<style scoped>

</style>
