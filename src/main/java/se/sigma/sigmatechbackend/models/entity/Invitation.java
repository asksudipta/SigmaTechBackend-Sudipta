package se.sigma.sigmatechbackend.models.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import se.sigma.sigmatechbackend.enums.EmailResponse;
import se.sigma.sigmatechbackend.enums.EmailStatus;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "event_id")
    private Event event;
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    private EmailStatus emailStatus;

    private EmailResponse emailResponse;

    public Invitation(Long id, Event event, User user, EmailStatus emailStatus, EmailResponse emailResponse)
    {
        setId(id);
        setEvent(event);
        setUser(user);
        setEmailStatus(emailStatus);
        setEmailResponse(emailResponse);
    }

    public void setId(Long id) {
        if (id < 0) throw new IllegalArgumentException("id must be Zero or above");
        this.id = id;
    }

    public void setEvent(Event event) {
        if (event == null) throw new IllegalArgumentException("event cannot be null");
        this.event = event;
    }

    public void setUser(User user)
    {
        if (user == null) throw new IllegalArgumentException("user cannot be null");
        this.user = user;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setEmailResponse(EmailResponse emailResponse) {
        this.emailResponse = emailResponse;
    }
}
