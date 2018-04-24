package com.mephorce.repository;

import com.mephorce.model.Subtask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Developer on 02.08.2017.
 */
@Repository
public interface SubtaskRepository extends CrudRepository<Subtask, Long> {

    List<Subtask> findByContractorSecondName(String contractorFirstName);

    List<Subtask> findAllByIsApproved(boolean isApproved);

    List<Subtask> findAllByOrderByOrderInTask();

    List<Subtask> findAllByTaskId(Long taskId);
}