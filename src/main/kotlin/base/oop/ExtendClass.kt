package base.oop




// kotlin 中的类默认的修饰符是 final, 是不允许被继承的
// 如果想要继承一个类，则这个类需要是 open 来修饰
open class E(name: String, age: Int) {

    constructor(name: String):this(name, 11)
}

// F 类继承了 E 类, 继承多个类的时候，使用 `,` 隔开
class F : E {

    // 继承类如果没有主构造方法， 需要最终执行到父类的构造方法
    // constructor(name: String, age: Int) : super(name, age)

    // 如果父类没有主构造，则需要执行父类的次构造
    constructor(name: String):super(name)
}


// 抽象类
abstract class H (){
    // 抽象类中可以有普通的方法
    // 抽象类中的抽象方法没有方法体，当一个类继承了一个抽象类，
    // 需要实现这个抽象方法
    abstract fun click()
}


