package flowx

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.channels.BufferOverflow

// SharedFlow 是一个热流，它允许将数据多次广播到多个收集器,
// 在生产者和消费者之间共享数据, 可以配置缓冲区
//
fun sharedFlow()  = runBlocking{
  println("--------------sharedFlow------------")
  // 构建一个 SharedFlow 流
  // 1.由于是热流，因此数据生产与是否有订阅者无关
  // 2.可以配置缓冲区来为订阅者缓冲数据
  // 3.实际的缓冲区的大小 = replay + extraBufferCapacity
  val state = MutableSharedFlow<String>(
    // 数据重播区的大小(可以为后来的订阅者缓冲的数据大小)
    replay = 1,
    // 缓冲区的大小
    extraBufferCapacity = 1,
    // 缓冲区溢出时的处理策略
    onBufferOverflow = BufferOverflow.SUSPEND, // 挂起(不丢弃数据，等待消费)
    // onBufferOverflow = BufferOverflow.DROP_LATEST, // 丢弃最新的数据
    // onBufferOverflow = BufferOverflow.DROP_OLDEST, // 丢弃最老的数据
  )

  launch {
    state.onEach {
      delay(10)
      println(it)
    }.collect()
  }

  launch {
    repeat(100) {
      state.emit("work$it")
    }
  }
}
