<template>
  <div class="home">
    <div class="container">
      <img src="../images/black-logo.png" alt="banner" class="banner">
    <table>
      <th>SERVICES</th><br>
      <tr>Estimated Schedule <td>This is an estimate on your schedule</td></tr><br>
      <tr>Estimated Cost <td>This is an estimate on your cost</td></tr>
    </table>
    <router-link :to="{name: 'repair'}" class="link">Repair Request</router-link>
  </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

.home {
  font-family: "Dosis", sans-serif;
}
.container {
  display: flex;
  align-items: center;
  flex-flow: column;
}
.banner {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  width: 25%;
  background-color: black;
  padding-left: 100%;
  padding-right: 100%;
}

.link {
  text-decoration: none;
  color: black;  
  margin-top: 50px;
  font-size: 32px;
}

.link:hover {
  background-color: teal;
  color: white;
  border-radius: 10px;
  padding: 2px 16px 4px 16px;
  box-shadow: 10px 5px black;
}

table {
  display: flex;
  flex-flow: column;
  margin-top: 10vh;
  font-size: 32px;
}

</style>