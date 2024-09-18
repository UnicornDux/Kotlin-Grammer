package coroutines

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.util.concurrent.Executors
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.Result
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

// 1. createCoroutine : 创建协程
// 2. startCoroutine : 启动协程
// 3. suspendCoroutine: 挂起协程
// 4. Continuation 接口 : 运行控制类, 负责结果和异常的返回
// 5. CoroutineContext 接口, 运行上下文, 持有资源, 运行调度
// 6. ContinuationInterceptor 接口: 携程控制拦截器，可用来处理协程调度

// Continuation, 控制着协程运行，返回结果/ 抛出异常
// 中间有 resumeWith(result: Result<T>) 来完成协程结果的返回

// 普通的 continuation
class BaseContinuation : Continuation<Unit> {
  override val context: CoroutineContext = EmptyCoroutineContext

  override fun resumeWith(result: Result<Unit>) {}
}

// 允许传入自定义 context 的 continuation, 从而我们可以在 Context 中做一些事情
// 例如下面我们在自定义的 context 中对 continuation 进行了包装
// 从而可以让 resumeWith 的协程结果运行在我们指定的 UI 线程中
class ContextContinuation(
        override val context: CoroutineContext,
) : Continuation<Unit> {
  override fun resumeWith(result: Result<Unit>) {}
}

// 自定义context, 实现对 continuation 的包装, 让程序的 resumeWith 都运行在协程中
class AsyncContext : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
  override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
    UIContinuationWrapper(continuation.context.fold(continuation) { continuation, element ->
      if (element != this && element is ContinuationInterceptor) {
        element.interceptContinuation(continuation)
      } else {
        continuation
      }
    })
}

// 为了在协程中不会使用到外界的数据

class DownloadContext(
  val url: String,
) : AbstractCoroutineContextElement(Key) {
  companion object Key : CoroutineContext.Key<DownloadContext>
}

// 可以接受一个挂起函数作为参数
fun useCorountine(
  context: CoroutineContext = EmptyCoroutineContext,
  block: suspend () -> Unit,
) {
  // block.startCoroutine(BaseContinuation())
  // 换成自定义 context 的协程.
  // context 和 context 之间可以组合起来使用, (不同用途的context)
  block.startCoroutine(ContextContinuation(context + AsyncContext()))
}

class UIContinuationWrapper<T>(
  val continuation: Continuation<T>,
) : Continuation<T> {
  override val context = continuation.context

  override fun resumeWith(result: Result<T>) {
    SwingUtilities.invokeLater { continuation.resumeWith(result) }
  }
}

private val pool by lazy { Executors.newCachedThreadPool() }

class AsyncTask(
  val block: () -> Unit,
) {
  fun execute() = pool.execute(block)
}

// 这是一个通用的耗时操作, 可以是
// 进行网络请求
// 数据库操作等
suspend fun <T> costTimeOperation(block: CoroutineContext.() -> Result<T>) =
  suspendCoroutine<T> { continuation ->
    // 对 contiation 进行包装, 将 ui 变化都集中到  UI 线程中
    //
    // 这里将包装这个 continuation 的过程移动到自定义 context 中
    // val uiContinuation = UIContinuationWrapper(continuation)
    AsyncTask {
      log("异步任务开始")
      try {
        continuation.resumeWith(block(continuation.context))
      } catch (e: Exception) {
        println("封装异常")
        continuation.resumeWith(Result.failure(e))
      }
    }.execute()
  }

val LOGO_URL = "https://files.codelife.cc/wallhaven/full/28/wallhaven-283ry9.png"
// val LOGO_URL = "https://files.codelife.cc/wallhaven/full/28/wallhaven-283r.png"

fun show() {
  val frame = MainWindow()
  frame.title = "Coroutine@Unicorn"
  frame.setSize(1000, 600)
  frame.isResizable = true
  frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
  frame.init()
  frame.isVisible = true

  frame.onButtonClick {
    println("加载图片")
    /*
    // 使用异步的 api 来加载图片
    var request = HttpRequest
        .newBuilder()
        .uri(URI.create(LOGO_URL))
        .timeout(Duration.ofMinutes(1))
        .build()
    Http.client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray()).thenApply { response ->
      frame.setLogo(response.body())
    }
     */
    useCorountine(DownloadContext(LOGO_URL)) {
      try {
        val data = costTimeOperation { Http.loadImage(this[DownloadContext]!!.url) }
        log("设置图片")
        frame.setLogo(data)
      } catch (e: Exception) {
        println("捕获到异常: ${e.message}")
      }
    }
  }
}

object Http {
  val client = HttpClient.newHttpClient()

  fun loadImage(url: String): Result<ByteArray> {
    var request = HttpRequest
        .newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .build()
    val response = Http.client.send(request, HttpResponse.BodyHandlers.ofByteArray())
    if (response.statusCode() == 200) {
      return Result.success(response.body())
    } else {
      println("抛出异常")
      throw Exception("Http status: ${response.statusCode()}")
    }
  }
}

fun log(msg: String) {
  println("[${Thread.currentThread().name}] : $msg")
}
