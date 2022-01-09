object Main {
  def main(args: Array[String]): Unit = {
    task1()
    task2()
    task3()
    task4()
  }

  def task1(): Unit = {
    val container = new Container[Double](3.2)
    println(container.getContent())
    println("Wykonanie funkcji: " + container.applyFunction[String]( x => "Napis = " + (x * 2).toString))
  }

  def task2(): Unit = {
    val yes = new Yes[Int](99)
    val no = new No()
    println("Czy yes jest podtypem maybe: " + yes.isInstanceOf[Maybe[_]])
    println("Czy no jest podtypem maybe: " + no.isInstanceOf[Maybe[_]])
  }

  def task3(): Unit = {
    val yes = new Yes[Int](99)
    val no = new No()
    println("Wynik funkcji: " + yes.applyFunction[Int](x => x * 2))
    println("Wynik funkcji: " + no.applyFunction[Int, Int](x => x * 2))
  }

  def task4(): Unit = {
    val yes = new Yes[Int](99)
    val no = new No()
    println("yes.orElse: " + yes.getOrElse(32132323))
    println("no.orElse: " + no.getOrElse(32132323))
  }
}
