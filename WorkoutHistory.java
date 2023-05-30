import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutHistory implements WorkoutHistoryInterface {
    private List<WorkoutRecord> workoutRecords;

    public WorkoutHistory() {
        this.workoutRecords = new ArrayList<>();
    }

    @Override
    public void addWorkout(Workout workout, int duration, Date date, User user) {
        WorkoutRecord workoutRecord = new WorkoutRecord(workout, duration, date, user);
        this.workoutRecords.add(workoutRecord);
    }

    public List<WorkoutRecord> getCompletedWorkouts(User user) {
        List<WorkoutRecord> userWorkouts = new ArrayList<>();
        for (WorkoutRecord record : this.workoutRecords) {
            if (record.getUser().getId() == user.getId()) {
                userWorkouts.add(record);
            }
        }
        return userWorkouts;
    }

    @Override
    public int getTotalCaloriesBurned(User user) {
        int totalCalories = 0;
        for (WorkoutRecord record : this.workoutRecords) {
            if (record.getUser().getId() == user.getId()) {
                totalCalories += record.getWorkout().getCaloriesPerMinute() * record.getDuration();
            }
        }
        return totalCalories;
    }
}
