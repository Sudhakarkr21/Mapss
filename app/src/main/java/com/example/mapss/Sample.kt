package com.example.mapss

import kotlin.properties.Delegates

class Sample {
    var nanty by Delegates.notNull<Int>()
    var name : String? = "sudhakar"
    var age : Int = 10

    var lamda1 : (Int , Int) -> Int {
        param : Int,param : Int ->
    }
    fun d(){
        val d = 10 + nanty;
    }

    fun display() : String = "Name = $name Age = $age"
}

class Display{

    fun main() {
        val numbers = mutableListOf("one", "two", "three")
        val countEndsWithE = numbers.apply {
            add("four")
            add("five")
            count { it.endsWith("e") }
        }
        println("There are $countEndsWithE elements that end with e.")
    }

    fun display() {
        val cardNumber: Int? = 1

        val a = cardNumber?.let {
            val b = it + 1
            // Everything executed inside this block will be null safe.
            print("data $b")
        }

        val list = listOf(1,4,57,7,3,34,34)
        print(list.groupBy {  })

        print(a)

        for (i in 1..5){
            print(i)
        }
    }

    fun <T> getGenerics(t : T) : T{
        return t
    }

    fun fibocci(count : Int) : ArrayList<Int>{
        var number1 = 0
        var number2 = 0
        var number3 = 0

        val arrayList = ArrayList<Int>()
        for (i in 2..count){
            number3 = number1 + number2
            number1 = number2
            number2 = number3
            arrayList.add(number3)
        }

        return arrayList;
    }

    fun armstrongNumber() : Int{

        var sumOfTemp = 0
        var a = 0;
        var temp = 0;
        var number = 153
        temp = number
        while (number > 0){
            a = number % 10
            number /= 10
            sumOfTemp += a * a * a;
        }
        
        return sumOfTemp
    }
}