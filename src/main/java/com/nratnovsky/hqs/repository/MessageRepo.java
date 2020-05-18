package com.nratnovsky.hqs.repository;

import com.nratnovsky.hqs.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag);
}
