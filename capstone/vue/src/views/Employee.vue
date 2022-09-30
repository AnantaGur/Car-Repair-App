<template>
  <div id="repair-service-estimate">
    <h1>Requests</h1>
    <form @submit.prevent="sendRepairService">
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
      />
      <label for="pickup-date">Pickup Date:</label>
      <input
        type="date"
        name="pickup-date"
        id="pickup-date"
        required
        v-model="newRepairEstimateForm.pickUpDate"
      />
      <label for="pickup-time">Pickup Time</label>
      <input
        type="time"
        name="pickup-time"
        id="pickup-time"
        required
        v-model="newRepairEstimateForm.pickUpTime"
      />
      
        <span id="parts-cost">
          Parts Cost: {{ newRepairEstimateForm.partsCost }}
        </span>
  
      <span id="labor-cost"
        >Labor Cost: {{ newRepairEstimateForm.laborCost }}</span
      >
      <input type="submit" value="Submit" id="submit" />
    </form>
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
        requestId: "1",
        partsCost: "",
        laborCost: "",
        totalTime: "",
        pickUpDate: "",
        pickUpTime: "",
      },
      totalLaborCost: "",
    };
  },
  methods: {
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
      console.log(this.newRepairEstimateForm);
      repairService
        .sendServiceEstimate(this.newRepairEstimateForm)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({
              path: "/employee",
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
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

template {
  background-color: brown;
}
#repair-service-estimate {
  font-family: "Dosis", sans-serif;
  width: 50%;
  margin: auto;
  background-color: rgba(255, 228, 196, 0.24);
  padding: 20vh 10vh;
}

#service-estimate {
  border: 1px solid black;
}

#labor-cost {
  margin: 10px 0px 10px 0px;
}

#parts-cost {
  margin-top: 20px;
}

.parts-input {
  padding-left: 8vh;
  padding-right: 8vh;
  text-align: center;
}

</style>