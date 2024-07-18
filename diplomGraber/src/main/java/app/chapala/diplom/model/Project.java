package app.chapala.diplom.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @OneToMany(
            mappedBy = "project",
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @ToString.Exclude
    @Builder.Default
    private List<Job> jobs = new ArrayList<>();

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getProjects().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getProjects().remove(this);
    }

    public void addUsers(List<User> users) {
        this.users.addAll(users);
    }

    public void removeJob(Job job){
        this.jobs.remove(job);
    }

    public void addJob(Job job){
        this.jobs.add(job);
    }

    public void addJobs(List<Job> jobs) {
        this.jobs.addAll(jobs);
    }
}
