package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.Language;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getLanguage(){
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }

    public Language getLanguageById(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.get();
    }

    public ApiResponse addLanguage(Language addlanguage){
        Language language = new Language();

        language.setName(addlanguage.getName());
        language.setTopicCount(addlanguage.getTopicCount());
        languageRepository.save(language);
        return new ApiResponse("Saved new Language", true);
    }

    public ApiResponse updateLanguage(Integer id, Language upLanguage){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Language not found", false);
        Language language = optionalLanguage.get();
        language.setName(upLanguage.getName());
        language.setTopicCount(upLanguage.getTopicCount());
        languageRepository.save(language);
        return new ApiResponse("Updated language", true);
    }

    public ApiResponse deleteLanguage(Integer id){
        try{
            languageRepository.deleteById(id);
            return new ApiResponse("Deleted language", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}
