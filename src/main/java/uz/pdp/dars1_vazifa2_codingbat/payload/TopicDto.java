package uz.pdp.dars1_vazifa2_codingbat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    @NotNull
    private String name;
    @NotNull
    private Integer languageId;

    private boolean checkMark;

    private int starsCount;
    @NotNull
    private String description;
}
