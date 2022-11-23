package se.sigma.sigmatechbackend.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import se.sigma.sigmatechbackend.models.entity.Invitation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class UserDto {

    private Integer userId;
    @NotBlank()
    private TitleDto title;
    @NotBlank(message="First Name may not be blank")
    private String firstName;
    @NotBlank(message="Last Name may not be blank")
    private String lastName;
    @NotBlank(message="Email may not be blank")
    private String email;
    @NotNull(message="userLevel may not be blank")
    private Integer userLevel;
    @NotBlank(message="Username may not be blank")
    private String userName;
    @NotBlank(message="Password may not be blank")
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull()
    private LocalDate regDate;

    @NotNull
    private List<InvitationDto> invitations;

    public UserDto(String firstName, String lastName, String email, Integer userLevel, String userName, String password, LocalDate regDate) {

        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUserLevel(userLevel);
        setUserName(userName);
        setPassword(password);
        setRegDate(regDate);
    }

    public UserDto(Integer userId, String firstName, String lastName, String email, Integer userLevel, String userName, String password, LocalDate regDate) {

        this(firstName,lastName,email,userLevel,userName,password,regDate);
        setUserId(userId);
    }

    public void setUserId(Integer userId) {
        if (userId < 0) throw new IllegalArgumentException("id must be Zero or above");
        this.userId = userId;
    }

    public void setTitle(TitleDto title)
    {
        if(title == null) throw new IllegalArgumentException("Title was null");
        if(title.getName() == null) throw new IllegalArgumentException("Title name was null");
        if(title.getName().isEmpty()) throw new IllegalArgumentException("Title name was empty");
        this.title = title;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("First Name was null");
        if (firstName.equals("")) throw new IllegalArgumentException("First Name was empty");

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("Last Name was null");
        if (lastName.equals("")) throw new IllegalArgumentException("Last Name was empty");

        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email was null");
        if (email.equals("")) throw new IllegalArgumentException("Email was empty");

        this.email = email;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public void setUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("Email was null");
        if (userName.equals("")) throw new IllegalArgumentException("Email was empty");

        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegDate(LocalDate regDate) {
        if (regDate == null) throw new IllegalArgumentException("regDate was null");
        this.regDate = regDate;
    }

    public List<InvitationDto> getInvitations() {
        if(invitations == null) invitations = new ArrayList<>();
        return invitations;
    }

    public void setInvitation(List<InvitationDto> invitations)
    {
        this.invitations = invitations;
    }

    public void addInvitation(InvitationDto invitation)
    {
        if(invitation == null) throw new IllegalArgumentException("invitation data was null");
        if(invitations == null) invitations = new ArrayList<>();

        invitations.add(invitation);
        if(invitation.getUser() == null) invitation.setUser(this);
    }

    public void removeInvitation(InvitationDto invitation)
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