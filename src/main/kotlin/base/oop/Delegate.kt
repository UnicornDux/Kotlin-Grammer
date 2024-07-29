package base.oop

class Delegrate {
  /**
   * 属性代理 :
   *
   */
  val hello by lazy {
    // 懒加载, (只有第一次访问到这个 hello 的变量的时候才会被初始化)
    //
    // val 类型的变量的代理类需要具有 getValue() 方法
    "hello"
  }
}
