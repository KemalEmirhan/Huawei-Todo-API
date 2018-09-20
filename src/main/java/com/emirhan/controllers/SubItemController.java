package com.emirhan.controllers;

import com.emirhan.models.Item;
import com.emirhan.models.SubItem;
import com.emirhan.models.Todo;
import com.emirhan.repositories.ItemRepository;
import com.emirhan.repositories.SubItemRepository;
import com.emirhan.repositories.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;

@RestController
@CrossOrigin(origins = "/**")
@RequestMapping(value = "/api/todos")
public class SubItemController {

    @Autowired
    private SubItemRepository subItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TodoRepository todoRepository;

    Date date = new Date();

    /**
     * It does create subItem
     *
     * @param todoId
     * @param itemId
     * @param subItemRequest
     * @return Created SubItem Object
     */
    @RequestMapping(value = "/{todoId}/items/{itemId}/subItems", method = RequestMethod.POST)
    public ResponseEntity<SubItem> createItem(@Valid @PathVariable("todoId") ObjectId todoId, @Valid @PathVariable("itemId") ObjectId itemId,
                                              @RequestBody SubItem subItemRequest) {

        Todo todo = todoRepository.findBy_id(todoId);
        Item item = itemRepository.findBy_id(itemId);

        SubItem subItem = new SubItem();
        subItem.set_id(ObjectId.get());
        subItem.setName(subItemRequest.getName());
        subItem.setDescription(subItemRequest.getDescription());
        subItem.setDeadline(subItemRequest.getDeadline());
        subItem.setCompleted(false);
        subItem.setCreatedAt(date);
        subItem.setUpdatedAt(date);

        subItemRepository.save(subItem);

        if(item.getSubItems() != null) {
            item.getSubItems().add(subItem);
        } else {
            item.setSubItems(Arrays.asList(subItem));
        }

        itemRepository.save(item);

        return new ResponseEntity<SubItem>(subItem, HttpStatus.OK);

    }

    /**
     * It does update subItem
     *
     * @param todoId
     * @param itemId
     * @param subId
     * @param subItemRequest
     * @return Updated SubItem Object
     */
    @RequestMapping(value = "/{todoId}/items/{itemId}/subItems/{subId}", method = RequestMethod.PUT)
    public ResponseEntity<SubItem> updateItem(@Valid @PathVariable("todoId") ObjectId todoId, @Valid @PathVariable("itemId") ObjectId itemId,
                                              @Valid @PathVariable("subId") ObjectId subId,@Valid @RequestBody SubItem subItemRequest) {

        Todo todo = todoRepository.findBy_id(todoId);
        Item item = itemRepository.findBy_id(itemId);

        SubItem subItem = subItemRepository.findBy_id(subId);

        if (todo != null) {
            subItem.setName(subItemRequest.getName());
            subItem.setDescription(subItemRequest.getDescription());
            subItem.setDeadline(date);
            subItem.setUpdatedAt(date);

           if (subItemRequest.isCompleted()) {
               item.setCompleted(true);
               subItem.setCompleted(true);

               itemRepository.save(item);
           }

            return new ResponseEntity<SubItem>(subItemRepository.save(subItem), HttpStatus.OK);

        } else {
            return new ResponseEntity<SubItem>(HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * It does delete subItem with given id
     * @param id
     */
    @RequestMapping(value = "/subItems/{subId}", method = RequestMethod.DELETE)
    public void deleteSubItemById(@Valid @PathVariable("subId") ObjectId id) {
        subItemRepository.deleteById(id.toHexString());
    }

}
