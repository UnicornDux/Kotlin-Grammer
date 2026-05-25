package base

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import java.io.*
import func.andThen
import func.compose
import func.curried
import func.partialFirst
import func.partialSecond
import func.findListNode
import func.findTreeNode
import func.factorial
import func.ListNode
import func.TreeNode

class UseTest : FunSpec({
  context("use resource management") {
    test("closes resource on success") {
      val file = File.createTempFile("test", ".txt")
      file.writeText("hello kotlin")
      val reader = BufferedReader(FileReader(file))
      val content = reader.use { it.readText() }
      content shouldBe "hello kotlin"
      shouldThrow<IOException> { reader.read() }
    }

    test("closes resource on exception") {
      val reader = StringReader("data")
      shouldThrow<RuntimeException> {
        reader.use { throw RuntimeException("boom") }
      }
      shouldThrow<IOException> { reader.read() }
    }
  }

  context("function composition") {
    test("andThen composes left to right") {
      val add5 = { i: Int -> i + 5 }
      val mul2 = { i: Int -> i * 2 }
      val add5ThenMul2 = add5 andThen mul2
      add5ThenMul2(10) shouldBe 30
    }

    test("compose composes right to left") {
      val add5 = { i: Int -> i + 5 }
      val mul2 = { i: Int -> i * 2 }
      val add5ComposeMul2 = add5 compose mul2
      add5ComposeMul2(10) shouldBe 25
    }
  }

  context("currying and partial application") {
    test("curried transforms three-arg function") {
      val sum = { a: Int, b: Int, c: Int -> a + b + c }
      sum.curried()(1)(2)(3) shouldBe 6
    }

    test("partialFirst fixes first argument") {
      val greet = { greeting: String, name: String -> "$greeting, $name!" }
      greet.partialFirst("Hello")("Kotlin") shouldBe "Hello, Kotlin!"
    }

    test("partialSecond fixes second argument") {
      val divide = { a: Int, b: Int -> a / b }
      divide.partialSecond(2)(10) shouldBe 5
    }
  }

  context("linked list recursion") {
    test("findListNode returns node with matching value") {
      val list = ListNode(1, ListNode(2, ListNode(3, null)))
      val found = findListNode(list, 2)
      found shouldBe ListNode(2, ListNode(3, null))
    }

    test("findListNode returns null when value not found") {
      val list = ListNode(1, ListNode(2, null))
      findListNode(list, 99) shouldBe null
    }

    test("findListNode returns null for empty list") {
      findListNode(null, 1) shouldBe null
    }
  }

  context("binary tree recursion") {
    test("findTreeNode returns node with matching value") {
      val tree = TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3)
      }
      val found = findTreeNode(tree, 3)
      found shouldBe TreeNode(3)
    }

    test("findTreeNode returns null when value not found") {
      val tree = TreeNode(1).apply {
        left = TreeNode(2)
        right = TreeNode(3)
      }
      findTreeNode(tree, 99) shouldBe null
    }
  }

  context("factorial") {
    test("returns 0 for 0") {
      factorial(0L) shouldBe 0L
    }

    test("returns 1 for 1") {
      factorial(1L) shouldBe 1L
    }

    test("computes correctly for larger numbers") {
      factorial(5L) shouldBe 120L
    }
  }
})
