import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import Vuelidate from 'vuelidate'
import firebase from 'firebase'

Vue.config.productionTip = false
Vue.use(Vuelidate)
var firebaseConfig = {
  apiKey: "AIzaSyDuvnpXKJmdDqK27DpljhXuYYznwmDj3Uk",
  authDomain: "gracenotes-2381f.firebaseapp.com",
  projectId: "gracenotes-2381f",
  storageBucket: "gracenotes-2381f.appspot.com",
  messagingSenderId: "926429894533",
  appId: "1:926429894533:web:fbadf20f7b4029ff3256ec",
};
firebase.initializeApp(firebaseConfig)

new Vue({
  router,  // add router here
  render: h => h(App),
}).$mount('#app')
