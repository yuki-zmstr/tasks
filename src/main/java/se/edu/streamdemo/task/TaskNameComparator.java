package se.edu.streamdemo.task;

import java.util.Comparator;

public class TaskNameComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDescription()
                .compareToIgnoreCase(t2.getDescription());
    }
}