package func

/**
 *  inline fun <T> T.also(block: (T) -> Unit): T
 */
fun alsoFun() {
  // use also to handle obj which invoke the function
  // then return self
  val result = "hello".also {
    it.replace("h", "H")
  }
  println(result)
}
