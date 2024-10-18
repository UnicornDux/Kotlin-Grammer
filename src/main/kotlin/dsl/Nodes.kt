package dsl

fun html(block: Tag.() -> Unit): Tag {
  return Tag("html").apply { block(this) }
}

