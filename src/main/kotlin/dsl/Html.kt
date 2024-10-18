package dsl

// 编写一个 Html 对应的 dsl
//
interface Node {
  fun render(): String
}

fun dom() {
  Tag("html")
    .apply {
      properties["id"] = "HtmlId"
      children.add(Tag("head"))
    }.render()
    .let(::println)
}

fun domdsl() {
  html {
    "id"("htmlId")
    "head" {
      "id"("headId")
    }
    "body" {
      "a" {
        "href"("https://baidu.com")
        +"百度一下"
      }
    }
  }.render()
  .let(::println)
}
