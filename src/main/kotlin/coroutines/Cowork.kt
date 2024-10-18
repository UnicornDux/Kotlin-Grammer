package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runWork() {
  println("------------coroutines------------------")
  lauchFunc()
  asyncFunc()
  scopeFunc()
  contextFunc()
}

/** kotlin 中的 协程 */

// 1. 使用 runBlocking 顶层函数，开启一个协程，
// 这种方式是一种阻塞的方式，一般不会直接使用，较多使用在单元测试里面
//
fun lauchFunc() = runBlocking {
  // 启动一个协程
  val job = launch { println("runBlocking, work..") }
  job.join()
  println("last-hello")
}

fun asyncFunc() = runBlocking {
  val deferer = async {
    workLong()
    "async handle over"
  }
  val result = deferer.await()
  println(result)
  println("async work end")
}

// 2. 第二种适用协程的方式,
// 使用 GlobalScope 单例对象, 可以直接 launch 开启协程，或者 async 开启需要配合 await 获取结果
// 这种方式创建的协程的生命周期与应用程序限制，且不能取消，一般也不会轻易使用

fun scopeFunc() {
  GlobalScope.launch {
    workLong()
    println("globalscope work in")
  }
  println("global work end")
}

fun workLong() {
  Thread.sleep(10000)
}

// 3. 使用 CoroutineContext 来创建一个 CoroutineScope 对象来开启，管理协程
//

fun contextFunc() {
  val coroutineScope = CoroutineScope(Dispatchers.Default)
  // 开启协程
  coroutineScope.launch {
    workLong()
    println("contextFunc worked in")
  }
  println("contextFunc worked end")
}
