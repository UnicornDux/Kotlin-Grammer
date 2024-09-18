package coroutines

import kotlin.coroutines.createCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume

/**
 *  协程的创建和启动的 Api 一共有两组
 *
 *  fun <R, T> (suspend R.() -> T).createCoroutine(
 *    receiver: R,
 *    completion: Continuation<T>
 *  ): Continuation<Unit>
 *
 * ------------------------------------------------------
 *
 *  fun <R, T>(suspend R.() -> T).startCoroutine(
 *    receiver: R,
 *    completion: Continuation<T>
 *  )
 */

fun run() {
  val continuation =
    // 协程体, 继承了 suspend lambda
    suspend {
      println("coroutine start")
      5
    }.createCoroutine(object : Continuation<Int> { // completion callback
      override val context = EmptyCoroutineContext
      override fun resumeWith(result: Result<Int>) {
        println("coroutine End: $result")
      }
    })
  continuation.resume(Unit)
}
