package com.bobmitchigan.com

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}