package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.Answer;
import uz.pdp.dars1_vazifa2_codingbat.entity.Task;
import uz.pdp.dars1_vazifa2_codingbat.entity.User;
import uz.pdp.dars1_vazifa2_codingbat.payload.AnswerDto;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.repository.AnswerRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TaskRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Answer LARNI OLIB KELADIGAN METHOD
     * @return List<Answer> </>
     */
    public List<Answer> getAnswer(){
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }


    /**
     * Answer QO'SHADIGAN METHOD
     * @param answerDto
     * @return ApiResponse
     * BIZGA AnswerDto TIPIDA JSON OBJECT KIRIB KELADI
     */
    public ApiResponse addAnswer(AnswerDto answerDto){
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found", false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalTask.isPresent())
            return new ApiResponse("User not found", false);
        Answer answer = new Answer();
        answer.setUser(optionalUser.get());
        answer.setTask(optionalTask.get());
        answer.setAnswerContent(answerDto.getAnswerContent());
        answerRepository.save(answer);
        return new ApiResponse("Saved answer", true);
    }





    /**
     * Answer NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param answerDto
     * @return  ApiResponse
     * BIZGA Id va AnswerDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse updateAnswer(Integer id, AnswerDto answerDto){
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found", false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalTask.isPresent())
            return new ApiResponse("User not found", false);
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Answer not found", false);
        Answer answer = optionalAnswer.get();
        answer.setAnswerContent(answerDto.getAnswerContent());
        answer.setUser(optionalUser.get());
        answer.setTask(optionalTask.get());
        answerRepository.save(answer);
        return new ApiResponse("Updated answer", true);
    }





    /**
     * Answer NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KIRIB KELADI
     */
    public ApiResponse deleteAnswer(Integer id){
        try{
            answerRepository.deleteById(id);
            return new ApiResponse("Deleted answer", true);
        } catch (Error e){
            return new ApiResponse("Error", false);
        }
    }
}
