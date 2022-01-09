trait Maybe[A] {

}


class No extends Maybe[Nothing] {
  def applyFunction[A, R](fun: A => R): Maybe[Nothing] = {
    No()
  }

  def getOrElse[A](v: A): A = v

  override def toString: String = "No"
}

class Yes[A](val a: A) extends Maybe[A] {
  def applyFunction[R](fun: A => R): Maybe[R] = {
    Yes(fun(a))
  }

  def getOrElse(v: A): A = a

  override def toString: String = "Yes: " + a
}