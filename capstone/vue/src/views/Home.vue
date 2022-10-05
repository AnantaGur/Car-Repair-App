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
              <span v-bind:style="[request.requestStatus === 'Completed' ? {color: 'green'} : {color: 'black'} ||
            request.requestStatus === 'Declined Order' ? {color: 'red'} : {color: 'black'} ||
            request.requestStatus === 'Accepted' ? {color: 'skyblue'} : {color: 'black'}]">{{ request.requestStatus }}</span>
            </tr>
            <div
              class="estimate"
              v-for="estimate in repairEstimate"
              :key="estimate.id"
            >
              <div
                v-if="request.requestId === estimate.requestId"
                id="estimate"
              >
                <tr class="rows">
                  <label for="your-estimate" class="your-estimate"
                    >Your Estimate</label
                  >
                  <td>
                    <label for="date" class="estimate-labels">Date:</label>
                    <span>{{ estimate.pickUpDate }}</span>
                    <label for="pickup-time" class="estimate-labels"
                      >Pick Up Time:</label
                    >
                    <span>{{ estimate.pickUpTime }}</span>
                  </td>
                  <td>
                    <label for="parts-cost" class="estimate-labels"
                      >Parts Cost:</label
                    >
                    <span>{{ estimate.partsCost }}</span>
                    <label for="labor-cost" class="estimate-labels"
                      >Labor Cost:</label
                    >
                    <span>{{ estimate.laborCost }}</span>
                  </td>
                  <td>
                    <label for="total-cost" class="estimate-labels"
                      >Total Cost:</label
                    >
                    <span>
                      {{
                        parseInt(estimate.partsCost) +
                        parseInt(estimate.laborCost)
                      }}
                    </span>
                  </td>
                </tr>
              </div>
            </div>
            <tr>
              <button
                id="delete-request"
                v-if="request.requestStatus === 'Pending Technician Review'"
                v-on:click="deleteRequestCard(request.requestId)"
                class="decline-button"
              >
                Delete
              </button>
              <div v-if="request.requestStatus == 'Pending customer review'">
                <button
                  v-on:click="updateStatusAccept(request.requestId)"
                  class="accept-button"
                >
                  Accept Order
                </button>
                <button
                  v-on:click="updateStatusDeclined(request.requestId)"
                  class="decline-button"
                >
                  Decline Order
                </button>
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
      requestId: "",
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
    updateStatusDeclined(id) {
      this.requestId = id;
      const request = this.repairRequests.find((requestId) => {
        return requestId.requestId === this.requestId;
      });
      request.requestStatus = "Declined Order";
      console.log(request);
      repairService.updateServiceStatus(request).then((response) => {
        if (response.status === 200) {
          alert("Declined Order");
          location.reload();
        }
      });
    },
    updateStatusAccept(id) {
      this.requestId = id;
      const request = this.repairRequests.find((requestId) => {
        return requestId.requestId === this.requestId;
      });
      request.requestStatus = "Accepted";
      repairService.updateServiceStatus(request).then((response) => {
        if (response.status === 200) {
          alert("Accepted");
          location.reload();
        }
      });
    },
  },
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
  background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  left: 0px;
  width: 100%;
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

#estimate {
  border: 2px solid black;
  border-radius: 20px;
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

.rows td {
  margin: 6px;
}

.estimate-labels {
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
  text-transform: uppercase;
  margin-bottom: 15px;
}
.accept-button {
  background-color: teal;
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  margin-right: 10px;
}
.decline-button {
  background-color: rgb(214, 57, 57);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
}

.accept-button:hover {
  background-color: rgba(0, 128, 128, 0.822);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 3px 3px black;
}

.decline-button:hover {
  background-color: rgba(214, 57, 57, 0.822);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 3px 3px black;
}

.your-estimate {
  font-size: 30px;
  padding: 10px;
  font-weight: bold;
}

table th {
  margin-bottom: -50px;
}

@media (min-width: 600px) {
  .home {
    background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  left: 0px;
  width: 100%;
  height: 100%;
  }
};

/* .background-img {
  background-attachment: fixed;
} */
</style>