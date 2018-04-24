package com.mephorce.controller;

import com.mephorce.model.Contractor;
import com.mephorce.model.Subtask;
import com.mephorce.model.Task;
import com.mephorce.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller responsible for work with views of tasks.
 * <p>
 * Created by Developer on 03.08.2017.
 */
@Controller
public class TaskViewController {
    private static final Logger logger = Logger.getLogger(TaskViewController.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/task/{taskId}")
    public ModelAndView getTask(@PathVariable("taskId") Long taskId) {
        ModelAndView taskView = new ModelAndView("task");
        Task task = Optional.ofNullable(taskRepository.findOne(taskId)).orElse(getTaskStub(taskId));
        logger.log(Level.INFO, "Got task : {}", task);

        taskView.addObject("currentTask", task);
        return taskView;
    }

    /**
     * Stub for get task if not exists if storage.
     *
     * @return
     */
    private Task getTaskStub(Long id) {

        Task task = new Task();
        task.setId(id);
        task.setName("Технический текст с английского на русский");
        List<Subtask> subtasks = new ArrayList<>();
        Subtask subtaskFirst = new Subtask();
        subtaskFirst.setColor("Зеленый");
        subtaskFirst.setContractor(new Contractor("Александр", "Неизвестный", subtasks));
        subtaskFirst.setId(45677L);
        subtaskFirst.setTask(task);
        subtaskFirst.setSubtaskContent("Views model pages in our applications and allow us to modify and predefine " +
                "their behaviour by defining them as beans.  Views are in charge of rendering the actual HTML " +
                "interface, usually by the execution of some template engine like JSP (or Thymeleaf).");
        subtasks.add(subtaskFirst);

        Subtask subtaskSecond = new Subtask();
        subtaskSecond.setColor("Красный");
        subtaskSecond.setContractor(new Contractor("Маргарита", "Павлова", subtasks));
        subtaskSecond.setId(45678L);
        subtaskSecond.setTask(task);
        subtaskSecond.setSubtaskContent("ViewResolvers are the objects in charge of obtaining View objects for a " +
                "specific operation and locale.  Typically, controllers ask ViewResolvers to forward to a view with a" +
                " specific name (a String returned  by the controller method), and then all the view resolvers in the" +
                " application execute in ordered chain until one of them is able to resolve that view,   in which " +
                "case a View object is returned and control is passed to it for the renderization of HTML.");
        subtasks.add(subtaskSecond);
        task.setSubtasks(subtasks);

        return task;
    }

}
