import kotlin.io.path.*
import kotlinx.datetime.LocalDate

class ToDoList() {

    private val items = mutableListOf<ToDoItem>()

    val size get() = items.size

    // 计算处于各个状态的待办事项数量
    val notStarted get() = items.count { it.status == ItemStatus.NotStarted }

    val inProgress get() = items.count { it.status == ItemStatus.InProgress }

    val done get() = items.count { it.status == ItemStatus.Done }

    // 计算已过期的事项数量
    val overdue get() = items.count { it.isOverdue() }

    constructor(filename: String): this() {
        load(filename)
    }

    // 从 CSV 文件读取并解析数据
    // 从文件读取并解析数据
    fun load(filename: String) {
        items.clear() // 重新加载时清空当前列表
        Path(filename).useLines { lines ->
            lines.forEach { line ->
                if (line.isNotBlank()) {
                    // 1. 先尝试用逗号分隔（兼容原始的 to-do.csv）
                    var parts = line.split(",", limit = 3)

                    // 2. 如果用逗号找不到 3 个部分，说明是 save() 写入的冒号格式，改用冒号分隔
                    if (parts.size < 3) {
                        parts = line.split(":", limit = 3)
                    }

                    if (parts.size == 3) {
                        try {
                            // 尝试解析日期和状态，使用 trim() 消除多余空格
                            val date = LocalDate.parse(parts[0].trim())
                            val status = ItemStatus.valueOf(parts[1].trim())
                            val description = parts[2].trim()
                            items.add(ToDoItem(date, status, description))
                        } catch (e: Exception) {
                            // 3. 捕获并忽略解析异常（例如跳过 CSV 的第一行表头）
                        }
                    }
                }
            }
        }
    }

    // （代码中已有的 save 方法保持原样）
    fun save(filename: String) {
        Path(filename).writer().use {
            for (item in items) {
                it.write("$item\n")
            }
        }
    }

    // （代码中已有的 add 方法保持原样）
    fun add(vararg item: ToDoItem) = item.forEach { items.add(it) }

    // 移除所有状态为 Done 的项，如果移除了至少一项则返回 true
    fun deleteDone(): Boolean {
        return items.removeAll { it.status == ItemStatus.Done }
    }

    // 根据传入的状态返回列表，若参数为 null 则返回全部列表
    fun getItems(status: ItemStatus? = null): List<ToDoItem> {
        return if (status == null) {
            items.toList()
        } else {
            items.filter { it.status == status }
        }
    }

    // 返回匹配指定日期的所有待办事项
    fun getItemsDueOn(date: LocalDate): List<ToDoItem> {
        return items.filter { it.dueDate == date }
    }
}