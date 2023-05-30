import java.util.Date;

public class WorkoutRecord {
    private Workout workout;
    private int duration;
    private Date date;
    private User user;

    public WorkoutRecord(Workout workout, int duration, Date date, User user) {
        this.workout = workout;
        this.duration = duration;
        this.date = date;
        this.user = user;
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public int getDuration() {
        return this.duration;
    }

    public Date getDate() {
        return this.date;
    }

    public User getUser() {
        return this.user;
    }
}
