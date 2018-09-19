package com.emirhan.repositories;

import com.emirhan.models.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {

    Item findBy_id(ObjectId _id);
}
