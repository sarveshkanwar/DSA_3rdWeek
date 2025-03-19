package week3_assignment1;

import java.util.*;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next, prev;

    Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = this.prev = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task currentTask = null;

    // Add a task at the beginning
    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task node = new Task(taskId, taskName, priority, dueDate);

        if (head == null) {
            head = node;
            head.next = head;
            head.prev = head;
        } else {
            Task tail = head.prev;
            node.next = head;
            node.prev = tail;
            head.prev = node;
            tail.next = node;
            head = node;
        }
    }

    // Add a task at the end
    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        if (head == null) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task node = new Task(taskId, taskName, priority, dueDate);
        Task tail = head.prev;

        tail.next = node;
        node.prev = tail;
        node.next = head;
        head.prev = node;
    }

    // Add a task at a specific position
    public void addAtPos(int taskId, String taskName, int priority, String dueDate, int pos) {
        if (pos == 1 || head == null) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task temp = head;
        for (int i = 1; temp.next != head && i < pos - 1; i++)
            temp = temp.next;

        Task node = new Task(taskId, taskName, priority, dueDate);
        node.next = temp.next;
        node.prev = temp;
        temp.next.prev = node;
        temp.next = node;
    }

    // Remove a task by Task ID
    public void removeTask(int taskId) {
        if (head == null) return;

        Task temp = head;
        do {
            if (temp.taskId == taskId) {
                if (temp == head && head.next == head) {
                    head = null;
                    return;
                }
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                if (temp == head) head = temp.next;
                return;
            }
            temp = temp.next;
        } while (temp != head);
    }

    // View current task and move to the next task
    public void viewAndMoveNext() {
        if (currentTask == null) currentTask = head;
        if (currentTask != null) {
            System.out.println("Current Task: " + currentTask.taskName + " (Priority: " + currentTask.priority + ")");
            currentTask = currentTask.next;
        }
    }

    // Display all tasks in the circular list
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    // Search for a task by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task Found: " + temp.taskName + " (Task ID: " + temp.taskId + ")");
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No tasks with the given priority.");
    }
}

// Main Class
public class CircularTaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks
        scheduler.addAtBeginning(1, "Complete Report", 2, "20-03-2025");
        scheduler.addAtEnd(2, "Submit Assignment", 3, "22-03-2025");
        scheduler.addAtPos(3, "Team Meeting", 1, "21-03-2025", 2);

        
        // Display tasks
        System.out.println("All Tasks:");
        scheduler.displayTasks();

        
        // Removing a task
        scheduler.removeTask(2);
        System.out.println("After Removing Task ID 2:");
        scheduler.displayTasks();

        
        // Searching by priority
        scheduler.searchByPriority(1);

        
        // Viewing tasks circularly
        System.out.println("Cycling through tasks:");
        scheduler.viewAndMoveNext();
        scheduler.viewAndMoveNext();
        scheduler.viewAndMoveNext();
    }
}

