package app.chapala.diplom.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public enum JobType {
    New("New"),
    ToDo("ToDo"),
    InProgress("InProgress"),
    Testing("Testing"),
    Done("Done");

    private static final Random PRNG = new Random();
    private static final JobType[] values = values();

    private final String job;

    JobType(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }
    

    public static JobType randomDirection()  {
        JobType[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }

    public static String nextType(JobType jobType){
        return (values[(jobType.ordinal() + 1) % values.length]).getJob();
    }

    public static String backType(JobType jobType){
        return (values[(jobType.ordinal() - 1) % values.length]).getJob();
    }

}
