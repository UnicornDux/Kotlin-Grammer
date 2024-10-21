package flowx

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

// kotlin 中的 flow

fun useFlow() {
  flowDeclare()
  collectFlow()
  flowApi()
  flowCombine()
  flowException()
  flatMapConcatFlow()
  flatMapMergeFlow()
  flatMapLatest()
  Thread.sleep(5000)
}

fun flowDeclare() {
  // 构建数据流
  val flowx = flow<String> {
    emit("hello")
    emit("flow")
  }

  val coroutineScope = CoroutineScope(Dispatchers.Default)
  coroutineScope.launch {
    flowx.collect({ value -> 
      println(value)
    })
  }
}

fun simpleFlow() : Flow<Int> = flow {
  for (i in 1 .. 3) {
    delay(100) // 计算数据的耗时
    emit(i)
  }
}

// flow 中产生的是普通的冷流，当没有订阅流的对象的时候，
// 代码被调用也不会发送数据()
fun collectFlow() = runBlocking {
  simpleFlow().collect { value ->
    // 使用 collect 方法收集流
    print("$value ")
  }
  println()
  println("--------------flatCollect------------")
}

fun flowApi() = runBlocking<Unit> {
  simpleFlow()
    .filter{ it % 2  == 0} // 过滤
    .map { it * it } // 映射
    .collect { print("$it ") }

  println()
  println("--------------flatApi------------")
}

// 异常处理
fun flowException() = runBlocking<Unit> {
  simpleFlow()
    .catch{ e -> println("Caught exception: $e") }
    .collect { print("$it ") }
  println()
  println("--------------flatException------------")
}

// 流的组合 
fun flowCombine() = runBlocking {
  val flowA = flowOf("A", "B", "C")
  val flowB = flowOf(1, 2, 3)

  flowA.zip(flowB) { a, b -> "$a:$b" }
    .collect{ print("$it ") }
  println()
  println("--------------flatCombine------------")
}

//
// ==============================================================================
// Flow Api 中提供了多种对嵌套 Flow 的扁平化操作函数，这些操作可以将嵌套的
// Flow<FLow<T>> 转换为单一的 Flow<T>
// ==============================================================================


// 流的展开
// flatMapConcat 按照原 Flow 中元素的顺序逐个处理每个内部 Flow, 
// 只有当前的内部 Flow 完全手机完毕后，才会手机下一个 Flow 
// 场景: 使用 FlatMapConcat, 当你需要保持内部 Flows 输出顺序与输入
//       顺序保持一致时。适用于有顺序依赖的任务，操作必须按照特定顺序
fun flatMapConcatFlow() = runBlocking {
  (1..4).asFlow().flatMapConcat{ value -> 
    flow { 
      emit(value * 10) 
      delay(100)
      emit(value * 20)
    }
  }.collect{ print("$it ") }
  println()
  println("--------------flatMapConcat------------")
}

// 流的合并 
// flatMapMerge 同时收集多个内部的 Flow, 并将他们的值合并到一个共同的 Flow 中
// 你可以设置并发收集的 Flow 的数量，默认是 DEFAULT_CONCURRENCY
// 场景：当需要高效处理，处理任务之间没有严格的顺序依赖时，使用 flatMapMerge
//       这适用于能够并行处理的情况，例如并行的网络请求或者数据库访问

fun flatMapMergeFlow() = runBlocking {
  // 输出的数据可能是随机的
  (1..4).asFlow().flatMapMerge { value ->
    flow {
      emit(value * 5)
      delay(100)
      emit(value * 7)
    }
  }.collect{ print("$it ")}
  println()
  println("--------------flatMerge------------")
}

// 流的截取
// flatMapLatest 在新的内部 Flow 开始手机时，会取消先前的内部 Flow 收集
// 每次源 Flow 发出一个新元素时，当前收集的内部 flow 将被取消，并开始
// 收集新的 Flow
// 场景: 使用flatMapLatest 当你只关心最新的异步操作结果, 并想要取消之前
//       的操作，这常用于用户输入的场景，比如搜索框的自动完成，用户的最后输入是最重要的
//
fun flatMapLatest() = runBlocking {
  (1..4).asFlow().onEach{ delay(100) }.flatMapLatest { value ->
    flow {
      emit(value * 3)
      delay(100)
      emit(value * 6)
    }
  }.collect { print("$it ") }
  println()
  println("--------------flatLatest------------")
}
