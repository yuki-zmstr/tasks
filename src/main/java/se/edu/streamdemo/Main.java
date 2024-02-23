package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        DataManager dataManager = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        // printAllData(tasksData);
        printAllDataUsingStreams(tasksData);



        System.out.println("Printing deadlines ...");
         printDeadlinesUsingStream(tasksData);

        // System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: "
                + countDeadlinesUsingStream(tasksData));

        System.out.println("Printing deadlines after sorting ...");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> filteredList = filterByString(tasksData, "11");
        printAllData(filteredList);

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration ...");
        for (Task t : tasksData) {
            System.out.println(t);
        }

    }

    public static void printAllDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using stream ...");
        tasks.stream()
                .forEach(System.out::println); // passing reference to println method
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using iteration");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredList;
    }

}
