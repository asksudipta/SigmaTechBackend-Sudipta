package se.sigma.sigmatechbackend.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class InvitationDto {


    private Integer invitationId;

    @NotNull()
    private LocalDate date;

    @NotBlank()
    private String time;

    @NotBlank()
    private String place;

    @NotBlank()
    private String dressCode;

    @NotBlank()
    private String cost;

    @NotBlank()
    private String food;
    @NotBlank()
    private String entertainment;

    @NotNull()
    private UserDto user;

    public InvitationDto(Integer invitationId, LocalDate date, String time, String place, String dressCode, String cost, String food, String entertainment, UserDto user) {
        this(date,time,place,dressCode,cost,food,entertainment,user);
        setInvitationId(invitationId);
    }

    public InvitationDto(LocalDate date, String time, String place, String dressCode, String cost, String food, String entertainment, UserDto user) {
        setDate(date);
        setTime(time);
        setPlace(place);
        setDressCode(dressCode);
        setCost(cost);
        setFood(food);
        setEntertainment(entertainment);
        setUser(user);
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user)
    {
        this.user = user;
    }
}
