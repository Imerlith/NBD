object Main {
  def main(args: Array[String]): Unit = {
    task1()
    task2()
  }

  def task1(): Unit = {
    println("Generator take: ")
    for ( v <- generator.take(20)) {
      println(v)
    }
    println("Generator next: ")
    val buffer = generator.buffered
    for (i <- 0 until 20) {
      println(buffer.next())
    }
  }

  def generator: Iterator[(Int, Int)] = for {
    a <- Iterator.from(1)
    b <- 1 until a + 1 if a % b == 0
  } yield (a, b)

  def task2(): Unit = {
    val yes = Yes(99)
    val no = No()

    println("Yes.map: " + yes.map(x => x * 2))
    println("Yes.flatMap: " + yes.flatMap(x => x * 2))
    println("No.map: " + no.map[Int, Int](x => x * 2))
    println("No.flatMap: " + no.flatMap[Int, Int](x => x * 2))
  }
}
