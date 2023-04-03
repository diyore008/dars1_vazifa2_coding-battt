package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.User;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    /**
     * USERLAR LIST INI OLIB KELADIGAN METHOD
     * @return List<User> </>
     */
    public List<User> getUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }




    /**
     * BITTA User NI Id ORQALI OLIB KELADIGAN METHOD
     * @param id
     * @return
     * BIZGA Id TIPLI JSON OBJECT KELADI
     */
    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }






    /**
     * YANGI User  QO'SHADIGAN METHOD
     * @param user
     * @return  ApiResponse
     * BIZGA User TIPIDAGI JSON OBJECT KELADI
     */
    public ApiResponse addUser(User user){
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (existsByEmail)
            return new ApiResponse("Such a email already exists", false);
        User addUser = new User();
        addUser.setEmail(addUser.getEmail());
        addUser.setPassword(addUser.getPassword());
        userRepository.save(addUser);
        return new ApiResponse("Saved user", true);
    }







    /**
     * User NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param user
     * @return ApiResponse
     * BIZGA Id VA User TIPLI JSON OBJECT KELADI
     */
    public ApiResponse updateUser(Integer id, User user){
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (existsByEmail)
            return new ApiResponse("Such a email already exists", false);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found", false);
        User upUser = optionalUser.get();
        upUser.setEmail(user.getEmail());
        upUser.setPassword(user.getPassword());
        userRepository.save(upUser);
        return new ApiResponse("Update user", true);
    }





    /**
     * User NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPLI JSON OBJECT KELADI
     */
    public ApiResponse deleteUser(Integer id){
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Deleted user", true);
        }catch (Error e){
            return new ApiResponse("Error", false);
        }

    }
}
