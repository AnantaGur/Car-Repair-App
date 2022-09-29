<template>
  <div class="home">
    <div class="container">
      <span id="banner-img">
      <img src="../images/black-logo.png" alt="banner" class="banner">
      </span>
    <table>
      <th>My Service Requests</th><br>
      <tr>
      <div class="request" v-for="request in repairRequests" :key="request.id">
        <h1>{{ request.vehicleMake }}</h1>
        <h1>{{ request.vehicleModel }}</h1>
        <h1>{{ request.vehicleColor }}</h1>
      </div>
      </tr>
      <tr>Estimated Schedule <td>This is an estimate on your schedule</td></tr><br>
      <tr>Estimated Cost <td>This is an estimate on your cost</td></tr>
    </table>
    <router-link :to="{name: 'repair'}" class="link">Repair Request</router-link>
  </div>
  </div>
</template>

<script>

import repairService from "../services/RepairService"

export default {
  name: "home",
  data() {
    return {
      repairRequests: []
    }
  },
  created() {
    console.log("created")
    repairService.getRepair().then(response => {
      console.log(response.data)
      this.repairRequests = response.data;
      console.log(this.repairRequests)
    });
  }
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
  margin-top: 10px;
  width: 25%;
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

#banner-img {
  display: flex;
  background-color: black;
  width: 100%;
  justify-content: center;
  margin-top: 10px;
  padding-bottom: 10px;
}

</style>