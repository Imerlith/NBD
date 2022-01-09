object Main {
  def main(args: Array[String]): Unit = {
    task1()
    task2()
    task3()
    task4()
    task5()
  }

  def task1(): Unit = {
    println(s"Sroda jest: " + matchingP("Sroda"))
    println(s"Niedziela jest: " + matchingP("Niedziela"))
    println(s"Cos innego jest: " + matchingP("Cos innego"))
  }

  def matchingP(day: String): String = {
    day.toLowerCase match {
      case "poniedzialek" | "wtorek" | "sroda" | "czwartek" | "piatek" => "praca"
      case "sobota" | "niedziela" => "weekend"
      case _ => "Nie ma takiego dnia"
    }
  }

  def task2(): Unit = {
    var konto1 = new KontoBankowe(32.0)
    var konto2 = new KontoBankowe()
    println("Konto1 na poczatku: "+konto1.stanKonta)
    println("Konto2 na poczatku: " + konto2.stanKonta)
    konto1 = konto1.wplata(23.1)
    println("Konto1 po wplacie: " + konto1.stanKonta)
    konto1 = konto1.wyplata(30.99)
    println("Konto1 po wyplacie: " + konto1.stanKonta)
  }

  def task3(): Unit = {
    val osoba1 = new Osoba("Jan", "Kowalski")
    val osoba2 = new Osoba("Adam", "Nowak")
    val osoba3 = new Osoba("Jozef", "Pilsudzki")
    val osoba4 = new Osoba("Ktos", "Inny")
    val osoby = List (osoba1, osoba2, osoba3, osoba4)
    osoby.foreach(os => {
      println(matchGreet(os))
    })
  }

  def matchGreet(osoba: Osoba): String = {
    osoba match {
      case Osoba("Jan", "Kowalski") => "Witaj Janie!"
      case Osoba("Adam", "Nowak") => "Dzien dobry Adamie!"
      case Osoba("Jozef", "Pilsudzki") => "Czolem Panie marszalku!"
      case _ => "Witaj nieznajomy!"
    }
  }

  def task4(): Unit = {
    println("Dwa do potegi trzeciej = " + supplyFuncForInts(1, x => x * 2))
  }

  def supplyFuncForInts(x: Int, fun: Int => Int): Int = {
    fun(fun(fun(x)))
  }

  def task5(): Unit = {
    val pracownik = new Osoba2("Adam", "Nowak", 0) with Pracownik(4000)
    println("Pracownik: "+ pracownik)
    val student = new Osoba2("Adam", "Nowak", 0) with Student {}
    println("Student: "+ student)
    val nauczyciel = new Osoba2("Adam", "Nowak", 0) with Pracownik(2000) with Nauczyciel {}
    println("Nauczyciel: "+ nauczyciel)
    val pracownikStudent = new Osoba2("Adam", "Nowak", 0) with Pracownik(4000) with Student {}
    println("pracownikStudent: "+ pracownikStudent)
    val studentPracownik = new Osoba2("Adam", "Nowak", 0) with Student with Pracownik(4000)
    println("studentPracownik: "+ studentPracownik)
  }
}
