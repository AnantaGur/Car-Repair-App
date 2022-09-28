<template>
  <div id="repair-service-estimate">
    <h1>Requests</h1>
    <form>
      <label for="service-estimate">Service:</label>
      <select
        name="service-estimate"
        id="service-estimate"
        required
        v-model="selected"
      >
        <option disabled selected>Please select an option</option>
        <option value="oil-change" id="oil-change">Oil Change</option>
        <option value="brake-change" id="brake-change">Brake Change</option>
        <option value="battery-change" id="battery-change">
          Battery Change
        </option>
        <option value="tire-change" id="tire-change">Tire Change</option>
      </select>
      <div id="ifOil" v-if="selected === 'oil-change'">
        <label for="oil-cost">Parts Cost:</label>
        <input
          type="text"
          id="oil-cost"
          name="oil-cost"
          required
          disabled
          value="40.00"
          placeholder="40.00"
          v-model="newRepairEstimateForm.partsCost"
        />
      </div>
      <div id="ifBrake" v-if="selected === 'brake-change'">
        <label for="brake-cost">Parts Cost:</label>
        <input
          type="text"
          id="brake-cost"
          name="brake-cost"
          required
          disabled
          value="60.00"
          placeholder="60.00"
          v-model="newRepairEstimateForm.partsCost"
        />
      </div>
      <div id="ifBattery" v-if="selected === 'battery-change'">
        <label for="bat-cost">Parts Cost:</label>
        <input
          type="text"
          id="bat-cost"
          name="bat-cost"
          required
          disabled
          value="115.00"
          placeholder="115.00"
          v-model="newRepairEstimateForm.partsCost"
        />
      </div>
      <div id="ifTire" v-if="selected === 'tire-change'">
        <label for="tire-cost">Parts Cost:</label>
        <input
          type="text"
          id="tire-cost"
          name="tire-cost"
          required
          disabled
          value="70.00"
          placeholder="70.00"
          v-model="newRepairEstimateForm.partsCost"
        />
      </div>
      <label for="labor-cost">Labor Cost:</label>
      <input
        type="number"
        name="labor-cost"
        id="labor-cost"
        required
        v-model="newRepairEstimateForm.laborCost"
      />
      <label for="total-time">Labor in Hours:</label>
      <input
        type="number"
        name="total-time"
        id="total-time"
        required
        v-model="newRepairEstimateForm.totalTime"
      />
      <label for="pickup-date">Pickup Date:</label>
      <input
        type="date"
        name="pickup-date"
        id="pickup-date"
        required
        v-model="newRepairEstimateForm.pickupDate"
      />
      <label for="pickup-time">Pickup Time</label>
      <input
        type="time"
        name="pickup-time"
        id="pickup-time"
        required
        v-model="newRepairEstimateForm.pickupTime"
      />
      <input type="submit" value="Submit" id="submit" />
    </form>
  </div>
</template>

<script>
export default {
  name: "employee",
  data() {
    return {
      selected: "",
      newRepairEstimateForm: {
        partsCost: "",
        laborCost: "",
        totalTime: "",
        pickupDate: "",
        pickupTime: "",
      },
    };
  },
  methods: {
    costCalculator(choice) {
      if (choice.value === "oil-change") {
        alert("check");
        document.getElementById("ifOil").style.display = "block";
      }
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

#repair-service-estimate {
  font-family: "Dosis", sans-serif;
}
#service-estimate {
  border: 1px solid black;
}
</style>