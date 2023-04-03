package uz.pdp.dars1_vazifa2_codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;
import uz.pdp.dars1_vazifa2_codingbat.service.TaskContentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskContent {

    @Autowired
    TaskContentService taskContentService;

    @GetMapping("/getTaskContent")
    public ResponseEntity<List<TaskContent>> getTaskContent(){
        List<TaskContent> taskContent = taskContentService.getTaskContent();
        return ResponseEntity.ok(taskContent);
    }




    @GetMapping("/getTaskContentSolutionById/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable Integer id){
        taskContentService.
    }
}
