package com.example.todo.database

import java.time.LocalDateTime

data class Todo(
    var index : Int? = null, // 일정 index
    var title : String? = null, // 일정 title
    var description : String? = null, // 일정 내용
    var schedule : LocalDateTime? = null, // 일정 시간
    var createdAt : LocalDateTime? = null, // 생성시간
    var updatedAt : LocalDateTime? = null // 업데이트 시간
)