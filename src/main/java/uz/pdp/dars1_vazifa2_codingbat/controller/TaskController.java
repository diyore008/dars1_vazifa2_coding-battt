package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Task;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.TaskDto;
import uz.pdp.dars1_vazifa2_codingbat.service.TaskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;


    /**
     * Task LARNI OLIB KELADIGAN METHOD
     * @return List<Task> </>
     */
    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getTask(){
        List<Task> taskList = taskService.getTask();
        return ResponseEntity.ok(taskList);
    }



    /**
     * Task QO'SHADIGAN METHOD
     * @param taskDto
     * @return ApiResponse
     * BIZGA TaskDto TIPIDA JSON OBJECT KELADI
     */
    @PostMapping("/addTask")
    public ResponseEntity<ApiResponse> addTask(@Valid  @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.addTask(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }




    /**
     * Task NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param taskDto
     * @return ApiResponse
     * BIZGA Id VA TaskDto TIPIDA JSON OBJECT KELADI
     */
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Integer id,@Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.updateTask(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }





    /**
     * Task NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KELADI
     */
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
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
