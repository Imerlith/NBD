import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    val list = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    task1(list)
    task2(list)
    println("Task3: " + task3(list))
  }

  def task1(list: List[String]): Unit = {
    println("Task1a: " + task1a(list))
    println("Task1b: " + task1b(list))
    println("Task1c: " + task1c(list))
  }

  def task1a(list: List[String]): String = {
    var result = ""
    for (i <- list) {
      result = result + i + ", "
    }
    result
  }

  def task1b(list: List[String]): String = {
    var result = ""
    for (i <- list; if i.toLowerCase().startsWith("p")) {
      result = result + i + ", "
    }
    result
  }
  def task1c(list: List[String]): String = {
    var result = ""
    var i = 0
    while (i < list.size) {
      result = result + list(i) + ", "
      i += 1
    }
    result
  }

  def task2(list: List[String]): Unit = {
    println("Task2a: " + task2a(list))
    println("Task2b: " + task2b(list))
  }

  def task2a(list: List[String]): String = {
    if (list.isEmpty) {
      ""
    }
    else {
      list.head + ", " + task2a(list.tail)
    }
  }

  def task2b(list: List[String]): String = {
      if (list.isEmpty) {
        ""
      }
      else {
         task2b(list.tail) + list.head + ", "
      }
  }

  def task3(list: List[String]): String = {
    @tailrec
    def iter(x: List[String], result: String): String = {
      if (x.isEmpty) result
      else {
        val tmp = result + x.head + ", "
        iter(x.tail, tmp)
      }
    }
    iter(list, "")
  }
}
