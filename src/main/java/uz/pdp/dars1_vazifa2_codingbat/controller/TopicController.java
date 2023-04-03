package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Topic;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.TopicDto;
import uz.pdp.dars1_vazifa2_codingbat.service.TopicService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {

    @Autowired
    TopicService topicService;




    /**
     * Topic LARNI OLIB KELADIGAN METHOD
     * @return List<Topic> </>
     */
    @GetMapping("/api/getTopic")
    public ResponseEntity<List<Topic>> getTopic(){
        List<Topic> topicList = topicService.getTopic();
        return ResponseEntity.ok(topicList);
    }


    /**
     * Topic QO'SHADIGAN METHOD
     * @param topicDto
     * @return ApiResponse
     * BIZGA TopicDto TIPIDA JSON OBJECT KELADI
     */
    @PostMapping("/api/addTopic")
    public ResponseEntity<ApiResponse> addTopic(@Valid @RequestBody TopicDto topicDto){
        ApiResponse apiResponse = topicService.addTopic(topicDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Topic NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param topicDto
     * @return ApiResponse
     * BIZGA Id va TopicDto TIPIDA JSON OBJECT KELADI
     */
    @PutMapping("/api/updateTopic/{id}")
    public ResponseEntity<ApiResponse> updateTopic(@PathVariable Integer id, @RequestBody TopicDto topicDto){
        ApiResponse apiResponse = topicService.updateTopic(id, topicDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Topic NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA TopicDto TIPIDA JSON OBJECT KELADI
     */
    @DeleteMapping("/api/deleteTopic/{id}")
    public ResponseEntity<ApiResponse> deleteTopic(@PathVariable Integer id){
        ApiResponse apiResponse = topicService.deleteTopic(id);
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
