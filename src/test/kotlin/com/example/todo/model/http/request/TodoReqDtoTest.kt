package com.example.todo.model.http.request

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import javax.validation.Validation
import javax.validation.Validator

internal class TodoReqDtoTest{

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoReqDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2022-07-30 00:00:00"

        }

        val result = validator.validate(todoDto)

        result.forEach {
            println(it.propertyPath.last().name)
            println(it.message)
            println(it.invalidValue)
        }

        Assertions.assertEquals(true, result.isEmpty()) // isEmpty -> 에러가 없으면 true 있으면 false

    }


}