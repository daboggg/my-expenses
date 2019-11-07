<template>
    <v-content>
      <p class="text-center display-1 my-3">Категории</p>
      <v-divider></v-divider>
      <v-container>
        <v-row>
          <v-col>
            <p class="title">Создать категорию</p>
            <create-category @addCategory="addCategory"/>
          </v-col>
          <v-col>
            <p class="title">Редактировать категорию</p>
            <edit-category :categories="categoriesName" :submit="submit" :key="updateEditCategory"/>
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
    categoriesName: [],
    categories: []
  }),
  async mounted () {
    try {
      this.categories = await this.$store.dispatch('getCategories')
      this.categoriesName = this.categories.map(item => item.name)
    } catch (e) {
      if (e.body.message === 'invalid token') {
        this.$router.push('/login?message=sign in again')
        this.$store.commit('logout')
      }
    }
  },
  methods: {
    async submit (oldName, newName) {
      try {
        const ctgr = this.categories.find(cat => cat.name === oldName)
        const ctgrFrmDB = await this.$store.dispatch('categoryUpdate', { id: ctgr.id, name: newName })
        this.categories.forEach(cat => {
          if (cat.id === ctgr.id) {
            cat.name = ctgrFrmDB.name
          }
        })
        this.categoriesName = this.categories.map(item => item.name)
      } catch (e) {
        console.log(e)
        if (e.body.message === 'invalid token') {
          this.$router.push('/login?message=sign in again')
          this.$store.commit('logout')
        }
      }
    },
    addCategory (category) {
      this.categories.push(category)
      this.categoriesName.push(category.name)
    }
  },
  computed: {
    updateEditCategory () {
      return new Date().getMilliseconds()
    }
  },
  components: {
    CreateCategory, EditCategory
  }
}
</script>

<style scoped>

</style>
