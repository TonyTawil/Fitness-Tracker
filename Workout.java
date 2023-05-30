class Workout implements WorkoutInterface {
    private String name;
    private int caloriesPerMinute;

    public Workout(String name, int caloriesPerMinute) {
        this.name = name;
        this.caloriesPerMinute = caloriesPerMinute;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCaloriesPerMinute() {
        return caloriesPerMinute;
    }
}