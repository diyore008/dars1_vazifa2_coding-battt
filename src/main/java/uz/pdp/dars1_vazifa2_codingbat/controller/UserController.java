package uz.pdp.dars1_vazifa2_codingbat.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dars1_vazifa2_codingbat.entity.User;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * USERLAR LIST INI OLIB KELADIGAN METHOD
     * @return List<User> </>
     */
    @GetMapping("/api/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }


    /**
     * BITTA User NI Id ORQALI OLIB KELADIGAN METHOD
     * @param id
     * @return User
     * BIZGA Id TIPLI JSON OBJECT KELADI
     */
    @GetMapping("/api/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }


    /**
     * YANGI User  QO'SHADIGAN METHOD
     * @param user
     * @return  ApiResponse
     * BIZGA User TIPIDAGI JSON OBJECT KELADI
     */
    @PostMapping("/api/addUser")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user){
        ApiResponse apiResponse = userService.addUser(user);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * User NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param user
     * @return ApiResponse
     * BIZGA Id VA User TIPLI JSON OBJECT KELADI
     */
    @PutMapping("/api/updateUser/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer id,@Valid @RequestBody User user){
        ApiResponse apiResponse = userService.updateUser(id, user);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * User NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPLI JSON OBJECT KELADI
     */
    @DeleteMapping("/api/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
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
