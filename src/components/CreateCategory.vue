<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="name"
      label="Название"
      type="text"
      :rules="nameRules"
      required
    ></v-text-field>

    <v-btn @click="submit" class="mt-3 teal darken-1" dark>создать</v-btn>
  </v-form>
</template>

<script>
export default {
  name: 'CreateCategory',
  data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'name is required',
      v => (v && v.length > 3) || 'name must be at least 4 characters'
    ]
  }),
  methods: {
    async submit () {
      if (this.$refs.form.validate()) {
        try {
          await this.$store.dispatch('createCategory', {
            name: this.name
          })
          this.$refs.form.reset()
        } catch (e) {
          // console.log(e)
          if (e.body.message === 'invalid token') {
            this.$router.push('/login?message=sign in again')
            this.$store.commit('logout')
          }
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
