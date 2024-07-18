package app.chapala.diplom.listner;

import app.chapala.diplom.model.*;
import app.chapala.diplom.repository.ProjectRepository;
import app.chapala.diplom.service.JobService;
import app.chapala.diplom.service.ProjectService;
import app.chapala.diplom.service.UserService;
import app.chapala.diplom.web.mapper.JobMapper;
import app.chapala.diplom.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("init")
public class DatabaseCreator {

    private final JobService jobService;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    private final UserMapper userMapper;

    private final JobMapper jobMapper;

    private final PasswordEncoder passwordEncoder;
    private Random random = new Random();

    private List<String> names = List.of("Софья",
            "Арина",
            "Максим",
            "Александр",
            "Иван",
            "Светлана",
            "Дмитрий",
            "Илья",
            "Максим",
            "Герман",
            "Артём",
            "Ксения",
            "Роман",
            "Владимир",
            "Михаил",
            "Агата",
            "Артём"
    );

    private List<String> lastNames = List.of("Тихомирова",
            "Зайцева",
            "Петров",
            "Кузьмин",
            "Козлов",
            "Фомина",
            "Панин",
            "Николаев",
            "Васильев",
            "Давыдов",
            "Ульянов",
            "Сахарова",
            "Кириллов",
            "Кузьмин",
            "Терехов",
            "Никитина",
            "Селезнев");

    List<String> tasks = List.of("Ознакомиться с ТЗ и бизнес-требованиями",
          "Разбить проект на подзадачи",
          "Оценить временные затраты на каждую подзадачу",
          "Определить приоритеты задач",
          "Составить план разработки",

          "Написать код для каждой подзадачи",
          "Реализовать функции и модули",
          "Протестировать код на наличие ошибок",
          "Исправить ошибки и баги",
          "Оптимизировать код",


          "Провести модульное тестирование",
          "Провести интеграционное тестирование",
          "Провести нагрузочное тестирование",
          "Провести тестирование на совместимость",
          "Исправить ошибки, выявленные в тестировании",

          "Развернуть код на тестовом сервере",
          "Провести тестирование на тестовом сервере",
          "Исправить ошибки, выявленные на тестовом сервере",
          "Развернуть код на рабочем сервере",

          "Исправлять ошибки пользователей",
          "Отвечать на вопросы пользователей",
          "Дорабатывать функционал по мере необходимости",
          "Обновлять документацию",

          "Описать код",
          "Провести рефакторинг кода",
          "Написать unit-тесты",
          "Обновить документацию (снова)",
          "Провести код-ревью",
          "Участвовать в митингах",
          "Отслеживать изменения в ТЗ",
          "Сообщать о возникающих проблемах",
          "Ограничить WIP (Work in Progress)",
          "Визуализировать ход выполнения проекта",
          "Отслеживать прогресс",
          "Выявлять узкие места",
          "Расставлять приоритеты задач",
          "Мотивировать команду"
    );

    @EventListener(ApplicationStartedEvent.class)
    public void createUser() {
        log.info("Запуск DatabaseCreator - User");

        List<Project> projects = createProject();

        for (int i = 0; i < 17; i++) {
            User user = new User();
            user.setFirstName(names.get(i));
            user.setLastName(lastNames.get(i));
            user.setWages(randomWages());
            user.setRolesType(Collections.singleton(RoleType.ROLE_USER));
            user.setPassword(passwordEncoder.encode("password")); // Пример пароля
            user.setUsername(generateUsername(names.get(i), lastNames.get(i)));

            user.setProjects(randomProjects(projects));

            userService.save(user);
        }

        createJob();
    }

    private Set<Project> randomProjects(List<Project> projects) {
        int projectCount = projects.size();
        if (projectCount == 0) {
            throw new IllegalArgumentException("Project list is empty");
        }

        int numberOfProjectsToAssign = 1 + random.nextInt(Math.max(projectCount - 1, 1));
        Set<Project> assignedProjects = new HashSet<>();
        Collections.shuffle(projects);

        for (int i = 0; i < numberOfProjectsToAssign; i++) {
            assignedProjects.add(projects.get(i));
        }
        return assignedProjects;
    }

    public List<Project> createProject() {
        log.info("Запуск DatabaseCreator - Project");

        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            log.info("Создание нового проекта");

            Project project = new Project();
            project.setTitle("Project " + (i + 1));

            projects.add(projectRepository.save(project));
        }
        return projects;
    }

    public void createJob() {
        log.info("Запуск DatabaseCreator - Job");
        List<Project> projects = projectRepository.findAll();

        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                Job job = new Job();
                List<User> users = userService.getRandomUsers(2L)
                        .stream().map(userMapper::responseToUser)
                        .collect(Collectors.toList());

                job.setUsers(users);
                job.setTitle(tasks.get(i));
                job.setDateCreate(Instant.now());
                job.setType(JobType.randomDirection());
                job.setInfo("Some text");

                Long tmpTime = 5L + random.nextLong(46L);
                job.setExpectedTime(tmpTime);

                if ("Done".equals(job.getType().getJob())) {
                    Long tmpActualTime = 2L + random.nextLong(99L);
                    job.setActualTime(tmpActualTime);
                }

                Project project = projects.get(random.nextInt(projects.size()));
                job.setProject(project);


                project.addJob(job);

                jobService.save(job);
                projectRepository.save(project);
            }
        }
        log.info("Завершение создания работ");
    }

    public Float randomWages() {
        return 500F + random.nextFloat() * 4500F;
    }



    private String generateUsername(String firstName, String lastName) {
        String baseUsername = firstName.charAt(0) + lastName;
        String username = baseUsername;
        int counter = 1;
        while (userService.existsByUsername(username)) {
            username = baseUsername + counter;
            counter++;
        }
        return username;
    }

    public void assignJobsToUsers() {
        List<User> users = userService.findAll().stream().map(userMapper::responseToUser).toList();
        List<Job> jobs = jobService.findAll().stream().map(jobMapper::responseToJob).toList();

        for (User user : users) {
            Set<Job> assignedJobs = new HashSet<>();
            int numberOfJobs = random.nextInt(3) + 1;

            for (int i = 0; i < numberOfJobs; i++) {
                Job job = jobs.get(random.nextInt(jobs.size()));
                assignedJobs.add(job);
            }
            user.setJobs(new ArrayList<>(assignedJobs));
            userService.save(user);
        }
    }

    private Set<Job> randomJobs(List<Job> jobs) {
        int jobCount = jobs.size();
        if (jobCount == 0) {
            throw new IllegalArgumentException("Job list is empty");
        }

        int numberOfJobsToAssign = 1 + random.nextInt(Math.min(3, jobCount));
        Set<Job> assignedJobs = new HashSet<>();
        Collections.shuffle(jobs);

        for (int i = 0; i < numberOfJobsToAssign; i++) {
            assignedJobs.add(jobs.get(i));
        }
        return assignedJobs;
    }
}
