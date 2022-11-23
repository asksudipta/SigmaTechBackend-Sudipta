package se.sigma.sigmatechbackend.models.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer titleId;
    @Column(nullable = false)
    private String name;

    public Title(int titleId, String name) {
        setTitleId(titleId);
        setName(name);
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Name was null");
        if (name.equals("")) throw new IllegalArgumentException("Name was empty");
        this.name = name;
    }
}

