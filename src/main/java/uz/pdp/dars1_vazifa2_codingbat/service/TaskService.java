package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.Task;
import uz.pdp.dars1_vazifa2_codingbat.entity.TaskContent;
import uz.pdp.dars1_vazifa2_codingbat.entity.Topic;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.TaskDto;
import uz.pdp.dars1_vazifa2_codingbat.repository.TaskContentRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TaskRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    TaskContentRepository taskContentRepository;




    /**
     * Task LARNI OLIB KELADIGAN METHOD
     * @return List<Task> </>
     */
    public List<Task> getTask(){
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }


    /**
     * Task QO'SHADIGAN METHOD
     * @param taskDto
     * @return ApiResponse
     * BIZGA TaskDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse addTask(TaskDto taskDto){
        Optional<Topic> optionalTopic = topicRepository.findById(taskDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic not found", false);
        Optional<TaskContent> optionalTaskContent = taskContentRepository.findById(taskDto.getTaskContentId());
        if (!optionalTaskContent.isPresent())
            return new ApiResponse("Task content not found", false);
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setTopic(optionalTopic.get());
        task.setTaskContent(optionalTaskContent.get());
        taskRepository.save(task);
        return new ApiResponse("Saved task content", true);
    }


    /**
     * Task NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param taskDto
     * @return ApiResponse
     * BIZGA Id VA TaskDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse updateTask(Integer id, TaskDto taskDto){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found",false);
        Optional<Topic> optionalTopic = topicRepository.findById(taskDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic not found", false);
        Optional<TaskContent> optionalTaskContent = taskContentRepository.findById(taskDto.getTaskContentId());
        if (!optionalTaskContent.isPresent())
            return new ApiResponse("Task content not found", false);

        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setTaskContent(optionalTaskContent.get());
        task.setTopic(optionalTopic.get());
        taskRepository.save(task);
        return new ApiResponse("Updated task", true);
    }


    /**
     * Task NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA Integer KELADI
     */
    public ApiResponse deleteTask(Integer id){
        try{
            taskRepository.deleteById(id);
            return new ApiResponse("Deleted task", true);
        } catch (Error e){
            return new ApiResponse("Error", false);
        }
    }
}
