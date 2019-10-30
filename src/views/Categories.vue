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
  }
}
</script>

<style scoped>

</style>
