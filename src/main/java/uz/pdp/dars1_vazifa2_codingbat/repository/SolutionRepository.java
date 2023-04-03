package uz.pdp.dars1_vazifa2_codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars1_vazifa2_codingbat.entity.Solution;

import java.util.Optional;

public interface SolutionRepository extends JpaRepository<Solution, Integer> {
    Optional<Solution> getSolutionByTaskContentId(Integer taskContent_id);
}
