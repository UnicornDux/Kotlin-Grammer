import base.branch.branch
import base.function.function
import base.loop.loop
import base.method.method
import base.oop.data
import base.oop.oop
import base.oop.seal
import base.oop.testEnum
import base.variable.range
import base.variable.variable


fun main(args: Array<String>) {

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    // 测试变量的声明
    variable()

    // 区间
    range()

    // 测试分支结构
    branch()
    
    // 循环
    loop()

    // 函数调用
    function()

    // 类与面向对象
    oop()

    // 枚举类
    testEnum()

    // 密封类
    seal()

    // 数据类
    data()

    // 方法
    method()

}
