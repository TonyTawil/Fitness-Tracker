import java.io.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static WorkoutHistory workoutHistory = new WorkoutHistory();
    private static List<User> users = new ArrayList<>();
    private static List<Workout> workouts = new ArrayList<>();

    public static void main(String[] args) {

        // Load workouts from text file (each line should be in the format "name,caloriesPerMinute")
        try (BufferedReader reader = new BufferedReader(new FileReader("workouts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int caloriesPerMinute = Integer.parseInt(parts[1]);
                Workout workout = new Workout(name, caloriesPerMinute);
                workouts.add(workout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load users from text file (each line should be in the format "id,name")
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                User user = new User(name, id);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("\nWelcome to Tony's Workout Tracker!\n");
            System.out.println("Please choose an action:");
            System.out.println("1. View available workouts");
            System.out.println("2. Add a new workout to your history");
            System.out.println("3. View your workout history");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\nAvailable workouts:");
                    for (int i = 0; i < workouts.size(); i++) {
                        Workout workout = workouts.get(i);
                        System.out.println((i + 1) + ". " + workout.getName() + ": " + workout.getCaloriesPerMinute() + " calories per minute");
                    }
                    break;
                case 2:
                    System.out.println("\nPlease enter your user ID:");
                    int userId = scanner.nextInt();
                    User user = getUserById(userId);
                    
                    if (user == null) {
                        System.out.println("Invalid user ID!");
                        return;
                    }

                    System.out.println("Welcome " + user.getName() + "!");

                    System.out.println("\nAvailable workouts:");
                    for (int i = 0; i < workouts.size(); i++) {
                        Workout workout = workouts.get(i);
                        System.out.println((i + 1) + ". " + workout.getName() + ": " + workout.getCaloriesPerMinute() + " calories per minute");
                    }

                    System.out.println("Choose a workout to add to your history:");
                    int workoutIndex = scanner.nextInt() - 1;

                    if (workoutIndex < 0 || workoutIndex >= workouts.size()) {
                        System.out.println("Invalid choice!");
                        return;
                    }

                    Workout workout = workouts.get(workoutIndex);

                    System.out.println("Enter the duration of the workout in minutes:");
                    int duration = scanner.nextInt();
                    Date date = new Date(); // current date

                    workoutHistory.addWorkout(workout, duration, date, user);
                    System.out.println("Workout added successfully!");
                    break;
                case 3:
                    System.out.println("\nPlease enter your user ID:");
                    int uID = scanner.nextInt();
                    User u = getUserById(uID);
                    
                    if (u == null) {
                        System.out.println("Invalid user ID!");
                        return;
                    }

                    List<WorkoutRecord> completedWorkouts = workoutHistory.getCompletedWorkouts(u);

                    if (completedWorkouts.isEmpty()) {
                        System.out.println("No completed workouts yet!");
                        return;
                    }

                    System.out.println("\nCompleted workouts:");
                    for (WorkoutRecord record : completedWorkouts) {
                        Workout w = record.getWorkout();
                        System.out.println("- " + w.getName() + ", duration: " + record.getDuration() + " minutes, date: " + record.getDate());
                    }

                    int totalCaloriesBurned = workoutHistory.getTotalCaloriesBurned(u);
                    System.out.println("Total calories burned: " + totalCaloriesBurned);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    private static User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
}
