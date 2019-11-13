<template>
  <v-content>
    <v-data-table
      :headers="headers"
      :items="records"
      caption="Все записи"
      class="elevation-1 px-6"
    >
      <template v-slot:item.income="{ item }">
        <v-chip :color="getColor(item.income)" dark>{{ item.income === true ? 'приход' : 'расход' }}</v-chip>
      </template>
      <template v-slot:item.id="{ item }">
        <v-btn text>
          <v-icon>mdi-open-in-new</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </v-content>
</template>

<script>
export default {
  name: 'History',
  data: () => ({
    headers: [
      // {
      //   align: 'center',
      //   sortable: false,
      //   value: 'name'
      // },
      { text: 'Сумма', value: 'amount' },
      { text: 'Дата', value: 'creationDate' },
      { text: 'Тип', value: 'income', align: 'center' },
      { text: 'Категория', value: 'category' },
      { text: 'Открыть', value: 'id' }
    ]
    // records: [
    //   {
    //     income: true,
    //     amount: 6.0,
    //     description: 24,
    //     creationDate: 4.0,
    //     id: '2',
    //     category: 'bbbbbbb'
    //   },
    //   {
    //     income: false,
    //     amount: 9.0,
    //     description: 37,
    //     creationDate: 4.3,
    //     id: '1',
    //     category: 'aaaaaa'
    //   }
    // ]
  }),
  computed: {
    records () {
      return this.$store.getters.records
    }
  },
  methods: {
    getColor (income) {
      if (income) return 'green'
      else return 'red'
    }
  },
  async mounted () {
    try {
      await this.$store.dispatch('getRecords')
      console.log(this.$store.getters.records)
    } catch (e) {
      if (e.body.message === 'invalid token') {
        this.$router.push('/login?message=sign in again')
        this.$store.commit('logout')
      }
    }
  }
}
</script>

<style scoped>

</style>
