<template>
  <div id="register" class="text-center">
    <img src="../images/logo.png" alt="Fixxer logo" class="logo" />
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Your Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"><img src="../images/user.png" alt="user icon" class="login-icon"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"><img src="../images/lock.png" alt="lock icon" class="login-icon"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" class="login-link"
        >Have an account?</router-link
      ><br />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
.text-center {
  display: flex;
  flex-flow: column;
  align-items: center;
  /* background-image: url("../images/backgroundmountian.jpg"); */
  /* border: dotted 5px black;
  border-radius: 30px 10px;
*/
}

div .logo {
  display: flex;
  width: 30%;
  height: 30%;
}

form {
  display: flex;
  flex-flow: column;
  align-items: center;

}

.login-link {
  margin: 10px;
}

.sr-only {
  display: flex;
  margin: 10px;
}

.form-control {
  margin: 5px;
  text-align: center;
}

.login-icon {
  width: 10%;
  margin: auto;
}
</style>
