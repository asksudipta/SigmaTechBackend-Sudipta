package se.sigma.sigmatechbackend.models.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;
    @Column
    private LocalDate date;
    @Column
    private String time;
    @Column
    private String place;
    @Column
    private String dressCode;
    @Column
    private String cost;
    @Column
    private String food;
    @Column
    private String entertainment;

    @OneToMany(mappedBy="event", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Invitation> invitations;

    public Event(LocalDate date, String time, String place, String dressCode, String cost, String food, String entertainment, List<Invitation> invitations) {
        setDate(date);
        setTime(time);
        setPlace(place);
        setDressCode(dressCode);
        setCost(cost);
        setFood(food);
        setEntertainment(entertainment);
        setInvitations(invitations);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {

        if (place == null) throw new IllegalArgumentException("Place was null");
        if (place.equals("")) throw new IllegalArgumentException("Place was empty");
        this.place = place;
    }

    public void setDressCode(String dressCode) {
        if (dressCode == null) throw new IllegalArgumentException("DressCode was null");
        if (dressCode.equals("")) throw new IllegalArgumentException("DressCode was empty");
        this.dressCode = dressCode;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setFood(String food) {
        if (food == null) throw new IllegalArgumentException("Food was null");
        if (food.equals("")) throw new IllegalArgumentException("Food was empty");
        this.food = food;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public void addInvitation(Invitation invitation)
    {
        if(invitation == null) throw new IllegalArgumentException("invitation data was null");
        if(invitations == null) invitations = new ArrayList<>();

        invitations.add(invitation);
        if(invitation.getEvent() == null) invitation.setEvent(this);
    }

    public void removeInvitation(Invitation invitation)
    {
        if(invitation == null) throw new IllegalArgumentException("invitation data was null");
        if(invitations == null) invitations = new ArrayList<>();
        if(invitations.contains(invitation))
        {
            invitations.remove(invitation);
            if(invitation.getEvent() != null) invitation.setEvent(null);
        }
    }
}
