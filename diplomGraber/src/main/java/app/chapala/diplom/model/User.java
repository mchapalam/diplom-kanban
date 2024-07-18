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
@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "wages")
    private Float wages;

    private String email;

    private String username;

    private String password;

    @ManyToMany(
            mappedBy = "users",
            fetch = FetchType.EAGER
    )
    private List<Job> jobs = new ArrayList<>();

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<RoleType> rolesType = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @Builder.Default
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        this.projects.add(project);
        project.getUsers().add(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getUsers().remove(this);
    }


    public void removeJob(Job job){
        this.jobs.remove(job);
        job.getUsers().remove(this);
    }

    public void addJob(Job job){
        this.jobs.add(job);
        job.getUsers().add(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", wages=" + wages +
                ", jobs=" +
                '}';
    }
}
