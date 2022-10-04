<template>
  <div class="employee-page">
    <div id="repair-service-estimate">
      <h1>Requests</h1>
      <form @submit.prevent="sendRepairService" class="employee-form">
        <label for="service-estimate">Service:</label>
        <select
          name="service-estimate"
          id="service-estimate"
          required
          v-model="selected"
          v-on:click="calculateParts"
        >
          <option disabled selected>Please select an option</option>
          <option value="oil-change" id="oil-change">Oil Change</option>
          <option value="brake-change" id="brake-change">Brake Change</option>
          <option value="battery-change" id="battery-change">
            Battery Change
          </option>
          <option value="tire-change" id="tire-change">Tire Change</option>
        </select>
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
            <p>Service Type:</p>
            <span>{{ request.serviceName }}</span>
          </tr>
          <tr class="rows">
            <p>Status of Request:</p>
            <span>{{ request.requestStatus }}</span>
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
            v-on:click="(isChecked = true), sendEstimate(request.requestId)"
            class="select-button"
          >
            Select Order
          </button>
          <button
            v-on:click="
              (isChecked = false), (newRepairEstimateForm.requestId = '')
            "
            v-if="request.requestId === newRepairEstimateForm.requestId && isChecked === true"
            class="deselect-button"
          >
            Deselect Order
          </button>
          <button v-if="request.requestStatus === 'Accepted'" v-on:click="completedRequest(request.requestId)" class="completed-button">Completed</button>
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
      requestId: ""
    };
  },
  methods: {
    sendEstimate(id) {
      this.newRepairEstimateForm.requestId = id;
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
      if (this.selected === "oil-change") {
        this.newRepairEstimateForm.partsCost = "45.00";
      } else if (this.selected === "brake-change") {
        this.newRepairEstimateForm.partsCost = "60.00";
      } else if (this.selected === "battery-change") {
        this.newRepairEstimateForm.partsCost = "115.00";
      } else if (this.selected === "tire-change") {
        this.newRepairEstimateForm.partsCost = "70.00";
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
      request.requestStatus = "Pending customer review";
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
    }
  },
  created() {
    repairService.getAllRepairs().then((response) => {
      this.repairRequests = response.data;
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
  background-color: rgba(0, 128, 128, 0.822);
  color: white;
  border-radius: 10px;
  padding: 6px 13px;
  border: none;
  box-shadow: 5px 3px black;
}

/* .colorChange {
  background-color: blueviolet;
} */
</style>