<template>
  <div class="home">
    <div class="container">
      <span id="banner-img">
        <img src="../images/black-logo.png" alt="banner" class="banner" />
      </span>
      <!-- <span class="background-img">
        <img src="../images/flow.jpg" alt="flow">
      </span> -->
      <router-link :to="{ name: 'repair' }" class="link"
        >Request a Repair</router-link
      >
      <table>
        <th>My Service Requests</th>
        <br />
        <tr v-if="repairRequests == ''">
          Click above to request a repair
        </tr>
        <tr>
          <div
            class="request-info"
            v-for="request in repairRequests"
            :key="request.id"
            v-bind:to="request.requestId"
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
            <div
              class="estimate"
              v-for="estimate in repairEstimate"
              :key="estimate.id"
            >
              <div v-if="request.requestId === estimate.requestId">
                <tr class="rows">
                  
                  <span>Date: {{ estimate.pickUpDate }}</span>
                  <span>Pick Up Time: {{ estimate.pickUpTime }}</span>
                  <span>Parts Cost: {{ estimate.partsCost }}</span>
                  <span>Labor Cost: {{ estimate.laborCost }}</span>
                  <span> Total Cost: {{
                    parseInt(estimate.partsCost) + parseInt(estimate.laborCost)
                  }}</span>
                </tr>
              </div>
            </div>
            <tr>
              <button
                id="delete-request"
                v-if="request.requestStatus !== 'Pending customer review'"
                v-on:click="deleteRequestCard(request.requestId)"
              >
                Delete
              </button>
              <div v-if="request.requestStatus == 'Pending customer review'">
              <button>Accept Order</button>
              <button v-on:click="deleteRequestCard(request.requestId)">Decline Order</button>
              </div>
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
      repairEstimate: [],
    };
  },
  created() {
    repairService.getRepair().then((response) => {
      this.repairRequests = response.data;
    });
    repairService.getRepairEstimate().then((response) => {
      this.repairEstimate = response.data;
    });
  },
  methods: {
    deleteRequestCard(id) {
      repairService.deleteRequest(id).then((response) => {
        if (response.status === 200) {
          alert("Deleted");
          location.reload();
        }
      });
    },
    calculatedEstimate() {
      this.totalCost =
        this.repairEstimate.partsCost + this.repairEstimate.laborCost;
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

.home {
  font-family: "Dosis", sans-serif;
  background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  left: 0px;
  width: 100%;
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
  color: white;
  margin-top: 30px;
  font-size: 32px;
  border: 2px solid grey;
  padding: 6px;
  border-radius: 10px;
  font-weight: 550;
  background-color: white;
  background: linear-gradient(
    90deg,
    rgba(36, 35, 50, 1) 1%,
    rgba(31, 136, 173, 0.9500175070028011) 56%,
    rgba(91, 96, 0, 0.25253851540616246) 95%
  );
  font-weight: 550;
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

.request-info {
  border: 3px solid black;
  box-shadow: 7px 10px grey;
  padding: 10px;
  text-align: center;
  margin: 20px;
  border-radius: 10px;
  background-color: rgb(255, 255, 255);
}

.request-info span {
  margin: 10px;
}

.rows {
  display: flex;
  justify-content: center;
  flex-flow: column;
  font-size: 24px;
}

.rows p {
  margin: 3px;
  font-weight: bold;
}

.info {
  color: white;
  font-weight: 700;
  box-shadow: 5px 6px rgba(87, 134, 236, 0.5);
  background: linear-gradient(
    90deg,
    rgba(36, 35, 50, 1) 1%,
    rgba(31, 136, 173, 0.9500175070028011) 56%,
    rgba(91, 96, 0, 0.25253851540616246) 95%
  );
  text-shadow: 2px 2px black;
  border-radius: 10px;
  padding: 5px 10px;
}

table th {
  margin-bottom: -50px;
}

/* .background-img {
  background-attachment: fixed;
} */
</style>