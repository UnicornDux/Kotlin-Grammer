package func

/**
 * apply 函数的定义
 * @kotlin.internal.InlineOnly
 * public inline fun <T> T.apply(block: T.() -> Unit): T { block(); return this }
 */

fun useApply() {
  // apply 接受一个关联函数作为参数，内部返回返回当前实例的引用
  findPerson()?.apply {
    // 在内部可以像在对象的内部一样使用对象的属性，方法
    work()
  }
}
