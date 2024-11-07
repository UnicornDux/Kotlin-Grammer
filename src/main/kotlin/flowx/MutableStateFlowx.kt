package flowx

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// MutableStateFlow 是一种热流, 可以有多个流的订阅者
//

fun useStateFlow() {
  stateCollect()
  stateCollectLatest()
  stateCollectIndex()
  stateOnEach()
  stateTakeWhile()
  stateTakeIf()
  stateTakeUnless()
  stateEmit()
}

val coroutineScope = CoroutineScope(Dispatchers.Default)

fun stateCollect() = runBlocking {
  val x = MutableStateFlow(10)
  coroutineScope.launch {
    // 订阅数据，
    // collect 方法是一个挂起函数，因此必须要运行在协程作用域中，(如果放在主进程中会一直阻塞)
    //
    x.collect { value ->
      print("$value ")
    }
  }
  coroutineScope.launch {
    // 修改数据,
    x.value = 20
    delay(100)
    x.value = 30
    delay(100)
    x.value = 40
  }
  println()
  println("---------stateFlow-------------")
  delay(1000)
}

fun stateCollectIndex() = runBlocking {
  val x = MutableStateFlow(0)
  coroutineScope.launch {
    x.collectIndexed{ index, value ->
      println("$index _ $value")
    }
  }
  println()
  println("--------------collectIndex-----------")
  x.emit(20)
  delay(100)
  x.emit(10)
  delay(100)
  x.emit(40)
  delay(100)
}

fun stateOnEach() = runBlocking {

  val x = MutableStateFlow(1)

  val cancelable = x.onEach { println(it) }

  coroutineScope.launch {
    repeat(100)  {
      x.emit(10);
      delay(10)
    }
  }
  delay(100)
  cancelable.cancellable()
}

fun stateCollectLatest() = runBlocking {
  val x = MutableStateFlow(0)
  coroutineScope.launch {
    x.collectLatest { value ->  print("$value ") }
  }
  println()
  println("------------stateLatest------------")
  x.emit(30)
  delay(0)
  x.emit(20)
  delay(10)
  x.emit(10)
  delay(10)
}


// takeWhile 获取数据，从流中获取满足条件的值，直到遇到不满足条件的数据立即终止订阅
//
fun stateTakeWhile() = runBlocking {
  val x = MutableStateFlow(10)
  println()
  println("--------takeWhile------------")
  coroutineScope.launch {
    x.takeWhile { ele -> ele > 100 }.collect { x -> print("$x ") }
  }
  x.value = 200
  delay(100)
  x.value = 120
  delay(100)
  x.value = 100
  delay(100)
  x.value = 80
  delay(100)
  x.value = 200
  delay(100)
}

// takeIf
// TODO:: ?? 未预期的行为, 非 kotlinx.coroutine.flow 包中的内容
fun stateTakeIf() = runBlocking {
  val x = MutableStateFlow(20)
  println()
  println("--------takeIf------------")
  coroutineScope.launch {
    x.takeIf { key -> key.value > 10 }?.collect { key -> print("$key ") }
  }
  x.value = 20
  delay(100)
  x.value = 10
  delay(100)
  x.value = 20
  delay(1000)
}


// takeUnless false 时获取数据, true 时返回 null
// TODO: ?? 未预期的结果, 非 kotlinx.coroutine.flow 包中的内容
fun stateTakeUnless() = runBlocking {
  val x = MutableStateFlow(1)
  println()
  println("--------takeUnless--------")
  coroutineScope.launch {
    x.takeUnless { x -> x.value < 10 }?.collect { x -> print("$x ")}
  }
  x.value = 20
  delay(100)
  x.value = 10
  delay(100)
  x.value = 20
  delay(1000)
}


// 使用发送的方式修改 MutableStateFlow 的值
//
fun stateEmit() = runBlocking {
  val x = MutableStateFlow(10)
  coroutineScope.launch {
    x.collect { value -> print("$value ") }
  }
  println()
  println("----------- emit -----------")
  x.emit(20)
  delay(100)
}

fun stateEmitWhile() = runBlocking {}
