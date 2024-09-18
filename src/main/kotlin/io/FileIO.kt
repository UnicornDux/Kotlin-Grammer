package io

import java.io.BufferedReader
import java.io.FileReader
import java.io.File

/*
 * 1. IO 操作
 *
 */

fun io() {
  base_io()
  small_file()
}

fun base_io() {
  // kotlin 中读取文件
  /*
  val file = File("build.gradle.kt")
  val bufferReader = BufferedReader(FileReader(file))
  var line: String

  while (true) {
    line = bufferReader.readLine() ?: break
    println(line)
  }
  bufferReader.close()
  */

  // 基于实现 Closable 接口的方式，我们可以自动实现对应
  // 资源的自动关闭操作
  var file = File("build.gradle.kt")
  BufferedReader(FileReader(file)).use {
    var line: String

    while (true) {
      line = it.readLine() ?: break
      println(line)
    }
  }
}

// 如果是读取小文件
fun small_file() {
  File("build.gradle.kt").readLines().forEach(::println)
}
