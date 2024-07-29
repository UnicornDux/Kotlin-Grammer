package base.function

/**
 * 说到表达式，就是可以赋值给一个变量的一个代码块，这个变量得到的值就是代码块运行的结果
 *
 * > 中缀表达式
 *  当一个表达式只有一个入参的时候，可以定义一种类似操作符的表达式，调用的时候可以不使用任何操作符
 *  只使用函数名称即可完成使用
 *  1. 定义中缀表达式的时候，需要在函数前添加 infix 关键字
 *  2. 例如
 */
infix fun <T> Array<T>.of(x: T): Int = this.indexOf(x)

// if else 表达式
//
fun run() {
  val input = readLine()
  // if 表达式简化写法，可以表达出类似三元表达式的含义
  val mode = if (input == "debug") "debug" else "release"

  println("当前运行的模式为 $mode")

  // 也可以使用更复杂的内容表达
  val loglevel =
    if (mode == "debug") {
      println("info")
      "info"
    }else if(mode == "release") {
      println("error")
      "error"
    } else {
      print("factal")
      "factal"
    }
  println("current loglevel is $loglevel")
}

// when case 表达式
//
fun whenCase(op: String) {
  var x: (left: Double, right: Double) -> Double =
    when (op) {
      "+" -> { l, r -> l + r}
      "-" -> { l, r -> l - r} 
      "*" -> { l, r -> l * r}
      "/" -> { l, r -> l / r}
      "%" -> { l, r -> l % r}
      else -> { throw UnsupportedOperationException("invalid operation") }
    }
  println(x)
}

// try catch 表达式
fun tryCatch() {
  val value =
    try {
      "OK"
    } catch (e: Exception) {
      e.printStackTrace()
      "Erorr"
    }
  println(value)
}
