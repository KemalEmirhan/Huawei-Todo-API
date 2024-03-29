package com.emirhan.controllers;

import com.emirhan.models.Todo;
import com.emirhan.repositories.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "/**")
@RequestMapping(value = "/api")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    private Date date = new Date();


    /**
     * It does create a new record and save it into Todos collection
     *
     * @return saved data
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public Todo createTodo(@Valid @RequestBody Todo todo) {

        todo.set_id(ObjectId.get());
        todo.setCreatedAt(date);
        todo.setUpdatedAt(date);

        repository.save(todo);

        return todo;
    }

    /**
     * It does list all objects from Todos collection
     *
     * @return list of todos
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }


    /**
     * It does find one object with given id
     *
     * @param id
     * @return single object
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public Todo getTodoById(@Valid @PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }


    /**
     * It does update object with given id from Todos collection
     *
     * @param id
     * @return updated object
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
    public Todo updateTodo(@Valid @PathVariable("id") ObjectId id, @Valid @RequestBody Todo todoRequest) {

        Todo todo = repository.findBy_id(id);
        todo.setUpdatedAt(date);
        todo.setName(todoRequest.getName());

        return repository.save(todo);
    }


    /**
     * It does delete object with given id parameter from Todos collection
     *
     * @param id
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public void deleteTodoById(@Valid @PathVariable ObjectId id) {
        repository.deleteById(id.toHexString());
    }


}
