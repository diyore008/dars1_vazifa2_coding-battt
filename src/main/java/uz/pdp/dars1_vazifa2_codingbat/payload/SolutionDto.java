package uz.pdp.dars1_vazifa2_codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionDto {

    private String solution;
    private Integer taskContentId;
}
