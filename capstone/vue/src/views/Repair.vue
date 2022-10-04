<template>
  <div class="repair-page">
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
          class="repair-input"
        />
        <label for="vmodel">Vehicle Model:</label>
        <input
          type="text"
          id="vmodel"
          name="vmodel"
          required
          v-model="newRepairForm.vehicleModel"
          class="repair-input"
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
          class="repair-input"
        />
        <label for="vcolor">Vehicle Color:</label>
        <input
          type="text"
          id="vcolor"
          name="vcolor"
          required
          v-model="newRepairForm.vehicleColor"
          class="repair-input"
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
          <option value="Oil Change">Oil change</option>
          <option value="Brake Change">Brake change</option>
          <option value="Tire Change">Tire change</option>
          <option value="Battery Change">Battery change</option>
        </select>
        <label for="oname">Owner's Full Name:</label>
        <input
          type="text"
          id="oname"
          name="oname"
          required
          v-model="newRepairForm.fullName"
          class="repair-input"
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
          class="repair-input"
        />
        <label for="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          required
          v-model="newRepairForm.email"
          class="repair-input"
        />
        <input type="submit" value="Submit" id="submit" class="repair-input" />
        <span class="copyright"
          ><img src="../images/teal-logo.png" alt="copyright logo"
        /></span>
      </form>
    </div>
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
  background-color: rgb(255, 255, 255);
  width: 25%;
  margin-top: 10vh;
  margin-bottom: 10vh;
}

.repair-page {
  background-image: url("../images/flow.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  left: 0px;
  width: 100%;
  padding-top: 20px;
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
  background-color: rgb(58, 139, 139);
  box-shadow: 5px 3px black;
}

form label {
  margin: 10px;
}

h1 {
  display: flex;
  justify-content: center;
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

.repair-input {
  width: 50%;
  padding: 6px;
  border: none;
  border-radius: 6px;
  box-shadow: 2px 2px rgba(128, 128, 128, 0.486);
}

.copyright img {
  width: 120px;
  height: 90px;
  border-radius: 50%;
  margin-top: 10px;
}

@media (max-width: 1400px) {
  .repair-contain {
    width: 40%;
  }
} ;
</style>