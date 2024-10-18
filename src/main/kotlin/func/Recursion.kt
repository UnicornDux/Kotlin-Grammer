package func

// kotlin 中一些尾递归可以通过 tailrec 关键字让编译器帮你优化为迭代的方式执行
// 什么样的递归是尾递归 ?
// 在递归的时候没有进行其他的操作
// 在一个非尾递归的程序中使用 tailrec 没有任何效果, 并且编译器会给你一个警告

data class ListNode(
  val value: Int,
  var next: ListNode?,
)

tailrec fun findListNode(
  head: ListNode?,
  value: Int,
): ListNode? {
  head ?: return null
  if (head.value == value) return head
  return findListNode(head.next, value)
}

// 这是伪递归
fun factorial(n: Long): Long {
  if (n == 1L || n == 0L) {
    return n
  }
  // 递归的时候有运算，不算尾递归
  return n * factorial(n - 1)
}

// 树的遍历
//
data class TreeNode(val value: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

// 递归时不是单独的操作，因此也不是尾递归
fun findTreeNode(
  root: TreeNode?,
  value: Int,
): TreeNode? {
  root ?: return null
  if (root.value == value) return root
  return findTreeNode(root.left, value) ?: return findTreeNode(root.right, value)
}
