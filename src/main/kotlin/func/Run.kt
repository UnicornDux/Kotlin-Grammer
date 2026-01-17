package func
/**
 *
 * inline fun <T, R> T.run(block: T.() -> R): R
 *
 */
fun runFunc() {
  // take "hello" as this and use lambda's return as result
  val result = "hello".run {
    (this.length > 10)
  }
  println("run return with: $result")
}
