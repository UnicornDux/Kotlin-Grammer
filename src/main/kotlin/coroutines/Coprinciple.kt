package coroutines

/**
 * 1. 协程被编译成状态机
 *
 *             (多个状态之间轮转)
 *   start ------->  状态机 🌀 -----------> 结束
 *                    |
 *                    ↓
 *
 *                 异常退出
 * 2. suspend 函数即状态转移
 *
 */

