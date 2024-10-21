package coroutines

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.cancelAndJoin
import kotlin.system.measureTimeMillis

fun coroutine() {
   runAsync()
   cancel()
}

fun runAsync() = runBlocking {
  // 系统函数统计时间
  val time = measureTimeMillis {
    val one = async { doSomethingUsefulOne() } // 异步调用，返回结果
    val two = async { doSomethingUsefulTwo() } // 异步调用，返回结果
    // 等待异步执行完成(await 调用会挂起当前线程，等待执行结果完成后，通过调用 resume 恢复挂起前的状态)
    println("The answer is ${one.await() + two.await()}")
  }
  println("complete is $time ms")
}

// 携程 coroutinues 调用的方法需要用 suspend 修饰, 告诉编译器此函数可以被挂起

suspend fun doSomethingUsefulOne(): Int {

  delay(1000L) // pretend we are doing something useful here
  return 13
}

suspend fun doSomethingUsefulTwo(): Int {
  delay(1000L) // pretend we are doing something useful here, too
  return 29
}

fun cancel() = runBlocking {
  val startTime = System.currentTimeMillis()
  val isActive = true
  val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while(isActive) { // cancellable computation loop
      // print a message twice a second
      if (System.currentTimeMillis() >= nextPrintTime) {
        println("job: I'm sleeping ${i++}")
        nextPrintTime += 500L
      }
    }
  }
  delay(1300L) // delay a bit
  println("main: I'm tired of waiting")
  job.cancelAndJoin() // cancels the job and waits for it's completion
  println("main: Now I can quit.")
}
