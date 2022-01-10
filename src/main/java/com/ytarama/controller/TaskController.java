package com.ytarama.controller;

import com.ytarama.entity.State;
import com.ytarama.entity.Task;
import com.ytarama.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getAllTask")
    public ResponseEntity<List<Task>>  getAllTask(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/getTaskByState")
    public ResponseEntity<List<Task>> getTaskByState(@RequestBody State state){
        return ResponseEntity.ok(taskService.getByState(state));
    }

    @PostMapping("/createTask")
    public  ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task taskdb = taskService.updateTask(task);
        if (taskdb == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskdb);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id){
        Task task = taskService.getById(id);
        if (task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
