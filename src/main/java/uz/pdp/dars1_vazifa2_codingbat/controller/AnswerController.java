package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Answer;
import uz.pdp.dars1_vazifa2_codingbat.payload.AnswerDto;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.service.AnswerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    AnswerService answerService;




    /**
     * Answer LARNI OLIB KELADIGAN METHOD
     * @return List<Answer> </>
     */
    @GetMapping("/getAnswer")
    public ResponseEntity<List<Answer>> getAnswer(){
        List<Answer> answerList = answerService.getAnswer();
        return ResponseEntity.ok(answerList);
    }



    /**
     * Answer QO'SHADIGAN METHOD
     * @param answerDto
     * @return ApiResponse
     * BIZGA AnswerDto TIPIDA JSON OBJECT KIRIB KELADI
     */
    @PostMapping("/addAnswer")
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Answer NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param answerDto
     * @return  ApiResponse
     * BIZGA Id va AnswerDto TIPIDA JSON OBJECT KELADI
     */
    @PutMapping("/updateAnswer/{id}")
    public ResponseEntity<ApiResponse> updateAnswer(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.updateAnswer(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Answer NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KIRIB KELADI
     */
    @DeleteMapping("/deleteAnswer/{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return (ResponseEntity<ApiResponse>) ResponseEntity.status(apiResponse.isSuccess()?202:409);
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
