package base.oop


fun testEnum(){
    println(Menu.HOME)
    println(Menu.BUFFER.alias)
}


// 枚举可以实现接口
interface Operation {
    fun allocate()
}


// 枚举类型成为了类修饰符, 这里的 枚举的用法与 Java 中并没有什么不同
enum class Menu(val alias : String, address: String): Operation {
    HOME("home", "alibaba"){
        override fun allocate() {
            println(this.alias)
        }
    },
    BUFFER("buffer", "alix"){
        override fun allocate() {
           println(this.alias)
        }
    };
}

