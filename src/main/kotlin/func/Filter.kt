package func

fun runFilter() {
  flexNumber()
}



fun flexNumber() {
  listOf(1..4, 3..9, 4..7)
    .flatMap{it}
    // .filter{ it % 2 == 0 }
    .filterIndexed { index, item -> index % 2 == 0 }
    .forEach(::println)
}
