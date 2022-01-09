// Task 1
class Container[A](private val a: A) {
  def getContent(): A = a
  
  def applyFunction[R](fun: A => R): R = {
    fun(a)
  }
}
