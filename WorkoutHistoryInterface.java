import java.util.Date;
import java.util.List;

interface WorkoutHistoryInterface {
    void addWorkout(Workout workout, int duration, Date date, User user);
    List<WorkoutRecord> getCompletedWorkouts(User user);
    int getTotalCaloriesBurned(User user);
}

