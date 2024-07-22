package app.chapala.diplom.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "date_create")
    private Instant dateCreate;
    @Column(name = "date_update")
    private Instant dateUpdate;

    private String info;

    @Column(name = "expected_time")
    private Long expectedTime;

    @Column(name = "actual_time")
    private Long actualTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "job_user",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private JobType type;

    public void addUser(User user) {
        this.users.add(user);
        user.getJobs().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getJobs().remove(this);
    }
}
