<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-select
      :items="categories"
      v-model="currentlyCategory"
      :rules="currentlyCategoryRules"
      label="название категории"
    ></v-select>
    <v-text-field
      v-model="editableСategory"
      label="новое название"
      type="text"
      :rules="editableСategoryRules"
      required
    ></v-text-field>
    <v-btn @click="submitHandler" class="mt-3 teal darken-1" dark>обновить</v-btn>
  </v-form>
</template>

<script>
export default {
  name: 'EditCategory',
  data: () => ({
    valid: true,
    currentlyCategory: '',
    currentlyCategoryRules: [
      v => (v && v.length > 0) || 'выберите назавание категории'
    ],
    editableСategory: '',
    editableСategoryRules: [
      v => !!v || 'name is required',
      v => (v && v.length > 3) || 'name must be at least 4 characters'
    ]
  }),
  computed: {
    categories () {
      return this.$store.getters.categories.map(cat => cat.name)
    }
  },
  methods: {
    async submitHandler () {
      if (this.$refs.form.validate()) {
        try {
          const ctgr = this.$store.getters.categories.find(cat => cat.name === this.currentlyCategory)
          await this.$store.dispatch('categoryUpdate', { id: ctgr.id, name: this.editableСategory })
        } catch (e) {
          console.log(e)
          if (e.body.message === 'invalid token') {
            this.$router.push('/login?message=sign in again')
            this.$store.commit('logout')
          }
        }
        this.$refs.form.reset()
      }
    }
  }
}
</script>

<style scoped>

</style>
