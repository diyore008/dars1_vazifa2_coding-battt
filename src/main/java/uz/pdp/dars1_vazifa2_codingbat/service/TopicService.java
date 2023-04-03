package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.Language;
import uz.pdp.dars1_vazifa2_codingbat.entity.Topic;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.TopicDto;
import uz.pdp.dars1_vazifa2_codingbat.repository.LanguageRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    LanguageRepository languageRepository;


    /**
     * Topic LARNI OLIB KELADIGAN METHOD
     * @return List<Topic> </>
     */
    public List<Topic> getTopic(){
        List<Topic> topicList = topicRepository.findAll();
        return topicList;
    }



    /**
     * Topic QO'SHADIGAN METHOD
     * @param topicDto
     * @return ApiResponse
     * BIZGA TopicDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse addTopic(TopicDto topicDto){
        Optional<Language> optionalLanguage = languageRepository.findById(topicDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Language not found", false);
        Topic topic = new Topic();
        topic.setName(topicDto.getName());
        topic.setCheckMark(topicDto.isCheckMark());
        topic.setStars_count(topicDto.getStarsCount());
        topic.setLanguageId(optionalLanguage.get());
        topic.setDescription(topicDto.getDescription());
        topicRepository.save(topic);
        return new ApiResponse("Saved topic", true);
    }




    /**
     * Topic NI TAHRIRLAYDIGAN METHOD
     * @param id
     * @param topicDto
     * @return ApiResponse
     * BIZGA Id va TopicDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse updateTopic(Integer id, TopicDto topicDto){
        Optional<Language> optionalLanguage = languageRepository.findById(topicDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Language not found", false);
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Topic not found", false);
        Topic topic = optionalTopic.get();
        topic.setName(topicDto.getName());
        topic.setLanguageId(optionalLanguage.get());
        topic.setCheckMark(topicDto.isCheckMark());
        topic.setStars_count(topicDto.getStarsCount());
        topic.setDescription(topicDto.getDescription());
        topicRepository.save(topic);
        return new ApiResponse("Updated topic", true);
    }




    /**
     * Topic NI O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA TopicDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse deleteTopic(Integer id){
        try{
            topicRepository.deleteById(id);
            return new ApiResponse("Deleted topic", true);
        } catch (Error e){
            return  new ApiResponse("Error ", false);
        }
    }
}
