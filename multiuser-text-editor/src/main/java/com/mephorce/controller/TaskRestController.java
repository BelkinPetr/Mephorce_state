package com.mephorce.controller;

import com.mephorce.model.Subtask;
import com.mephorce.model.Task;
import com.mephorce.repository.SubtaskRepository;
import com.mephorce.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rest controller for work with tasks.
 * <p>
 * Created by Developer on 01.08.2017.
 */
@RestController
@RequestMapping(path = "/rest/")
public class TaskRestController {
    private static final Logger logger = Logger.getLogger(TaskRestController.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;

    @RequestMapping(path = "/task/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllTasks() {
        logger.info("Get all tasks");

        return (List<Task>) taskRepository.findAll();
    }

    @RequestMapping(path = "/subtask/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subtask> getAllSubtasks() {
        logger.info("Get all subtasks");

        return (List<Subtask>) subtaskRepository.findAll();
    }

    @RequestMapping(path = "/subtask/task-id/{task_id}", method = RequestMethod.GET, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public List<Subtask> getSubtasksByTaskId(@PathVariable("task_id") Long taskId) {
        logger.log(Level.INFO, "Get all subtasks by task id: {}", taskId);

        return subtaskRepository.findAllByTaskId(taskId);
    }

    @RequestMapping(path = "/subtask/{id}", method = RequestMethod.GET, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public Subtask getSubtaskById(@PathVariable("id") Long id) {
        logger.log(Level.INFO, "Get subtask by id: {}", id);

        return subtaskRepository.findOne(id);
    }

    @RequestMapping(path = "/subtask/", method = RequestMethod.POST, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public Subtask saveSubtask(@RequestBody Subtask subtask) {
        logger.log(Level.INFO, "Save subtask: {}", subtask);

        return subtaskRepository.save(subtask);
    }

    @RequestMapping(path = "/subtask/", method = RequestMethod.PUT, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public Subtask updateSubtask(@RequestBody Subtask subtask) {
        logger.log(Level.INFO, "Update subtask: {}", subtask);

        return subtaskRepository.save(subtask);
    }

    @RequestMapping(path = "/subtask/{id}", method = RequestMethod.DELETE, produces = MediaType
            .APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSubtaskById(@PathVariable("id") Long id) {
        logger.log(Level.INFO, "Delete subtask by id: {}", id);

        subtaskRepository.delete(id);
    }
}
