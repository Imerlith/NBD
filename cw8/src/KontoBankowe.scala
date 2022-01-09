class KontoBankowe(val stanKonta: Double) {

  def this() = {
    this(0)
  }

  def wplata(toAdd: Double): KontoBankowe = {
    if (toAdd < 0) {
      return this
    }
    new KontoBankowe(stanKonta + toAdd)
  }

  def wyplata(toSubstract: Double): KontoBankowe = {
    if (toSubstract < 0) {
      return this
    }
    if (toSubstract > stanKonta) {
      return this
    }
    new KontoBankowe(stanKonta - toSubstract)
  }
}
