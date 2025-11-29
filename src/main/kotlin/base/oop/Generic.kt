package base.oop;

// kotlin 中的泛型
fun runGeneric() {
  // 子类对象赋值给父类对象(协变)
  var producer: Producer<Human> = object : Producer<Male> {
    override fun generate(): Male {
      return Male()
    }
  }

  producer.generate()

  // 父类对象赋值给子类对象(逆变)
  var consumer: Consumer<Female> = object : Consumer<Human> {
    override fun accept(t: Human) {}
  }
  consumer.accept(Female())

  val result = Pipe3().randomOrDefault<Ktor> {
    Ktor("defaul")
  }
  println(result)
}

// 1. 与 java 中的泛型基本一致，但是表示方式不一样
//  > out 协变, 相当于 java 中的 <? extends T>
//  > in 逆变, 相当于 java 中的 <? super T>
//  > invariance 不变, 相当于 java 中的 T

interface Producer<out T> {
  fun generate(): T
}

interface Consumer<in T> {
  fun accept(t: T)
}

open class Human

class Male : Human()

class Female : Human()

// 2. kotlin 中可以在类申明过程中使用 in，out 修饰泛型，从而限定泛型的类型
//    > in 逆变，使用在类之上的时候限定了类中的泛型只能作为参数而不能作为返回值
//    > out 协变, 使用在类之上的时候限定了类中的泛型只能作为返回值，不能作为参数
class Pipe<in T> {
  fun next(t: T) {}
  // ❌ 不能作为返回值
  // fun comsume(): T {}
}

class Pipe2<out T>(_item: T) {
  val item: T = _item

  // ❌ 不能作为参数
  // fun next(t: T) {}
  fun comsume(): T? {
    return item
  }
}

// 3. reified 修饰泛型
//    > 在泛型类中使用 reified 修饰的泛型可以在运行时获取泛型的具体类型(Java 由于类型擦除而无法获取)
//    > 使用 xxx is T 的方式来判断类型
//    > reified 只能修饰 inline 函数
class Pipe3 {
  val data: List<Any> = listOf(
    Koa("koa"),
    Ktor("ktor"),
    Netty("netty"),
  )

  // 获取列表的类型，如果类型满足泛型类型就返回，否则执行 lambda 返回一个默认值
  inline fun <reified T> randomOrDefault(defaultAction: () -> T): T? {
    val random: Any = data.shuffled().first()
    return random.takeIf { it is T } as T? ?: defaultAction()
  }
}

data class Koa(val name: String)

data class Netty(val name: String)

data class Ktor(val name: String)

inline fun <reified T> getClass(): KClass<T> {
  return T::class
}

inline fun <reified T> getClass2(): Class<T> {
  return T::class.java
}

inline fun <reified T> checkType(value: Any) {
  if (value is T) {
    println("value is type ${T::class}")
  }else {
    println("value is not type ${T::class}")
  }
}






