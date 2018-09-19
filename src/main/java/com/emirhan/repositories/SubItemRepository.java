package com.emirhan.repositories;

import com.emirhan.models.SubItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubItemRepository extends MongoRepository<SubItem, String> {

    SubItem findBy_id(ObjectId _id);

}
