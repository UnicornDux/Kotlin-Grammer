package dsl

import kotlin.text.appendLine

open class Tag(val name: String) : Node {
  val children = ArrayList<Node>()
  val properties = HashMap<String, String>()

  // 添加属性
  operator fun String.invoke(value: String) {
    properties[this] = value
  }

  // 添加子节点
  operator fun String.invoke(block: Tag.() -> Unit) {
    children.add(Tag(this).apply(block)) 
  }

  // 添加一个文本子节点
  operator fun String.unaryPlus() {
    children.add(Text(this))
  }

  override fun render(): String {
    return StringBuilder()
      .append("<$name")
      .let { build ->
        if (this.properties.isNotEmpty()) {
          this.properties.forEach {
            build
              .append(" ${it.key}='${it.value}'")
          }
        }
        build
      }.append(">")
      .let { build ->
        children.map(Node::render).map(build::append)
        build
      }.append("</$name>")
      .toString()
  }
}


class Text(val content: String) : Node {
  override fun render() = content
}
