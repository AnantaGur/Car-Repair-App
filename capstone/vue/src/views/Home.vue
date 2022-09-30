<template>
  <div class="home">
    <div class="container">
      <span id="banner-img">
        <img src="../images/black-logo.png" alt="banner" class="banner" />
      </span>
      <router-link :to="{ name: 'repair' }" class="link"
        >Request a Repair</router-link
      >
      <table>
        <th>My Service Requests</th>
        <br />
        <tr>
          <div
            class="request"
            v-for="request in repairRequests"
            :key="request.id"
          >
            <div class="info">
              <span>{{ request.vehicleMake }}</span>
              <span>{{ request.vehicleModel }}</span>
              <span>{{ request.vehicleColor }}</span>
              <span>{{ request.vehicleYear }}</span>
              <span>{{ request.fullName }}</span>
            </div>
            <tr class="rows">
              <p>Service Type:</p>
              <span>{{ request.serviceName }}</span>
            </tr>
            <tr class="rows">
              <p>Status of Request:</p>
              <span>{{ request.requestStatus }}</span>
            </tr>
          </div>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import repairService from "../services/RepairService";

export default {
  name: "home",
  data() {
    return {
      repairRequests: [],
    };
  },
  created() {
    console.log("created");
    repairService.getRepair().then((response) => {
      console.log(response.data);
      this.repairRequests = response.data;
      console.log(this.repairRequests);
    });
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

.home {
  font-family: "Dosis", sans-serif;
}

body {
  background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
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
  margin-top: 30px;
  font-size: 32px;
  border: 2px solid grey;
  padding: 6px;
  border-radius: 10px;
  font-weight: 550;
  background-color: white;
}

.link:hover {
  background-color: teal;
  color: white;
  border-radius: 10px;
  padding: 2px 10px 4px 10px;
  box-shadow: 10px 5px black;
  font-weight: 700;
}

table {
  display: flex;
  flex-flow: column;
  margin-top: 2vh;
  font-size: 32px;
}

#banner-img {
  display: flex;
  background-color: black;
  width: 100%;
  justify-content: center;
  padding-bottom: 10px;
}

.request {
  border: 3px solid black;
  box-shadow: 7px 10px grey;
  padding: 10px;
  text-align: center;
  margin: 20px;
  border-radius: 10px;
  background-color: white;
}

.request span {
  margin: 10px;
}

.rows {
  display: flex;
  justify-content: center;
  flex-flow: column;
}

.rows p {
  margin: 3px;
  font-weight: bold;
}

.info {
  color: white;
  font-weight: 700;
  box-shadow: 0px 6px rgb(0, 0, 0);
  text-shadow: 2px 2px black;
  background-color: teal;
  border-radius: 10px;
}

table th {
  margin-bottom: -50px;
}
</style>