package base.oop

fun extendFunction() {
  println(10.multiple(5))

  // infix 修饰的简写
  // 将这个扩展函数定义为中缀表达式
  println(10 multiple 5)

  // 重复这个字符 40 次
  println("*".multiple(40))
}

/**
 * > 扩展方法的应用
 *
 *  扩展某个类型的一些方法，例如常用的与某个类的一些方法，这时候我们
 *  应该想到这些方法能不能直接赋予这个类型，但是标准库中定义的内容又
 *  不能修改
 *
 * > 这时候如果在当前模块中扩展这些类型。直接让这些方法属于这个类
 *
 * > 有时候可以把扩展函数定义为中缀表达式
 *
 *
 *
 * > 扩展函数在 java 中怎么完成调用呢 ？
 *
 *   反解字节码可以看出来，扩展函数的实现是在类中定义了静态方法
 *   增加了一个参数，当前的参数就是扩展类型的实例
 *
 *   public final static String multiple(String receiver, int times) {
 *
 *      // 函数内部的 this 使用 receiver 这个实例来替代
 *
 *   }
 */

fun String.multiple(times: Int): String {
  val build = StringBuffer()
  for (i in 0 until times) {
    build.append(this)
  }
  return build.toString()
}

// 扩展函数,某个指定类型扩展方法，只有这样的类型才能调用
fun Int.sum(a: Int): Int {
  // 在函数内部可以使用 this 拿到调用这个函数的（Int) 对象引用
  return this + a
}

// 如果这样的函数只有一行的表达式，则可以简化
fun Int.sub(a: Int) = this - a

// 使用 infix 关键字对<扩展函数(只能对扩展函数使用)> 标记的时候
// 使用的时候，则可以省略为 20.multiple(5) -> 20 multiple 5
infix fun Int.multiple(a: Int): Int = (this * a)
