package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.convertTodo
import com.example.todo.model.http.request.TodoReqDto
import com.example.todo.model.http.request.convertTodoDto
import com.example.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    val todoRepository: TodoRepository
) {

   // C
    fun create(todoReqDto: TodoReqDto) : TodoReqDto? {
        return todoReqDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepository.save(it)
        }?.let {
          TodoReqDto().convertTodoDto(it)
        }
    }

    // R
    fun read(index : Int) : TodoReqDto?{

        return todoRepository.findOne(index)?.let {
            TodoReqDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoReqDto> {
        
        return todoRepository.findAll().map {
            TodoReqDto().convertTodoDto(it)
        }.toMutableList()
    }


    // U

    fun update(todoReqDto: TodoReqDto) : TodoReqDto? {
        return todoReqDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepository.save(it)
        }?.let {
            TodoReqDto().convertTodoDto(it)
        }
    }



    // D

    fun delete(index : Int): Boolean {
        return todoRepository.delete(index)
    }

}