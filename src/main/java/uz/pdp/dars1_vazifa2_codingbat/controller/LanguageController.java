package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Language;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.service.LanguageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping("/api/getLanguages")
    public ResponseEntity<List<Language>> getLanguage(){
        List<Language> languageList = languageService.getLanguage();
        return ResponseEntity.ok(languageList);
    }

    @GetMapping("/api/getLanguageById/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id){
        Language languageById = languageService.getLanguageById(id);
        return ResponseEntity.ok(languageById);
    }

    @PostMapping("/api/addLanguage")
    public ResponseEntity<ApiResponse> addLanguage(@Valid  @RequestBody Language addlanguage){
        ApiResponse apiResponse = languageService.addLanguage(addlanguage);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("api/upLanguage/{id}")
    public ResponseEntity<ApiResponse> upLanguage(@PathVariable Integer id,@Valid @RequestBody Language upLanguage){
        ApiResponse apiResponse = languageService.updateLanguage(id, upLanguage);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("api/deleteLanguage/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.ok(apiResponse);
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
