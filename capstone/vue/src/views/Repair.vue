<template>
  <div class="repair-contain">
      <h1>Repair Form</h1>
      <form action="repairform.txt">
          <label for="vname">Vehicle Name:</label>
          <input type="text" id="vname" name="vname">
          <label for="vmodel">Vehicle Model:</label>
          <input type="text" id="vmodel" name="vmodel">
          <label for="vcolor">Vehicle Color:</label>
          <input type="text" id="vcolor" name="vcolor">
          <label for="oname">Owner Name:</label>
          <input type="text" id="oname" name="oname">
          <label for="pnumber">Phone Number:</label>
          <input type="text" id="pnumber" name="pnumber">
          <input type="submit" value="submit" id="submit">
      </form>
  </div>
</template>

<script>
export default {
    name: "repair"
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap");

.repair-contain {
 font-family: "Dosis", sans-serif;
}

#submit {
    margin-top: 15px;
    
}

form label {
    margin: 10px;
}

h1 {
    display: flex;
    justify-content: center;
}
</style>