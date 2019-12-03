<template>
  <v-content>
      <p class="text-center display-1 my-3">Счет</p>
      <v-divider></v-divider>
      <v-container>
        <v-row>
          <v-col>
            <p class="title text-center">Доходы</p>
            <income-chart :dataChart="income"/>
          </v-col>
          <v-col>
            <p class="title text-center">Расходы</p>
            <outcome-chart :dataChart="outcome"/>
          </v-col>
        </v-row>
      </v-container>
  </v-content>
</template>

<script>
import IncomeChart from '../components/IncomeChart'
import OutcomeChart from '../components/OutcomeChart'
export default {
  name: 'Home',
  components: {
    IncomeChart, OutcomeChart
  },
  data: () => ({
    records: []
  }),
  computed: {
    income () {
      return this.extractData(true)
    },
    outcome () {
      return this.extractData(false)
    }
  },
  methods: {
    extractData (income) {
      const data = {
        labels: [],
        series: []
      }
      this.records.filter(a => a.income === income).forEach(rec => {
        if (!data.labels.includes(rec.category)) {
          data.labels.push(rec.category)
          data.series.push(rec.amount)
        } else {
          const idx = data.labels.indexOf(rec.category)
          data.series[idx] += rec.amount
        }
      })
      return data
    }
  },
  async mounted () {
    try {
      const records = await this.$store.dispatch('getRecords')
      // console.log(records)
      this.records = records
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
