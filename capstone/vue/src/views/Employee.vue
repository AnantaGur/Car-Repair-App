<template>
  <div class="employee-page">
    <div id="repair-service-estimate">
      <h1>Requests</h1>
      <form @submit.prevent="sendRepairService" class="employee-form">
        <label for="service-estimate">Service:</label>
        <span>{{ selected }}</span>
        <label for="total-time">Labor in Hours:</label>
        <input
          type="number"
          name="hours"
          id="hours"
          required
          v-model="newRepairEstimateForm.totalTime"
          v-on:change="calculateCost(newRepairEstimateForm.totalTime)"
          class="employee-input"
        />
        <label for="pickup-date">Pickup Date:</label>
        <input
          type="date"
          name="pickup-date"
          id="pickup-date"
          required
          v-model="newRepairEstimateForm.pickUpDate"
          class="employee-input"
        />
        <label for="pickup-time">Pickup Time</label>
        <input
          type="time"
          name="pickup-time"
          id="pickup-time"
          required
          v-model="newRepairEstimateForm.pickUpTime"
          class="employee-input"
        />
        <span id="parts-cost">
          Parts Cost: {{ newRepairEstimateForm.partsCost }}
        </span>

        <span id="labor-cost"
          >Labor Cost: {{ newRepairEstimateForm.laborCost }}</span
        >
        <input
          type="submit"
          value="Submit"
          id="submit"
          class="employee-input"
        />
      </form>
      <div class="card-container">
        <label for="dropdown">Filter Results:</label>
        <select
          name="dropdown_btn"
          id="dropdown_menu"
          v-model="dropdown"
          v-on:change="changeDrop(dropdown)"
        >
        <option disabled selected id="select-option">
            --Please select option--
          </option>
          <option value="All Request">All Request</option>
          <option value="Pending Technician Review">
            Pending Technician Review
          </option>
          <option value="Pending Customer Review">
            Pending Customer Review
          </option>
          <option value="Accepted">Accepted</option>
          <option value="Declined Order">Declined Order</option>
          <option value="Completed">Completed</option>
        </select>
      </div>
      <div
        class="request"
        v-for="request in repairRequests"
        :key="request.id"
        v-bind:checked="request.requestId"
        :class="{ colorChange: statusChange }"
      >
        <div class="employee-info">
          <div class="vehicle-info">
            <span>{{ request.vehicleMake }}</span>
            <span>{{ request.vehicleModel }}</span>
            <span>{{ request.vehicleColor }}</span>
            <span>{{ request.vehicleYear }}</span>
          </div>
          <div class="user-info">
            <span>{{ request.fullName }}</span>
            <span>{{ request.phoneNumber }}</span>
            <span>{{ request.email }}</span>
            <br />
          </div>
        </div>
        <div class="service-info">
          <tr class="rows">
            <p>Service Type</p>
            <span class="request-status">{{ request.serviceName }}</span>
          </tr>
          <tr class="rows">
            <p>Status of Request</p>
            <span
              class="request-status"
              v-bind:style="[
                request.requestStatus === 'Completed'
                  ? { color: 'green' }
                  : { color: 'black' } &&
                    request.requestStatus === 'Declined Order'
                  ? { color: 'red' }
                  : { color: 'black' } && request.requestStatus === 'Accepted'
                  ? { color: 'skyblue' }
                  : { color: 'black' },
              ]"
            >
              {{ request.requestStatus }}
            </span>
          </tr>
        </div>
        <br />
        <div class="status-box">
          <button
            v-if="
              isChecked === false &&
              request.requestId !== newRepairEstimateForm.requestId &&
              request.requestStatus == 'Pending Technician Review'
            "
            v-on:click="
              (isChecked = true),
                sendEstimate(request.requestId, request.serviceName),
                calculateParts()
            "
            class="select-button"
          >
            Select Order
          </button>
          <button
            v-on:click="
              (isChecked = false),
                (newRepairEstimateForm.requestId = ''),
                (selected = ''),
                (newRepairEstimateForm.partsCost = '')
            "
            v-if="
              request.requestId === newRepairEstimateForm.requestId &&
              isChecked === true
            "
            class="deselect-button"
          >
            Deselect Order
          </button>
          <button
            v-if="request.requestStatus === 'Accepted'"
            v-on:click="completedRequest(request.requestId)"
            class="completed-button"
          >
            Complete
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import repairService from "../services/RepairService";

