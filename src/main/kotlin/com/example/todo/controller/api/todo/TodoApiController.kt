package com.example.todo.controller.api.todo

import com.example.todo.model.http.request.TodoReqDto
import com.example.todo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(description = "일정관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {

    @ApiOperation(value = "일정확인", notes = "일정 확인 GET API")
    @GetMapping("")
    fun read(
        @ApiParam(name = "index")
        @RequestParam(required = false) index : Int?,) : ResponseEntity<Any?>{
        return index?.let {
            todoService.read(it)

        }?.let {// 인덱스가 있을때
         return ResponseEntity.ok(it)
        }?: kotlin.run { // 인덱스가 없을때 readAll 로 리다이렉션 시키는 방법
            return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all")
                .build()
        }

    }

    @GetMapping("/all")
    fun readAll(): MutableList<TodoReqDto> {
        return todoService.readAll()
    }


    @PostMapping("")
    fun create(@RequestBody @Valid todoReqDto: TodoReqDto): TodoReqDto? {
        return todoService.create(todoReqDto)

    }

    @PutMapping("") // create = 201 update = 200 이 돼야하는데 현재는 무조건 200이 떨어지므로 리팩토링 할것. -> return ResponseEntity
    fun update(@RequestBody @Valid todoReqDto: TodoReqDto): TodoReqDto? {
        return todoService.update(todoReqDto)
    }

    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index : Int) : ResponseEntity<Any>{

        if(!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()

    }




}