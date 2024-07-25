import java.lang.Exception

enum class TaskStatus {
    ACTIVE, COMPLETED
}

interface Task {
    val id: Int
    val description: String
    var status: TaskStatus
    val type: String
}

//data классы HomeTask и WorkTask реализуют принцип LSP. Имплементируя один и тот же интерфейс, они являются взаимозаменяемыми в клиентском коде
data class HomeTask(
    override val id: Int,
    override val description: String,
    override var status: TaskStatus = TaskStatus.ACTIVE,
    override val type: String = "HomeTask"
) : Task {

}

data class WorkTask(
    override val id: Int,
    override val description: String,
    override var status: TaskStatus = TaskStatus.ACTIVE,
    override val type: String = "WorkTask"
) : Task {

}

interface TaskHandler {
    fun addTask(task: Task)
    fun completeTask(taskId: Int)
    fun getTasks(): List<Task>
}


//SRP класс ответственен только за имплементацию бизнес-логики по работе с Task.
//Изменения в этот класс могут вноситься только по одной причине - если изменится логика работы с тасками
class TaskHandlerImpl : TaskHandler {
    private val tasks =
        mutableListOf<Task>() //DIP  создается список абстракций Task. В него могут попадать как HomeTask, так и WorkTask

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun completeTask(taskId: Int) {
        val task = tasks.find { it.id == taskId }
        if (task?.status == TaskStatus.ACTIVE) {
            task.status = TaskStatus.COMPLETED
        } else {
            throw Exception("Something went wrong!")
        }
    }

    //OCP метод открыт для расширения функциональности(например, добавления логирования), но закрыт для изменения, так как реализует метод интерфейса
    override fun getTasks(): List<Task> {
        return tasks
    }

}

fun showTasks(tasks: List<Task>) {
    for (task in tasks) {
        println(
            "id: ${task.id}  description: ${task.description}  type: ${task.type}  status: ${task.status.toString()}"
        )

    }
}

fun main() {
    val taskHandler: TaskHandler = TaskHandlerImpl()

    taskHandler.addTask(HomeTask(1, "Помыть посуду"))
    taskHandler.addTask(WorkTask(2, "Изучить Kotlin"))
    showTasks(taskHandler.getTasks())
    println()
    taskHandler.completeTask(1)
    showTasks(taskHandler.getTasks())


}
