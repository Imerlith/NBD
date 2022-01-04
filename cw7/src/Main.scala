import scala.annotation.tailrec


object Main {
  def main(args: Array[String]): Unit = {
    val list = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    task1(list)
    task2(list)
    println("Task3: " + task3(list))
    task4(list)
    val pricesByItems = Map(
      "cola" -> 9.99,
      "baton" -> 3.49,
      "piwo" -> 5.78,
      "whiskey" -> 89.88
    )
    println("Task5: "+ task5(pricesByItems))
    task6(("Napis", true, 3))
    task7(pricesByItems)
    val numList = List[Int](1, 2 ,3 ,4, 0 , 1 ,3, 0, 1, 0, -2)
    println("task8: " + task8(numList))
    println("task9: " + task9(numList))
    println("task10: " + task10(List(3.0, -2, 14, -30, 0, 2.5)))
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

  def task4(list: List[String]): Unit = {
    println("Task4a: " + task4a(list))
    println("Task4b: " + task4b(list))
    println("Task4c: " + task4c(list))
  }

  def task4a(list: List[String]): String = {
    list.foldLeft("")((sum, item) => sum + ", " + item).substring(1)
  }

  def task4b(list: List[String]): String = {
    list.foldRight("")((sum, item) => sum + ", " + item)
  }

  def task4c(list: List[String]): String = {
    list.filter(el => el.toLowerCase().startsWith("p"))
      .foldLeft("")((sum, item) => sum + ", " + item).substring(1)
  }

  def task5(items: Map[String, Double]): Map[String, Double] = {
    items map((k: String, v: Double) => (k, v * 0.9))
  }

  def task6(tuple: (String, Boolean, Int)): Unit = {
    println("Task6")
    println(s"string: ${tuple._1}")
    println(s"boolean: ${tuple._2}")
    println(s"int: ${tuple._3}")
  }

  def task7(items: Map[String, Double]): Unit = {
    def findProduct(productName: String): Unit = {
      items.get(productName.toLowerCase()) match {
        case Some(productPrice) => println(s"Cena $productPrice dla produktu o nazwie $productName")
        case None => println(s"Nie ma produktu o nazwie $productName")
      }
    }
    println("Task7:")
    findProduct("Piwo")
    findProduct("Wodka")
  }

  def task8(list: List[Int]): List[Int] = {
    @tailrec def rec(ret: List[Int], list: List[Int]): List[Int] = {
      if (list.isEmpty) {
        ret
      } else {
        if (list.head == 0) {
          rec(ret, list.tail)
        } else {
          rec(ret :+ list.head, list.tail)
        }
      }
    }
    rec(List(), list)
  }

  def task9(list: List[Int]): List[Int] = {
    list map(el => el + 1)
  }

  def task10(list: List[Double]): List[Double] = {
    list.filter(el => el <= 12 && el >= -5)
      .map(el => math.abs(el))
  }
}
