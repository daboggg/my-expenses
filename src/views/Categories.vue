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
            <p class="title">Редактировать категорию</p>
            <edit-category/>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
</template>

<script>
import CreateCategory from '../components/CreateCategory'
import EditCategory from '../components/EditCategory'
export default {
  name: 'Categories',
  data: () => ({
  }),
  async mounted () {
    try {
      await this.$store.dispatch('getCategories')
      console.log(this.$store.getters.categories)
    } catch (e) {
      if (e.body.message === 'invalid token') {
        this.$router.push('/login?message=sign in again')
        this.$store.commit('logout')
      }
    }
  },
  components: {
    CreateCategory, EditCategory
  }
}
</script>

<style scoped>

</style>
