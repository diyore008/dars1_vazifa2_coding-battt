package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.controller.TaskContentController;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;
import uz.pdp.dars1_vazifa2_codingbat.entity.TaskContent;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.repository.SolutionRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TaskContentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskContentService {

    @Autowired
    TaskContentRepository taskContentRepository;

    @Autowired
    SolutionRepository solutionRepository;



    /**
     * TaskContent LARNI OLIB KELADIGAN METHOD
     * @return List<TaskContent> </>
     */
    public List<TaskContent> getTaskContent(){
        List<TaskContent> taskContentControllerList = taskContentRepository.findAll();
        return taskContentControllerList;
    }



    /**
     * TaskContent NI Solution LARINI OLIN KELADIGAN METHOD
     * @param id
     * @return Solution
     * BIZGA Id TIPID Integer KELADI
     */
    public Optional<Solution> getSolution(Integer id){
        Optional<Solution> solutionByTaskContentId = solutionRepository.getSolutionByTaskContentId(id);
        return solutionByTaskContentId;
    }




    /**
     * TaskContent QO'SHADIGAN METHOD
     * @param taskContent
     * @return ApiResponse
     * BIZGA TaskContent TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse addTaskContent(TaskContent taskContent){
        TaskContent addTaskContent = new TaskContent();
        addTaskContent.setContent(taskContent.getContent());
        taskContentRepository.save(addTaskContent);
        return new ApiResponse("Saved task Content", true);
    }




    /**
     * TaskContent NI TAHRIRLAYDIGAN MEHTOD
     * @param id
     * @param taskContent
     * @return ApiResponse
     * BIZGA Id va TaskContent TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse updateTaskContent(Integer id, TaskContent taskContent){
        Optional<TaskContent> optionalTaskContent = taskContentRepository.findById(id);
        if (!optionalTaskContent.isPresent())
            return new ApiResponse("Task Content not found", false);
        TaskContent upTaskContent = optionalTaskContent.get();
        upTaskContent.setContent(taskContent.getContent());
        taskContentRepository.save(upTaskContent);
        return new ApiResponse("Update task content",true);
    }




    /**
     * TaskContent NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KELADI
     */
    public ApiResponse deleteTaskContent(Integer id){
        try{
            taskContentRepository.deleteById(id);
            return new ApiResponse("Deleted task content", true);
        } catch (Error e){
            return new ApiResponse("Error", false);
        }
    }
}
