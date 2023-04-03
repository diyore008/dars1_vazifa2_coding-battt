package uz.pdp.dars1_vazifa2_codingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer taskId;
    @NotNull
    private String answerContent;
}
