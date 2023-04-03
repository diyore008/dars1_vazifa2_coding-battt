package uz.pdp.dars1_vazifa2_codingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotNull
    private String name;
    @NotNull
    private Integer taskContentId;
    @NotNull
    private Integer topicId;
}
