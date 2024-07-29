// kotlin 中的伴生类 (主要用来取代java中的一些静态的功能)
//
// 1.@JvmField 主要用于伴生类中的字段，用于转化为静态公开的内容，方便与 java 语言交互
// 2.@JvmStatic 主要用于伴生类中的函数，用于转化为静态公开的函数，方便与 java 语言交互
//

fun companions() {
  //
  // 从伴生类中调用方法
  // 从反编译出来的原生 java 代码来看, 在 AC 中构建了一个静态内部类 Companion,
  // test 方法是实例方法，因此是通过静态对象 Companion 来调用 test() 方法
  //
  //
  AC.Companion.test()

  //
  // 方法体上添加 @JvmStatic 可以直接调用，
  AD.test()
  //
  //
  // 对于伴生类的变量的访问
  print(AF.Companion.a)
}

class AC {
  // 定义伴生类
  companion object {
    fun test() {
      println("test")
    }
  }

  // 编译出来的 java 代码类似于
  /*
  public static final Companion Companion = new Companion((DefaultCons....))
  public static final class Companion {
     fun final void test() { .... }
  }
  */
}

class AD {
  // 定义伴生类
  companion object {
    //
    // 添加上这个注解的时候，有什么不同 ?
    // 在生成的 java 代码中 AC 类多出来一个静态的 test() 方法
    // 主要用于与 java 代码交互的时候会有区别
    @JvmStatic
    fun test() {
      println("test")
    }

    // 反编译出来的 java 代码多出来一个静态的 test 方法
    /*
     @JvmStatic
     public static void test() { ... }

     public static final Companion Companion = new Companion((DefaultCons....))
     public static final class Companion {

        @JvmStatic
        fun final void test() { .... }
     }
    */
  }
}

class AF {
  // 定义成员
  companion object {
    // 当我们需要使用这个变量的时候 还是需要借助
    // Companion 中暴露的访问数据的方法
    val a = 1
  }

  // 反编译成 java 代码后
  /*
   *
  // 生成了私有静态常量，无法被外部使用
  private static final int a = 1

  public static final Companion Companion = new Companion((DefaultCons....))
  public static final class Companion {
     // Companion 内使用 public  getA 提供数据访问,
     fun final void getA() { return AF.a }
  }
   */
}

class AG {
  companion object {
    // const 关键字的用法,
    // 1. const 声明的变量在原生的 java 中是反编译为
    //    public static final int a = 1
    const val a = 1

    // 2. const 只能被用于基本类型
    // const val a = mapOf<String, String>() // 这会编译报错
    //
    // 如果我们想要这样的复杂对象成为公开的静态对象，可以与 java 进行交互
    // 需要使用 @JvmField 来表示对应的
    @JvmField val m = mapOf<String, String>()

    // 3. const 只能用于 val 变量，不能用于 var 变量
    // const var x = 10 // 这是编译无法通过的
    //
  }
}

class AH {
  // 伴生类可以有类名, 如果不像使用默认生成的 Companion 类名, 可以指定类名
  // 伴生类同样可以实现接口, 继承抽象类
  companion object Child : Cache {
    // 实现抽象方法
    override fun get(key: String): String = key
  }
}

interface Cache {
  fun get(key: String): String
}
