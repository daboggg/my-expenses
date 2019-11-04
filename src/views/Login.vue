<template>
  <v-content class="grey darken-1">
    <v-container
      class="fill-height"
      fluid
    >
      <v-row
        align="center"
        justify="center"
      >
        <v-col
          cols="12"
          sm="8"
          md="4"
        >
          <v-card class="elevation-12">
            <v-toolbar
              color="teal darken-1"
              dark
              flat
            >
              <v-toolbar-title>Login</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-card-text>
              <v-form
                ref="form"
                v-model="valid"
                lazy-validation
                @submit.prevent
              >
                <v-text-field
                  label="Email"
                  name="email"
                  prepend-icon="mdi-mail-ru"
                  type="text"
                  v-model="email"
                  :rules="emailRules"
                  required
                ></v-text-field>

                <v-text-field
                  id="password"
                  label="Password"
                  name="password"
                  prepend-icon="lock"
                  type="password"
                  v-model="password"
                  :rules="passwordRules"
                  required
                ></v-text-field>
                <v-card-actions>
                  <p class="mt-4">Нет аккаунта?</p>
                  <v-btn
                    color="deep-orange"
                    text
                    to="/register"
                  >
                    Регистрация
                  </v-btn>
                  <v-spacer></v-spacer>
                  <v-btn
                    type="submit"
                    dark
                    color="teal darken-1"
                    @click="validate"
                  >
                    Login
                  </v-btn>
                </v-card-actions>
              </v-form>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
import { SHA256 } from '../utils/sha256'
export default {
  name: 'Login',
  data: () => ({
    valid: true,
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid'
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required',
      v => (v && v.length > 5) || 'the password must be at least 6 characters'
    ]
  }),
  methods: {
    async validate () {
      if (this.$refs.form.validate()) {
        const tmpPassword = SHA256(this.password)
        const login = {
          email: this.email,
          password: tmpPassword
        }
        try {
          await this.$store.dispatch('login', login)
          this.$router.push('/')
        } catch (e) {
        }
      }
    }
  },
  mounted () {
    if (this.$route.query.message) {
      this.$toast(this.$route.query.message, {
        'x': 'right',
        'y': 'top',
        'timeout': 4000,
        icon: 'mdi-flash',
        'color': 'error'
      })
    }
  }
}
</script>

<style scoped>

</style>
