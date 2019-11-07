<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-select
      :items="categories"
      v-model="currentlyCategory"
      :rules="currentlyCategoryRules"
    ></v-select>
    <v-text-field
      v-model="editableСategory"
      label="новое название"
      type="text"
      :rules="editableСategoryRules"
      required
    ></v-text-field>
    <v-btn @click="validate">обновить</v-btn>
  </v-form>
</template>

<script>
export default {
  name: 'EditCategory',
  props: ['categories', 'submit'],
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
  methods: {
    validate () {
      if (this.$refs.form.validate()) {
        this.submit(this.currentlyCategory, this.editableСategory)
        this.$refs.form.reset()
      }
    }
  }
}
</script>

<style scoped>

</style>
