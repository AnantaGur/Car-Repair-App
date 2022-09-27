<template>
  <div id="app">
    <div id="nav">
      <!-- <img src="@/images/logotransparent.png" alt="black logo" id="black-logo"> -->
      <router-link :to="{ name: 'home' }" class="black-logo">
      <span class="black-logo"><h2> FIXXER </h2></span>
      </router-link>
      <router-link v-bind:to="{ name: 'home' }" class="nav">Home</router-link>
      <!-- &nbsp;|&nbsp; -->
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>

@import url('https://fonts.googleapis.com/css2?family=Dosis:wght@300&display=swap');


.black-logo {
  width: 65px;
  height: 50px;
  background-color: black;
  border-radius: 35px 2px;
  box-shadow: 20px 0px teal;
  color: white;
  text-decoration: none;
}
.black-logo h2 {
  
  position: relative;
  left: 10px;
  bottom: -4px;
  letter-spacing: 2px;
  font-size: 14px;
}

#nav {
  font-family: 'Dosis', sans-serif;
  padding: 7px;
  display: flex;
  align-items: center;
}

.nav {
  
  text-decoration: none;
  color: navy;
  border-right: 1px solid black;
  padding-right: 25px; 
  padding-left: 40px;
}

/* .nav:hover {
  color: white;
  background-color: rgb(15, 15, 206);
  padding: 5px;
  border-radius: 5px;
  opacity: .75;
} */


</style>
