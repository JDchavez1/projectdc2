package com.example.projectdc.contoller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")

class practica {
    @GetMapping
    fun index():String{
        val num1: Int = 50
        val num2: Int = 50

        val suma = num1 + num2
        return "La suma es: $suma"

    }

    @GetMapping("{age}")
    fun indexAge(@PathVariable("age") age: Long): String?{
        if (age>=18){
            return "Eres mayor de edad"

        }else{
            return "Eres menor de edad"
        }
    }
}