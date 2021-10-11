package com.shuai

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        var proxy = ProxyFactory(IUserDao())
        var userDao = proxy.proxyInstance as UserDao
        userDao.save("00000", 111111111)
    }
}

// 维护一个目标对象
// 执行目标对象方法
class ProxyFactory(private val target: Any) {

    // 为目标对象生成代理对象
    val proxyInstance: Any
        get() = Proxy.newProxyInstance(
            target.javaClass.classLoader,
            target.javaClass.interfaces,
            object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
                    TODO("Not yet implemented")
                }
            }
        )
//        { proxy, method, args ->
//            println("开启事务")
//            for (item in args) {
//                println(item)
//            }
//
//            // 执行目标对象方法
//            var returnValue = method.invoke(target, *(args ?: emptyArray()))
////            var returnValue = method.invoke(target, args)
//
//            println("提交事务")
//            null
//        }

}

interface UserDao {
    fun save(msg: String, index: Int);
}

class IUserDao : UserDao {
    override fun save(msg: String, index: Int) {
        println("保存数据")
    }
}