package com.cw.myframework.testAct

import kotlinx.coroutines.*

class Coroutines {

    fun test(){
        print("----xxx1")
        GlobalScope.launch{

            print("----xxx2")
            withContext(Dispatchers.IO){
                print("--->1")
                delay(100)
                print("--->2")
            }
            print("----xxx2")
        }
        print("----xxx3")


    }

    fun launchEmptys() {
        launchEmpty {

        }

    }

    fun launchEmpty( block:suspend () -> Unit) {
    }


    suspend fun testCor(){

    }

    suspend fun testCorDelay1(){
        delay(100)
    }

    suspend fun testCorDelay2(ss :String):String{


        print("---")
//        withContext(Dispatchers.IO){
//            print("---xxxxxxxx")
//        }
        delay(200)
        return "123"
    }

    private fun testsss(){
        runBlocking {
            read()
            print("---xxxxxxxx")
        }
    }

    private suspend fun read(){
        Thread.sleep(100)

    }
}
