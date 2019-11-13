<template>
  <v-navigation-drawer
    app
    clipped
    v-model="drawer"
  >
    <v-list>
      <v-list-item-group v-model="item" color="teal darken-1">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: 'Sidebar',
  props: ['drawer'],
  data: () => ({
    item: 1,
    items: [
      { text: 'Счет', icon: 'mdi-currency-rub', to: '/' },
      { text: 'Категории', icon: 'mdi-view-list', to: '/categories' },
      { text: 'История', icon: 'mdi-book-open-variant', to: '/history' },
      { text: 'Новая запись', icon: 'mdi-check-outline', to: '/record' }
    ]
  })
}
</script>

<style scoped>

</style>
