package com.emirhan.controllers;

import com.emirhan.models.Item;
import com.emirhan.models.Todo;
import com.emirhan.repositories.ItemRepository;
import com.emirhan.repositories.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/todos")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TodoRepository todoRepository;

    private Date date = new Date();


    /**
     * It does create a new item and save it into Item Collection
     *
     * @param id
     * @param itemRequest
     * @return Item Object
     */
    @RequestMapping(value = "/{todoId}/items", method = RequestMethod.POST)
    public ResponseEntity<Item> createItem(@Valid @PathVariable("todoId") ObjectId id, @RequestBody Item itemRequest) {

        Todo todo = todoRepository.findBy_id(id);

        Item item = new Item();
        item.set_id(ObjectId.get());
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item.setDeadline(itemRequest.getDeadline());
        item.setCompleted(false);
        item.setCreatedAt(date);
        item.setUpdatedAt(date);

        itemRepository.save(item);

        if(todo.getItems() != null) {
            todo.getItems().add(item);
        } else {
            todo.setItems(Arrays.asList(item));
        }

        todoRepository.save(todo);

        return new ResponseEntity<Item>(item, HttpStatus.OK);

    }

    /**
     * It does collect all items from its todo list
     *
     * @param id
     * @return List of items
     */
    @RequestMapping(value = "/{todoId}/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItems(@Valid @PathVariable("todoId") ObjectId id) {

        Todo todo = todoRepository.findBy_id(id);

        if (todo != null) {
            if (todo.getItems() != null) {
                return new ResponseEntity<List<Item>>(todo.getItems(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Item>>(new ArrayList<Item>(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
    }

    /**
     * It does collect one item from its todo list
     *
     * @param todoId
     * @param itemId
     * @return Item
     */
    @RequestMapping(value = "/{todoId}/items/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItemById(@Valid @PathVariable("todoId") ObjectId todoId, @Valid @PathVariable("itemId") ObjectId itemId) {
        Item item = itemRepository.findBy_id(itemId);

        if (item != null) {
            return new ResponseEntity<Item>(item, HttpStatus.OK);
        }

        return new ResponseEntity<Item>(HttpStatus.BAD_REQUEST);
    }

    /**
     * It does update item
     *
     * @param todoId
     * @param itemId
     * @param itemRequest
     * @return Updated Item Object
     */
    @RequestMapping(value = "/{todoId}/items/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<Item> updateItem(@Valid @PathVariable("todoId") ObjectId todoId, @Valid @PathVariable("itemId") ObjectId itemId,
                                           @Valid @RequestBody Item itemRequest) {

        Todo todo = todoRepository.findBy_id(todoId);
        Item item = itemRepository.findBy_id(itemId);

        if (todo != null) {
            item.setName(itemRequest.getName());
            item.setDescription(itemRequest.getDescription());
            item.setDeadline(date);
            item.setUpdatedAt(date);

            if (item.getSubItems() == null) {
                item.setCompleted(itemRequest.isCompleted());
            }

            todo.setItems(Arrays.asList(item));

            todoRepository.save(todo);

            return new ResponseEntity<Item>(itemRepository.save(item), HttpStatus.OK);

        } else {
            return new ResponseEntity<Item>(HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * It does delete item with given id
     * @param itemId
     */
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.DELETE)
    public void deleteItemById(@Valid @PathVariable("itemId") ObjectId itemId) {
        itemRepository.deleteById(itemId.toHexString());
    }

}
