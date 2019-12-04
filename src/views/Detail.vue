<template>
  <v-content>
    <v-card
      class="mx-auto my-10"
      max-width="344"
    >
      <v-system-bar
        :color="record.income? 'teal darken-1' : 'red darken-1'"
        height="40px"
        dark
        class="title d-flex justify-center"
      >
        {{record.category}}
      </v-system-bar>

      <p class="subtitle-1 ma-4">
        <span class="font-weight-bold">
          {{record.income ? 'Приход' : 'Расход'}}
        </span>
      </p>

      <p class="subtitle-1 ma-4">
        <span class="font-weight-bold">Дата: </span>
        {{record.creationDate | date('datetime')}}
      </p>

      <p class="subtitle-1 ma-4">
        <span class="font-weight-bold">Сумма: </span>
        {{record.amount | currency()}}
      </p>

      <v-card-actions>
        <v-btn text
               color="teal darken-1"
               to="/history"
        >
          Назад
        </v-btn>

        <v-btn
          color="teal darken-1"
          text
          @click="remove"
        >
          Удалить
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn
          icon
          @click="show = !show"
        >
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>
            {{record.description}}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-content>
</template>

<script>
export default {
  name: 'Detail',
  data: () => ({
    show: false,
    record: ''
  }),
  methods: {
    async remove () {
      try {
        console.log(this.record.id + '    :HHHH')
        await this.$store.dispatch('removeRecord', this.record.id)
        this.$router.push('/history')
      } catch (e) {
        if (e.body.message === 'invalid token') {
          this.$router.push('/login?message=sign in again')
          this.$store.commit('logout')
        }
      }
    }
  },
  async mounted () {
    try {
      const id = await this.$route.params.id
      const record = await this.$store.dispatch('getRecord', id)
      console.log(record)
      this.record = record
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
