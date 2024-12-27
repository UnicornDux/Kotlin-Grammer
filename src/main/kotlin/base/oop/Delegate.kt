package base.oop

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

/*
 * 自定义一个委托, 需要实现 getValue() 和 setValue() 方法
 *
 */

class SmartDevice {
  var work by RangerRegulator(initValue = 10, minValue = 0, maxValue = 100)
}

class RangerRegulator(initValue: Int, val minValue: Int, val maxValue: Int): ReadWriteProperty<Any, Int> {
  var value = initValue

  override fun getValue(thisRef: Any, property: KProperty<*>): Int {
    return value
  }

  override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
    if (value in minValue..maxValue) {
      this.value = value
    }
  }
}
