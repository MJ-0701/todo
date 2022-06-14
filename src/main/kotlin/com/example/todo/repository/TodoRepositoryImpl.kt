package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl( val todoDataBase: TodoDataBase) : TodoRepository {

    override fun save(todo: Todo): Todo? {

        // 1. index?

       return todo.index?.let { index ->// index 존재 -> 업데이트
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()

            }

        }?: kotlin.run { // index 없음 -> 생성
             todo.apply {
                this.index = ++todoDataBase.index // 데이터 베이스의 마지막 인덱스를 가져와서 하나 증가 (다음번호)
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDataBase.todoList.add(todo)
                this
            }
        }


    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

       return  try {
            todoList.forEach{
                save(it)
            }
            true
        }catch (e : Exception){
            false
        }
    }


    override fun delete(index: Int): Boolean {

        return findOne(index)?.let {
            todoDataBase.todoList.remove(it)
            true
        }?: kotlin.run {
            false
        }

    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}