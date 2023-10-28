package base.loop

// for 循环
fun loop(){

    // 迭代器的原理
    // iterator()
    // forLoop()
    // whileLoop()
    // loopControl()
    label()
}

// kotlin for 循环依赖于迭代器
// 因此 Java 中 for 循环的写法是不能直接在 kotlin 中使用
fun iterator() {
    println("------------iterator--------------")
    // 数组迭代器
    val arr: Array<Int> = arrayOf(1, 3, 4)
    // 迭代器
    val iterator = arr.iterator()
    println(iterator.next())
    println(iterator.next())
    println(iterator.hasNext())
    println(iterator.next())
    println(iterator.hasNext())

}

// kotlin 中的循环依赖于迭代器，只有实现了迭代器接口的对象才能进行循环
fun forLoop(){
    println("---------------loop--------------")
    val str = arrayOf("hello", "of", "world");
    // 遍历元素
    for (s in str) {
       println(s)
    }

    // 下标遍历
    for (index in str.indices) {
        println(index)
    }

    // 获取整个元素对象, 包含了下标与值j
    for (withIndex in str.withIndex()) {
        println("${withIndex.index} - ${withIndex.value}")
    }


    // 增强 for 循环
    str.forEach { item -> println("item: $item") }

    // 携带索引的循环
    // 这里的索引并非数据自身的索引, 而是在循环内部维护的一个索引值
    // 参数不使用括号()包裹，lambda 表达式不使用 {} 包裹
    str.forEachIndexed { i, item ->
       println("$i -- $item")
    }

}

fun whileLoop() {
    val range = 1 .. 10
    var a = 0
    while(a in range) {
        if (a > 10) {
            println(a)
        }
        a++
    }
}

fun loopControl(){

    // break : 中断循环, 多层循环可以使用标签来中断
    // continue : 终止当前循环中的本次执行
    // return : 结束当前函数体
    a@ for (i in 0..4) {
        for (j in 0..4) {
            if (i == 2 && j == 2) {
                break@a
            }
            println("$i - $j")
        }
    }
    println("end")
}

// 标签与 函数体的结合使用

fun label(){

    run b@ {
        (0..10).forEach a@{ item ->
            if (item == 5)  {
                // lambda 表达式中的 break 是不起作用
                // break

                // 因为  lambda 实际上是一个函数，因此我们只能使用return 来结束它
                return@b

                // 但是我们仅仅想要当前正在执行的本次操作，需要借助标签来操作
                // return@a
            }
            println(item)
        }
    }

    (0..10).forEach a@{ item ->
        if (item == 5)  {
            // lambda 表达式中的 break 是不起作用
            // break

            // 因为  lambda 实际上是一个函数，因此我们只能使用return 来结束它
            return@a

            // 但是我们仅仅想要当前正在执行的本次操作，需要借助标签来操作
            // return@a
        }
        println(item)
    }
}

