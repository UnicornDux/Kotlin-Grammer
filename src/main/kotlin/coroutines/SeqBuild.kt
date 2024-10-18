package coroutine

fun seq() {
  for (x in fibonaci) {
    println(x)
    if (x > 100) {
      break
    }
  }
}

val fibonaci = sequence {
  yield(1)
  var cur = 1
  var next = 1
  while (true) {
    yield(next)
    val tmp = cur + next
    cur = next
    next = tmp
  }
}
