import java.lang.Exception

class UserTracker private constructor() {
    companion object{
        private  var instance: UserTracker? = null

        fun getInstance(): UserTracker {
            if(instance == null){
                instance = UserTracker()
            }
            return instance?: throw Exception("Something went wrong...")
        }
    }

    fun logView(user: String, page: String){
        println("$user visit page: $page")
    }

    fun logClick(user: String, button: String){
        println("$user  press button: $button")
    }
}

fun main(){
    val userTracker1 = UserTracker.getInstance()
    userTracker1.logClick("Serg", "Sign In")
    val userTracker2 = UserTracker.getInstance()
    userTracker2.logView("Vasya", "Home Page")
    println(userTracker1 == userTracker2)
}