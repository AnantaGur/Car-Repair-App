<template>
  <div id="register" class="text-center">
    <img src="../images/teal-logo.png" alt="Fixxer logo" class="logo" />
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Register an Employee</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"
        ><img src="../images/user.png" alt="user icon" class="login-icon"
      /></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"
        ><img src="../images/lock.png" alt="lock icon" class="login-icon"
      /></label>
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
    
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Register Employee
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
        role: "employee",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this employee.",
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
              alert("Employee created successfully")
              location.reload()
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
      this.registrationErrorMsg = "There were problems registering this employee.";
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

.text-center {
  font-family: "Dosis", sans-serif;
  display: flex;
  flex-flow: column;
  align-items: center;
}

div .logo {
  display: flex;
  width: 50vh;
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
  width: 8%;
  margin: auto;
}

.login-link {
  text-decoration: none;
  color: black;
}

.login-link:hover {
  text-decoration: none;
  background-color: rgb(40, 131, 131, 0.1);
  padding: 5px;
  border-radius: 10px;
}

.btn {
  font-family: "Dosis", sans-serif;
  color: white;
  background-color: teal;
  border-radius: 10px;
  padding: 4px 16px 4px 16px;
  border: none;
}

.btn:hover {
  background-color: rgb(40, 131, 131);
  opacity: 0.75;
}
</style>
