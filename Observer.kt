enum class TaskStatus {
    CREATED, COMMENTED, DONE, CANCELLED
}

interface Observer {
    fun update(task: Task)
}

interface Subject {
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}


class Task(var title: String, var status: TaskStatus = TaskStatus.CREATED) : Subject {
    private val observers = mutableListOf<Observer>()
    private val comments = mutableListOf<String>()

    fun changeStatus(newStatus: TaskStatus) {
        status = newStatus
        println("Status of the task '$title' was changed on  '$status'")
        notifyObservers()
    }

    fun addComment(comment: String) {
        comments.add(comment)
        println("Comment was added to task '$title': $comment")
        this.status = TaskStatus.COMMENTED
        notifyObservers()
    }

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.update(this)
        }
    }
}

// Класс Менеджер, реализующий интерфейс Observer
class Manager(val name: String) : Observer {
    override fun update(task: Task) {
        println("Manager $name got notification: Task '${task.title}' was changed. New status: ${task.status}")
    }
}

// Класс Разработчик, реализующий интерфейс Observer
class Developer(val name: String) : Observer {
    override fun update(task: Task) {
        println("Developer $name got notification: Task '${task.title}' was changed. New status: ${task.status}")
    }
}

// Класс Тестировщик, реализующий интерфейс Observer
class Tester(val name: String) : Observer {
    override fun update(task: Task) {
        println("Tester $name  got notification: Task '${task.title}' was changed. New status: ${task.status}")
    }
}

fun main() {
    // Создаем задачу
    val task = Task("Implementation of the Observer pattern", TaskStatus.CREATED)
    // Создаем участников
    val manager = Manager("Ivan")
    val developer = Developer("Aleksei")
    val tester = Tester("Nikolay")
    // Добавляем наблюдателей за задачей
    task.addObserver(manager)
    task.addObserver(developer)
    task.addObserver(tester)
    // Добавляем комментарий к задаче
    task.addComment("All checks have been completed successfully")
    // Меняем статус задачи
    task.changeStatus(TaskStatus.DONE)
}