export default {
  name: "employee",
  data() {
    return {
      selected: "",
      newRepairEstimateForm: {
        requestId: "",
        partsCost: "",
        laborCost: "",
        totalTime: "",
        pickUpDate: "",
        pickUpTime: "",
      },
      repairRequests: [],
      isChecked: false,
      requestId: "",
      dropDown: "",
      newRequests: [],
    };
  },
  methods: {
    sendEstimate(id, serviceName) {
      this.newRepairEstimateForm.requestId = id;
      this.selected = serviceName;
      console.log(serviceName);
    },
    statusChange() {
      let selection = document.querySelector("colorChange");
      return this.repairRequests.forEach((repair) => {
        if (this.newRepairEstimateForm.requestId === repair.requestId) {
          selection.style.color = "red";
        }
      });
    },

    calculateCost(hours) {
      let total = 0;
      const labor = 45.0;
      hours = this.newRepairEstimateForm.totalTime;
      total = hours * labor;
      this.newRepairEstimateForm.laborCost = total;
    },
    calculateParts() {
      if (this.selected === "Oil Change") {
        this.newRepairEstimateForm.partsCost = "45.00";
      } else if (this.selected === "Brake Change") {
        this.newRepairEstimateForm.partsCost = "60.00";
      } else if (this.selected === "Battery Change") {
        this.newRepairEstimateForm.partsCost = "115.00";
      } else if (this.selected === "Tire Change") {
        this.newRepairEstimateForm.partsCost = "70.00";
      }
    },
    changeDrop(id) {
      console.log(id);
      if (id === "Pending Technician Review") {
        this.repairRequests = this.newRequests.filter((element) => {
          return element.requestStatus === "Pending Technician Review";
        });
      } else if (id === "Completed") {
        this.repairRequests = this.newRequests.filter((element) => {
          return element.requestStatus === "Completed";
        });
      } else if (id === "Declined Order") {
        this.repairRequests = this.newRequests.filter((element) => {
          return element.requestStatus === "Declined Order";
        });
      } else if (id === "Accepted") {
        this.repairRequests = this.newRequests.filter((element) => {
          return element.requestStatus === "Accepted";
        });
      } else if (id === "Pending Customer Review") {
        this.repairRequests = this.newRequests.filter((element) => {
          return element.requestStatus === "Pending Customer Review";
        });
      } else if (id === "All Request") {
        this.repairRequests = this.newRequests;
      }
    },
    sendRepairService() {
      repairService
        .sendServiceEstimate(this.newRepairEstimateForm)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({
              path: "/",
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
      const request = this.repairRequests.find((requestId) => {
        return requestId.requestId === this.newRepairEstimateForm.requestId;
      });
      request.requestStatus = "Pending Customer Review";
      repairService.updateServiceStatus(request).then((response) => {
        if (response.status === 200) {
          alert("Estimate was sent");
          location.reload();
        }
      });
    },
    completedRequest(id) {
      this.requestId = id;
      const request = this.repairRequests.find((requestId) => {
        return requestId.requestId === this.requestId;
      });
      request.requestStatus = "Completed";
      repairService.updateServiceStatus(request).then((response) => {
        if (response.status === 200) {
          alert("Completed");
          location.reload();
        }
      });
    },
  },
  created() {
    repairService.getAllRepairs().then((response) => {
      this.repairRequests = response.data;
      this.newRequests = response.data;
    });
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

#repair-service-estimate {
  font-family: "Dosis", sans-serif;
  width: 50%;
  margin: auto;
  margin-bottom: 10vh;
}

#labor-cost {
  margin: 10px 0px 10px 0px;
}

#parts-cost {
  margin-top: 20px;
}

#service-estimate {
  border: 1px solid black;
}

.card-container {
  display: flex;
  flex-flow: column;
  align-items: center;
  margin-top: 20px;
}

.card-container label {
  font-weight: 600;
  background-color: black;
  color: white;
  border-radius: 10px;
  margin-bottom: 5px;
  padding: 3px 20px 3px 20px;
}

.employee-page {
  background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  left: 0px;
  width: 100%;
}

.employee-form {
  font-weight: 700;
}

.parts-input {
  padding-left: 8vh;
  padding-right: 8vh;
  text-align: center;
}

.request {
  border: 3px solid black;
  box-shadow: 7px 10px grey;
  padding: 10px;
  text-align: center;
  margin: 20px;
  border-radius: 10px;
  background-color: white;
  display: flex;
  align-items: center;
  flex-flow: column;
  margin-top: 40px;
}

.request span {
  margin: 10px;
}

.rows p {
  font-weight: bold;
  text-decoration: underline;
}

.employee-info {
  color: white;
  font-weight: 700;
  box-shadow: 0px 6px rgb(0, 0, 0);
  text-shadow: 2px 2px black;
  background: linear-gradient(
    90deg,
    rgba(36, 35, 50, 1) 1%,
    rgba(31, 136, 173, 0.9500175070028011) 56%,
    rgba(91, 96, 0, 0.25253851540616246) 95%
  );
  border-radius: 10px;
  padding: 0px 10px 0px 10px;
  width: 80%;
}

.vehicle-info {
  display: flex;
  justify-content: space-around;
  text-transform: uppercase;
  justify-content: center;
}

.user-info {
  display: flex;
  justify-content: space-around;
  text-transform: capitalize;
  justify-content: center;
}

.service-info {
  text-transform: capitalize;
  font-size: 18px;
}

.employee-input {
  width: 50%;
  padding: 6px;
  border: none;
  border-radius: 6px;
  box-shadow: 2px 2px rgba(128, 128, 128, 0.486);
}

.select-button {
  background: linear-gradient(
    90deg,
    rgba(36, 35, 50, 1) 1%,
    rgba(31, 136, 173, 0.9500175070028011) 56%,
    rgba(91, 96, 0, 0.25253851540616246) 95%
  );
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
}

.select-button:hover {
  background-color: rgba(0, 128, 128, 0.822);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 5px 3px black;
}

.deselect-button {
  background-color: rgb(214, 57, 57);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
}

.deselect-button:hover {
  background-color: rgba(214, 57, 57, 0.801);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 5px 3px black;
}

.completed-button {
  background: linear-gradient(
    90deg,
    rgba(36, 35, 50, 1) 1%,
    rgba(31, 136, 173, 0.9500175070028011) 56%,
    rgba(91, 96, 0, 0.25253851540616246) 95%
  );
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
}

.completed-button:hover {
  background-color: teal;
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 5px 3px black;
}

.request-status {
  border: 2px solid black;
  box-shadow: 3px 5px grey;
  padding: 8px 40px 8px 40px;
  text-align: center;
  margin: 20px;
  border-radius: 5px;
  background-color: white;
  display: flex;
  align-items: center;
  flex-flow: column;
  margin-top: 40px;
  font-weight: 600;
}
</style>