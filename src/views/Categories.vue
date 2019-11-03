<template>
    <v-content>
      <h1>GGGGGGGGGGG</h1>
    </v-content>
</template>

<script>
import { SHA256 } from '../utils/sha256'
export default {
  name: 'Categories',
  mounted () {
    console.log(SHA256('a'))
    console.log(SHA256('sasas'))
    console.log(SHA256('asasas'))
    console.log(SHA256('sasas'))
    console.log(SHA256('vzinin@list.ru'))
    this.$toast('AAAAAAAAAAAdddddddddddd', {
      x: 'right',
      y: 'top',
      timeout: 10000
    })
  }
}
</script>

<style scoped>

</style>
