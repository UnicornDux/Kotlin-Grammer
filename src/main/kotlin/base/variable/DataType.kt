package base.variable

fun variable() {
  // simpleType();
  // stringType()
  // arrayType()
  listType()
  // mapType()
  // pairType()
}

// 定义简单类型
fun simpleType() {
  // 定义变量, 具有类型推断的能力
  var a = 1

  // 默认为 Double 类型
  var k = 1.2
  // 可以通过后缀转换为 float
  var l = 1.2F

  // 字面值默认的类型推断出来是 int, 如果想要缩小范围，可以指定
  var b: Byte = 1

  // 小的数据类型不能直接转化为大类型 byte -> int (这是错误的)
  // 这是两种完全不同的类型
  // var x:Int = b

  // 底层还是 System.out.println()
  println(b)
  // 定义常量
  val c: String = "hello"

  // 字符串与数字不能直接相加
  println(a.toString() + ":" + c)
  // 使用字符串模板
  println("$a : $c")
}

fun stringType() {
  val s = "hello"
  // 可以使用下标操作字符序列
  println(s[0])
  println(s.get(1))

  // 可以遍历字符序列
  for (c in s) {
    println(c)
  }

  // 打印，对字符串中的字符序列进行排序, 同时去掉重复的元素
  println(s.toSortedSet())

  // 字符串模板
  val say = "$s world"
  println(say)

  // 多行字符串模板
  val multi =
      """
        ------------------------------------
        kotlin
        $say
    """.trimIndent()

  println(multi)
}

// 定义复杂类型
fun arrayType() {

  // 定义一个数组类型
  // val x:Array<Int> = arrayOf(1, 3, 3, 5)
  val x: IntArray = intArrayOf(1, 3, 5, 7)

  // 指定数组的容量, 这里指定容量，初始化后得到的每个元素的初始值都是 0
  val b = IntArray(10)
  for (v in b) {
    println(v)
  }
}

fun listType() {
  println("---------------list--------------")
  // 不可变list, 初始化之后就固定了, 不能修改，只能获取
  val list = listOf<String>("full", "list")
  println(list.get(0))
  println(list[1])

  // 可变类型
  val mutList = mutableListOf<String>("mut", "list", "of")
  mutList.add("ok")
  println(mutList.get(0))
  println(mutList[1])
  // 清除所有元素
  // mutList.clear()

  // 移除某个元素
  // mutList.remove("ok")
  println(mutList)

  // 判断 list 是否为空, 内置了很多函数可以对 list 的操作简化
  println("isEmpty" + mutList.isEmpty())

  // 遍历
  for (s in mutList) {
    print(s + " ")
  }
  println()
  mutList.forEach { print(it + " ") }
  println()
}

// map, 基本就是构造上的差异, 用法与 java 类似
fun mapType() {
  println("---------------map---------------")
  // 非可变的 map
  val map = mapOf("hello" to "world")

  for (entry in map) {
    println("${entry.key} :: ${entry.value}")
  }

  // 可变的 map
  val mutMap = mutableMapOf(3423 to "true")
  println(mutMap[3423])
}

// 元组, 用于临时存储一些数据
fun pairType() {

  println("---------------tuple-------------")
  // 二元元组
  val pairs = Pair<String, Int>("122", 1)
  println(pairs.first)
  println(pairs.second)

  // 三元组
  val triple = Triple<String, Int, Float>("hello", 1, 1.3f)
  println(triple.first)
  println(triple.second)
  println(triple.third)
}
