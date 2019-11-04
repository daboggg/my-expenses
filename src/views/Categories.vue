<template>
    <v-content>
      <p class="text-center display-1 my-3">Категории</p>
      <v-divider></v-divider>
      <v-container>
        <v-row>
          <v-col>
            <p class="title">Создать категорию</p>
            <create-category/>
          </v-col>
          <v-col>
            <h1>sdsd</h1>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
</template>

<script>
import CreateCategory from '../components/CreateCategory'
export default {
  name: 'Categories',
  components: {
    CreateCategory
  }
}
</script>

<style scoped>

</style>
