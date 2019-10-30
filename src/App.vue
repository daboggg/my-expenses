<template>
  <component :is="layout">
    <router-view/>
  </component>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import MainLayout from './layouts/MainLayout'
import EmptyLayout from './layouts/EmptyLayout'
export default {
  name: 'App',
  components: {
    EmptyLayout, MainLayout
  },
  computed: {
    layout () {
      console.log(this.$route.meta.layout)
      return (this.$route.meta.layout || 'empty') + '-layout'
    }
  }
}
</script>
