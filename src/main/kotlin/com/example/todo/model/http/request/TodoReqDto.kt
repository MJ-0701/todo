package com.example.todo.model.http.request

import com.example.todo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoReqDto(

    @field:ApiModelProperty( // Swagger 전용 설명
        value = "DB INDEX",
        example = "1",
        required = false
    )
    var index : Int? = null,

    @field:NotBlank
    @field:ApiModelProperty(
        value = "일정 명",
        example = "일정관리",
        required = true
    )
    var title : String? = null,

    @field:ApiModelProperty(
        value = "일정 설명",
        example = "13시 스타벅스",
        required = false
    )
    var description : String? = null,

    @field:NotBlank
    @field:ApiModelProperty(
        value = "약속 시간",
        example = "2022-07-30 00:00:00",
        required = true
    )
    var schedule : String? = null,


    @field:ApiModelProperty(
        value = "생성 일자",
        required = false
    )
    var createdAt : LocalDateTime? = null,

    @field:ApiModelProperty(
        value = "수정정시간",
        required = false
    )
    var updatedAt : LocalDateTime? = null

){
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷에 맞지 않습니다.")
    fun validSchedule() : Boolean{
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e : Exception){
            false
        }
    }

}

fun TodoReqDto.convertTodoDto(todo: Todo) : TodoReqDto {

    return TodoReqDto().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}
