package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.SolutionDto;
import uz.pdp.dars1_vazifa2_codingbat.service.SolutionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SolutionController {

    @Autowired
    SolutionService solutionService;


    /**
     * Solution LARNI OLIB KELADIGAN METHOD
     * @return List<Solution> </>
     */
    @GetMapping("/getSolution")
    public ResponseEntity<List<Solution>> getSolution(){
        List<Solution> solution = solutionService.getSolution();
        return ResponseEntity.ok(solution);
    }





    /**
     * Solution QO'SHADIGAN METHOD
     * @param solutionDto
     * @return ApiResponse
     * BIZGA SolutionDto TIPIDA JSON OBJECT KELADI
     */
    @PostMapping("/addSolution")
    public ResponseEntity<ApiResponse> addSolution(@Valid @RequestBody SolutionDto solutionDto){
        ApiResponse apiResponse = solutionService.addSolution(solutionDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Solution TAHRIRLAYDIGAN METHOD
     * @param id
     * @param solutionDto
     * @return ApiResponse
     * BIZGA SolutionDto TIPIDA JSON OBJECT KIRIB KELADI
     */
    @PutMapping("/updateSolution/{id}")
    public ResponseEntity<ApiResponse> updateSolution(@PathVariable Integer id,@Valid @RequestBody SolutionDto solutionDto){
        ApiResponse apiResponse = solutionService.updateSolution(id, solutionDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Solution O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA JSON OBJECT KELADI
     */
    @DeleteMapping("/deleteSolution/{id}")
    public ResponseEntity<ApiResponse> deleteSolution(@PathVariable Integer id){
        ApiResponse apiResponse = solutionService.deleteSolution(id);
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

