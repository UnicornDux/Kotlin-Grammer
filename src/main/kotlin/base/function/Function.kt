package base.function

// 用于测试 kotlin 中的函数

fun function() {
    println("-----------------fun---------------")
    first()
    second()
    third("hello")
    four("hello")
    five("hello", 13)
    six("hello", "world", "kotlin")
    seven("hello")
    seven("hello", 100)

    // ------------------------------
    //  调用一个参数为 lambda 表达式的函数时
    // 当这个函数只有一个函数表达式参数,
    // lambdaArgs({})
    // -| 可以将函数表达式后置 -> lambda(){}
    // -| 这时原函数参数括号省略 -> lambda {}
    lambdaArgs { args ->
        "$args -- $args"
    }
}


/**
 * - fun 开头标识一个函数, 可定义函数的结构为
 * >> fun funName(args:Type[,arg1:Type])[:returnType] {} <<
 *   - 函数参数可以没有，可以为多个，定义可变参数, 参数可赋默认值
 *   - 函数的返回值也是可选的，当没有时默认返回的时 Unit 类型
 */
// 这是有一个没有参数,没有返回值的函数
fun first() {}
// 这是一个具有返回值的函数
fun second():String {
     return "hello"
}
// 这是一个具有一个参数的函数
fun third(args:String){}

// 既有参数也有返回值
fun four(args:String):String{
    return args
}
// 多种类型的参数
fun five(arg1:String, arg2:Int){}

// 可变参数
fun six(vararg args:String){
    println(args::class)
    println(args.contentToString())
    for (i in args.indices) {
        println(args[i])
    }
}

// 参数默认值
fun seven(arg1:String, arg2:Int=12){}


/**
 * kotlin 作为一门函数式的编程语言，
 * - lambda 表达式作为另一个函数的参数是非常常见的情况
 */

// 输入一个函数作为当前函数的参数
fun lambdaArgs(args:(String)->String){
    val random = Math.random() * 10
    if (random < 5) {
       args(random.toString())
    }else {
        println("not execute")
    }
}