package base.branch

import java.util.Date

fun branch(){

    // if 分支结构
    // ifStatement()

    // when_case 分支
    whenCase()

}

// if 语句
fun ifStatement(){
    // 1.
    val a = Date().time % 10

    if (a.toInt() == 1) {
        println("a = $a")
    }else {
        println("a != 1")
    }


    // if 作为一个表达式来使用,
    // 这时候，必须存在 else 语句，否则不知道返回的具体值是什么
    // 当 表达式中没有返回值的时候，x 为 Unit 类型，与 Java 中 void 类型对应
    val x  = if (a < 5) {
        // 最后一行为返回值
        1
    }else {
        //
        "hello"
    }
    println(x)

    // kotlin 没有三元表达式，可以使用 if 构建出来一个 三元表达式
    // 当 if / else 逻辑只有一行的时候，可以省略 {}
    // 上述的语句可以省略为下方的写法
    val y = if (a < 6) 10 else "hello"
    println(y)
}

// kotlin 没有 switch 关键字表示的分支语句
// when_case 分支语句, 没有 break, 默认是一种条件匹配就终止
fun whenCase() {

    // 用于数值判断
    val a = Date().time % 10
    when (a.toInt()) {
        1, 2 -> {
            println("0 < && < 3")
        }
        5, 6 -> {
            println("4 < && < 7")
        }
        else -> {
            println("other")
        }
    }

    // 用于数据类型的判断
    val b: Any = 2

    when (b) {
        //
        is Int -> {
            println(b.toInt())
        }
        is String -> {
            println(b.toString())
        }
        else -> {
            println(b)
        }
    }

    // when case 作为表达式，返回结果
    // 这时候 else 分支必须存在，
    // 表示式的最后一行为返回值，不需要 return, 最后一行不返回则是 Unit
    val x = when (a.toInt()) {
        1, 3 -> {
            println("plus")
            a + 10
        }
        5, 6 -> {
            println("minus")
            a - 3
        }
        else -> {
            "not handle"
        }
    }
    println("x is $x")
}