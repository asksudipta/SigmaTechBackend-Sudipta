package se.sigma.sigmatechbackend.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class TitleDto {
    private Integer titleId;
    @NotBlank()
    private String name;

    public TitleDto(int titleId, String name) {
        setTitleId(titleId);
        setName(name);
    }

    public void setTitleId(Integer titleId) {
        if (titleId < 0) throw new IllegalArgumentException("id must be Zero or above");
        this.titleId = titleId;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Name was null");
        if (name.equals("")) throw new IllegalArgumentException("Name was empty");
        this.name = name;
    }
}
