package app

import kotlin.text.lowercase

fun calculate() {
  println("请输入一个算式例如: 3 + 4")

  while (true) {
    try {
      var express = readLine() ?: break
      val (l, op, r) = express.trim().split(" ")
      println("$l $op $r = ${Operation(op).apply(l.toDouble(), r.toDouble())}")
    } catch (e: IllegalArgumentException) {
      e.printStackTrace()
      println("确认是否使用空格分隔, 输入三组字符")
    } catch (ex: UnsupportedOperationException) {
      ex.printStackTrace()
      println("不支持的运算符")
    } catch (e: NumberFormatException) {
      e.printStackTrace()
      println("运算符的两侧只支持数字")
    } finally {
      println("是否继续计算? [Y], 按其他键退出")
      var input = readLine()
      if (input == null || input.lowercase() != "y") break
    }
  }
  println("感谢您使用我们的计算程序!!")
}

class Operation(
  op: String,
) {
  var operationFunction: (left: Double, right: Double) -> Double

  init {

    operationFunction = when (op) {
      "+" -> { l, r -> l + r }
      "-" -> { l, r -> l - r }
      "*" -> { l, r -> l * r }
      "/" -> { l, r -> l / r }
      "%" -> { l, r -> l % r }
      else -> {
        throw UnsupportedOperationException(op)
      }
    }
  }

  fun apply(left: Double, right: Double): Double = operationFunction(left, right)
}
