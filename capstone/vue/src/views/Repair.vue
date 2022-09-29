<template>
  <div class="repair-contain">
    <h1>Repair Form</h1>
    <form @submit.prevent="addRepairForm">
      <label for="vmake">Vehicle Make:</label>
      <input
        type="text"
        id="vmake"
        name="vmake"
        required
        v-model="newRepairForm.vehicleMake"
      />
      <label for="vmodel">Vehicle Model:</label>
      <input
        type="text"
        id="vmodel"
        name="vmodel"
        required
        v-model="newRepairForm.vehicleModel"
      />
      <label for="vyear">Vehicle Year:</label>
      <input
        type="number"
        id="vyear"
        name="vyear"
        required
        min="1900"
        max="2023"
        placeholder="1900-2023"
        v-model="newRepairForm.vehicleYear"
      />
      <label for="vcolor">Vehicle Color:</label>
      <input
        type="text"
        id="vcolor"
        name="vcolor"
        required
        v-model="newRepairForm.vehicleColor"
      />
      <label for="rdescription">Repair Request:</label>
      <select
        name="rdescription"
        id="rdescription"
        required
        v-model="newRepairForm.serviceName"
      >
        <option disabled selected id="select-option">
          --Please select option--
        </option>
        <option value="oil-change">Oil change</option>
        <option value="brake-change">Brake change</option>
        <option value="tire-change">Tire change</option>
        <option value="battery-change">Battery change</option>
      </select>
      <label for="oname">Owner Name:</label>
      <input
        type="text"
        id="oname"
        name="oname"
        required
        v-model="newRepairForm.fullName"
      />
      <label for="pnumber">Phone Number:</label>
      <input
        type="tel"
        id="pnumber"
        name="pnumber"
        pattern="[0-9]{3}[0-9]{3}[0-9]{4}"
        required
        placeholder="ex. 123-456-7890"
        v-model="newRepairForm.phoneNumber"
      />
      <label for="email">Email:</label>
      <input
        type="email"
        id="email"
        name="email"
        required
        v-model="newRepairForm.email"
      />
      <input type="submit" value="Submit" id="submit" />
    </form>
  </div>
</template>

<script>
import repairService from "../services/RepairService";

export default {
  name: "repair",
  data() {
    return {
      newRepairForm: {
        vehicleMake: "",
        vehicleModel: "",
        vehicleYear: "",
        vehicleColor: "",
        serviceName: "",
        fullName: "",
        phoneNumber: "",
        email: "",
      },
    };
  },
  methods: {
    addRepairForm() {
      repairService
        .addRepairForm(this.newRepairForm)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({
              path: "/"
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

.repair-contain {
  font-family: "Dosis", sans-serif;
  border: 2px solid black;
  margin: auto;
  padding-bottom: 25px;
  border-radius: 20px;
  box-shadow: 10px 5px grey;
  background-color: rgba(61, 61, 102, 0.123);
  width: 25%;
  margin-top: 10vh;
}

#submit {
  margin-top: 15px;
  font-family: "Dosis", sans-serif;
  color: white;
  background-color: teal;
  border-radius: 10px;
  padding: 4px 16px 4px 16px;
  border: none;
  font-weight: bold;
  letter-spacing: 1px;
  font-size: 16px;
}

#submit:hover {
  background-color: rgb(40, 131, 131);
  opacity: 0.75;
  box-shadow: 5px 3px black;
}

form label {
  margin: 10px;
}

h1 {
  display: flex;
  justify-content: center;
}

input {
  width: 50%;
  padding: 6px;
  border: none;
  border-radius: 6px;
  box-shadow: 2px 2px rgba(128, 128, 128, 0.486);
}

select {
  width: 53%;
  border: none;
  padding: 5px;
  border-radius: 6px;
}
#select-option {
  text-align: center;
}

@media (max-width: 1400px) {
  .repair-contain {
    width: 40%;
  }
} ;
</style>