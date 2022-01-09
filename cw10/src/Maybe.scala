trait Maybe[A] {

}


class No extends Maybe[Nothing] {
  def applyFunction[A, R](fun: A => R): Maybe[Nothing] = No()

  def getOrElse[A](v: A): A = v

  def flatMap[A, R](fun: A => R): Maybe[Nothing] = No()

  def map[A, R](fun: A => R): Maybe[Nothing] = No()

  override def toString: String = "No"
}

class Yes[A](val a: A) extends Maybe[A] {
  def applyFunction[R](fun: A => R): Maybe[R] = Yes(fun(a))

  def getOrElse(v: A): A = a

  def map[R](fun: A => R): Maybe[R] = Yes(fun(a))

  def flatMap[R](fun: A => R): R = fun(a)

  override def toString: String = "Yes: " + a
}