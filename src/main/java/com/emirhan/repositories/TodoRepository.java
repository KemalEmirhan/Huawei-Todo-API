package com.emirhan.repositories;

import com.emirhan.models.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {

    Todo findBy_id(ObjectId _id);

}
