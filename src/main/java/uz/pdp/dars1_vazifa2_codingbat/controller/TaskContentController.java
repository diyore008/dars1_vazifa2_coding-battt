package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;
import uz.pdp.dars1_vazifa2_codingbat.entity.TaskContent;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.service.TaskContentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskContentController {

    @Autowired
    TaskContentService taskContentService;


    /**
     * TaskContent LARNI OLIB KELADIGAN METHOD
     * @return List<TaskContent> </>
     */
    @GetMapping("/getTaskContent")
    public ResponseEntity<List<TaskContent>> getTaskContent(){
        List<TaskContent> taskContent = taskContentService.getTaskContent();
        return ResponseEntity.ok(taskContent);
    }


    /**
     * TaskContent NI Solution LARINI OLIN KELADIGAN METHOD
     * @param id
     * @return Solution
     * BIZGA Id TIPID Integer KELADI
     */
    @GetMapping("/getTaskContentSolutionById/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable Integer id){
        Optional<Solution> solution = taskContentService.getSolution(id);
        return ResponseEntity.ok(solution.get());
    }


    /**
     * TaskContent QO'SHADIGAN METHOD
     * @param taskContent
     * @return ApiResponse
     * BIZGA TaskContent TIPIDA JSON OBJECT KELADI
     */
    @PostMapping("/addTasContent")
    public ResponseEntity<ApiResponse> addTaskContent(@Valid @RequestBody TaskContent taskContent){
        ApiResponse apiResponse = taskContentService.addTaskContent(taskContent);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * TaskContent NI TAHRIRLAYDIGAN MEHTOD
     * @param id
     * @param taskContent
     * @return ApiResponse
     * BIZGA Id va TaskContent TIPIDA JSON OBJECT KELADI
     */
    @PutMapping("/updateTaskContent/{id}")
    public ResponseEntity<ApiResponse> updateTaskContent(@PathVariable Integer id,@Valid @RequestBody TaskContent taskContent){
        ApiResponse apiResponse = taskContentService.updateTaskContent(id, taskContent);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * TaskContent NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KELADI
     */
    @DeleteMapping("/deleteTaskContent/{id}")
    public ResponseEntity<ApiResponse> deleteTaskContent(@PathVariable Integer id){
        ApiResponse apiResponse = taskContentService.deleteTaskContent(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }





    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(
            MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}


