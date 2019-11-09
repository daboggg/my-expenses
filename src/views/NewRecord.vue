<template>
  <v-content>
    <p class="text-center display-1 my-3">Новая запись</p>
    <v-divider></v-divider>
    <v-container>
      <v-row>
        <v-col>
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            >

            <v-select
              :items="categories"
              v-model="currentlyCategory"
              :rules="currentlyCategoryRules"
              label="название категории"
            ></v-select>

            <v-radio-group v-model="income" column :rules="IncomeRules">
              <v-radio label="Приход" value="true"></v-radio>
              <v-radio label="Расход" value="false"></v-radio>
            </v-radio-group>

            <v-text-field
              v-model="amount"
              label="Сумма"
              type="number"
              :rules="amountRules"
              required
            ></v-text-field>

            <v-text-field
              v-model="description"
              label="Описание"
              type="text"
              :rules="descriptionRules"
              required
            ></v-text-field>

            <v-btn @click="submit" class="mt-3 teal darken-1" dark>создать</v-btn>
          </v-form>
        </v-col>
        <v-col>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
export default {
  name: 'NewRecord',
  data: () => ({
    valid: true,
    currentlyCategory: '',
    currentlyCategoryRules: [
      v => (v && v.length > 0) || 'выберите назавание категории'
    ],
    income: '',
    IncomeRules: [
      v => !!v || 'выберите тип'
    ],
    description: '',
    descriptionRules: [
      v => !!v || 'name is required'
    ],
    amount: null,
    amountRules: [
      v => (v && v > 0) || 'введите число больше ноля'
    ]
  }),
  methods: {
    async submit () {
      if (this.$refs.form.validate()) {
        // eslint-disable-next-line no-unused-vars
        const rec = {
          currentlyCategory: this.currentlyCategory,
          income: this.income,
          description: this.description,
          amount: Number.parseFloat(this.amount).toFixed(2)
        }
        console.log(rec)
        try {
          const category = this.$store.getters.categories.find(cat => cat.name === this.currentlyCategory)
          await this.$store.dispatch('recordCreate', { ...rec, categoryId: category.id })
          this.$refs.form.reset()
        } catch (e) {
          console.log(e)
          if (e.body.message === 'invalid token') {
            this.$router.push('/login?message=sign in again')
            this.$store.commit('logout')
          }
        }
      }
    }
  },
  async mounted () {
    try {
      await this.$store.dispatch('getCategories')
    } catch (e) {
      if (e.body.message === 'invalid token') {
        this.$router.push('/login?message=sign in again')
        this.$store.commit('logout')
      }
    }
  },
  computed: {
    categories () {
      return this.$store.getters.categories.map(cat => cat.name)
    }
  }
}
</script>

<style scoped>

</style>
