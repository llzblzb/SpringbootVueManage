import Vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "@/router";


Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        currentPathName: ''
    },
    mutations: {
        setPath(state) {
            state.currentPathName = localStorage.getItem("currentPathName")
        },
        logout() {
            localStorage.removeItem("user")
            localStorage.removeItem("menus")
            router.push("/login")

            //重置路由
            resetRouter()
        }
    }
})

export default store