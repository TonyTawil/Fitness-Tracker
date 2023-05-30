class User implements UserInterface {
    private String name;
    private int id;
    private WorkoutHistory workoutHistory;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.workoutHistory = new WorkoutHistory();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public WorkoutHistory getWorkoutHistory() {
        return workoutHistory;
    }
}