package uz.pdp.dars1_vazifa2_codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars1_vazifa2_codingbat.controller.TaskContentController;
import uz.pdp.dars1_vazifa2_codingbat.entity.TaskContent;

public interface TaskContentRepository extends JpaRepository<TaskContent, Integer> {

}
