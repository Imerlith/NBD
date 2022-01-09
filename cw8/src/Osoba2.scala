// Task 5
class Osoba2(val _imie: String, val _nazwisko: String, val _podatek: Double) {
  def imie: String = _imie
  def nazwiko: String = _nazwisko
  def podatek: Double = _podatek

  override def toString: String = "Osoba podatek: " + podatek
}

trait Pracownik(var _pensja: Double) extends Osoba2 {
  def pensja: Double = _pensja

  override def podatek: Double = _pensja * 0.2
  override def toString: String = "Pracownik podatek: " + podatek
}

trait Nauczyciel extends Pracownik {
  override def podatek: Double = _pensja * 0.1
  override def toString: String = "Nauczyciel podatek: " + podatek
}

trait Student extends Osoba2 {
  override def podatek: Double = 0
  override def toString: String = "Student podatek: " + podatek
}



