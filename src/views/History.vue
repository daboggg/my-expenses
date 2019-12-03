<template>
  <v-content >
    <div v-if="loader" class="d-flex justify-center ma-10">
      <v-progress-circular
        :size="170"
        :width="12"
        color="teal darken-1"
        indeterminate
      ></v-progress-circular>
    </div>
      <v-data-table
        :headers="headers"
        :items="records"
        caption="Все записи"
        class="elevation-1 px-6"
        v-else
      >
        <template v-slot:item.income="{ item }">
          <v-chip :color="getColor(item.income)" dark>{{ item.income === true ? 'приход' : 'расход' }}</v-chip>
        </template>
        <template v-slot:item.id="{ item }">
          <v-btn
            text
            @click="$router.push('/detail/' + item.id)"
          >
            <v-icon>mdi-open-in-new</v-icon>
          </v-btn>
        </template>
      </v-data-table>
  </v-content>
</template>

<script>
import dateFilter from '../filters/date.filter'
import currencyFilter from '../filters/currency.filter'
export default {
  name: 'History',
  data: () => ({
    loader: true,
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
      // return this.$store.getters.records
      console.log(this.$store.getters.records)
      return this.$store.getters.records.map(rec => {
        const tmp = {
          ...rec,
          creationDate: dateFilter(rec.creationDate, 'timedate'),
          amount: currencyFilter(rec.amount)
        }
        return tmp
      })
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
      this.loader = false
      console.log(this.$store.getters.records)
      console.log(dateFilter(new Date()))
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
