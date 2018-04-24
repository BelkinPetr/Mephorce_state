package com.mephorce.repository;

import com.mephorce.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Developer on 02.08.2017.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByNameLike(String name);
}