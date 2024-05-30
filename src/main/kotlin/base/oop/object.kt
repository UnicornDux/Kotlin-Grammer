
/**
 * object 关键字的使用场景
 */


fun objects {

  // kotlin 中的单例中方法的调用可以省略实例，直接使用类进行调用即可
  O.find()
  println(O.a)

  // 函数参数为接口时，需要使用对象表达式来传递参数，
  // 需要借助 object 传递参数 (低层使用的 java 的 lambda 表达式)
  // 值得注意的时，这里并不会转换为 lambda 表达式
  request(object: CallBack{
    override fun loading() {
      
    }
  });

  // 如果我们在 java 中的 lambda 接口在 kotlin 中被使用，
  //
  // request( object: JavaInterface { 
  //
  // })
  //
  // 可以简化为下面的写法，这是 kotlin 提供对 java lambda 的兼容
  //
  // request {
  // 
  // }
}


// 1. 创建单例
//
// 单例也是一个类，可以进行接口实现和抽象类的继承
//
object O: Query {
  // 使用 object 关键字声明的单例是饿汉模式
  /*
  public class O {
    private O(){}
    public static final O INSTANCE;
    static {
      INSTANCE = new O();
    }
  }
  */

  override fun find():String {
    return "query"
  }

  // 单例中也可以使用 const 关键字
  const val a =  10

}

interface Query {
  fun find():String;
}


// 2.对象表达式
//  用于创建接口的匿名实现
//  
interface CallBack {
  fun loading()
}

fun request(callback:CallBack) {
  callback.loading()
}
