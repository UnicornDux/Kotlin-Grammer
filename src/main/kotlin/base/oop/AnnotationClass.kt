package base.oop

fun anno() {}

// 注解类
annotation class Type

// 可以在注解类上定义默认的参数
@Retention(AnnotationRetention.SOURCE)
@Target(
  AnnotationTarget.CLASS,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.VALUE_PARAMETER,
  AnnotationTarget.EXPRESSION,
)
annotation class Log(
  val menu: String = "app",
)
