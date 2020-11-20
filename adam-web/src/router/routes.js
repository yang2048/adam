// import ajax from '$ui/utils/ajax';
import Router from 'vue-router'

export default function({ get }) {
  // ajax({
  //   url: '/api/users'
  // })
  // .then(res => {
  //   // to do something...
  // })
  //   .catch(e => {
  //   this.$store.dispatch('dictInit', null)
  //   })
  Router.beforeEach((to, from, next) => {
    console.warn('============== ajaxajaxajax ================= ')
})
  // this.$store.dispatch('dictInit', null)

  return [];
}


