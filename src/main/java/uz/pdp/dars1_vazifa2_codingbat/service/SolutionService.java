package uz.pdp.dars1_vazifa2_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;
import uz.pdp.dars1_vazifa2_codingbat.entity.TaskContent;
import uz.pdp.dars1_vazifa2_codingbat.payload.ApiResponse;
import uz.pdp.dars1_vazifa2_codingbat.payload.SolutionDto;
import uz.pdp.dars1_vazifa2_codingbat.repository.SolutionRepository;
import uz.pdp.dars1_vazifa2_codingbat.repository.TaskContentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {

    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    TaskContentRepository taskContentRepository;


    /**
     * Solution LARNI OLIB KELADIGAN METHOD
     * @return List<Solution> </>
     */
    public List<Solution> getSolution(){
        List<Solution> solutionList = solutionRepository.findAll();
        return solutionList;
    }


    /**
     * Solution QO'SHADIGAN METHOD
     * @param solutionDto
     * @return ApiResponse
     * BIZGA SolutionDto TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse addSolution(SolutionDto solutionDto){
        Optional<TaskContent> optionalTaskContent = taskContentRepository.findById(solutionDto.getTaskContentId());
        if (!optionalTaskContent.isPresent())
            return new ApiResponse("Task content not found", false);
        Solution solution = new Solution();
        solution.setSolutionContent(solutionDto.getSolution());
        solution.setTaskContent(optionalTaskContent.get());
        solutionRepository.save(solution);
        return new ApiResponse("Saved solution", true);
    }




    /**
     * Solution TAHRIRLAYDIGAN METHOD
     * @param id
     * @param solutionDto
     * @return ApiResponse
     * BIZGA SolutionDto TIPIDA JSON OBJECT KIRIB KELADI
     */
    public ApiResponse updateSolution(Integer id, SolutionDto solutionDto){
        Optional<TaskContent> optionalTaskContent = taskContentRepository.findById(solutionDto.getTaskContentId());
        if (!optionalTaskContent.isPresent())
            return new ApiResponse("Task content not found", false);
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return new ApiResponse("Solution not found", false);
        Solution solution = optionalSolution.get();
        solution.setSolutionContent(solutionDto.getSolution());
        solution.setTaskContent(optionalTaskContent.get());
        solutionRepository.save(solution);
        return new ApiResponse("Updated solution", true);
    }





    /**
     * Solution O'CHIRADIGAN METHOD
     * @param id
     * @return ApiResponse
     * BIZGA Id TIPIDA JSON OBJECT KELADI
     */
    public ApiResponse deleteSolution(Integer id){
        try{
            solutionRepository.deleteById(id);
            return new ApiResponse("Deleted solution", true);
        }catch (Error e){
            return  new ApiResponse("Error", false);

        }
    }

}
