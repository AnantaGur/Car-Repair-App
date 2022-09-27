<template>
  <div class="repair-contain">
    <h1>Repair Form</h1>
    <form action="repairform.txt">
      <label for="vname">Vehicle Name:</label>
      <input type="text" id="vname" name="vname" required />
      <label for="vmodel">Vehicle Model:</label>
      <input type="text" id="vmodel" name="vmodel" required />
      <label for="vcolor">Vehicle Color:</label>
      <input type="text" id="vcolor" name="vcolor" required />
      <label for="rdescription">Repair Request:</label>
      <select name="rdescription" id="rdescription" required>
        <option disabled selected id="select-option">
          --Please select option--
        </option>
        <option value="oil-change">Oil change</option>
        <option value="brake-change">Brake change</option>
        <option value="tire-change">Tire change</option>
        <option value="battery-change">Battery change</option>
      </select>
      <label for="oname">Owner Name:</label>
      <input type="text" id="oname" name="oname" required />
      <label for="pnumber">Phone Number:</label>
      <input
        type="tel"
        id="pnumber"
        name="pnumber"
        pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
        required
        placeholder="ex. 123-456-7890"
      />
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required />
      <input type="submit" value="Submit" id="submit" />
    </form>
  </div>
</template>

<script>
export default {
  name: "repair",
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

@media (max-width: 1000px) {
  .repair-contain {
    width: 50%;
  }
} ;
</style>