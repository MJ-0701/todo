package com.example.todo.database

import com.example.todo.model.http.request.TodoReqDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo(
    var index : Int? = null, // 일정 index
    var title : String? = null, // 일정 title
    var description : String? = null, // 일정 내용
    var schedule : LocalDateTime? = null, // 일정 시간
    var createdAt : LocalDateTime? = null, // 생성시간
    var updatedAt : LocalDateTime? = null // 업데이트 시간
)

// 코틀린 dto 매핑 방법은 함수를 직접 만들어 주는 방법(자바의 빌더 메서드를 만들어 주는 방법과 동일)
// 모델 매퍼를 사용하는 방법 / 리플렉션을 사용하는 방법 이렇게 크게 3가지가 있다.
// 웬만하면 model mapper 나 코틀린 reflection 을 사용하기.,

// 코틀린 확장함수(익스텐션)
fun Todo.convertTodo(todoReqDto: TodoReqDto) : Todo {

    return Todo().apply {
        this.index = todoReqDto.index
        this.title = todoReqDto.title
        this.description = todoReqDto.description
        this.schedule = LocalDateTime.parse(todoReqDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:Ss"))
        this.createdAt = todoReqDto.createdAt
        this.updatedAt = todoReqDto.updatedAt
    }
}