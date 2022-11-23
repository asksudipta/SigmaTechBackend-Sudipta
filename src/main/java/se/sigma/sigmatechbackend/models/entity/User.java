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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer userId;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "title_id")
    private Title title;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private Integer userLevel;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, updatable = false)
    private LocalDate regDate;
    @OneToMany(mappedBy="user", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Invitation> invitations;
    public User(Integer userId, Title title, String firstName, String lastName, String email, Integer userLevel, String userName, String password, LocalDate regDate) {
        setUserId(userId);
        setTitle(title);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUserLevel(userLevel);
        setUserName(userName);
        setPassword(password);
        setRegDate(regDate);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        if(this.userId != null)
        {
            if(userId == null) throw new IllegalArgumentException("id must not be null");
            if (userId < 0) throw new IllegalArgumentException("id must be Zero or above");
        }
        this.userId = userId;
    }

    public Title getTitle()
    {
        return title;
    }

    public void setTitle(Title title)
    {
        if(title == null) throw new IllegalArgumentException("Title was null");
        if(title.getName() == null) throw new IllegalArgumentException("Title name was null");
        if(title.getName().isEmpty()) throw new IllegalArgumentException("Title name was empty");
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("First Name was null");
        if (firstName.equals("")) throw new IllegalArgumentException("First Name was empty");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("Last Name was null");
        if (lastName.equals("")) throw new IllegalArgumentException("Last Name was empty");

        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email was null");
        if (email.equals("")) throw new IllegalArgumentException("Email was empty");

        this.email = email;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("Email was null");
        if (userName.equals("")) throw new IllegalArgumentException("Email was empty");

        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        if (regDate == null) throw new IllegalArgumentException("regDate was null");
        this.regDate = regDate;
    }

    public List<Invitation> getInvitations() {
        if(invitations == null) invitations = new ArrayList<>();
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public void addInvitation(Invitation invitation)
    {
        if(invitation == null) throw new IllegalArgumentException("invitation data was null");
        if(invitations == null) invitations = new ArrayList<>();

        invitations.add(invitation);
        if(invitation.getUser() == null) invitation.setUser(this);
    }

    public void removeInvitation(Invitation invitation)
    {
        if(invitation == null) throw new IllegalArgumentException("invitation data was null");
        if(invitations == null) invitations = new ArrayList<>();
        if(invitations.contains(invitation))
        {
            invitations.remove(invitation);
            if(invitation.getUser() != null) invitation.setUser(null);
        }
    }
}







