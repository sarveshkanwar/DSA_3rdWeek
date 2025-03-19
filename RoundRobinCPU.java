package week3_assignment1;

import java.util.*;

class Process {
    int processId;
    int burstTime;
    int remainingTime;
    int priority;
    Process next;

    Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null, tail = null;
    private int timeQuantum;

    RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Add a process at the end
    public void addProcess(int processId, int burstTime, int priority) {
        Process node = new Process(processId, burstTime, priority);

        if (head == null) {
            head = tail = node;
            tail.next = head;
        } else {
            tail.next = node;
            tail = node;
            tail.next = head;
        }
    }

    // Remove a process after execution
    private void removeProcess(Process prev, Process current) {
        if (current == head && current == tail) {
            head = tail = null;
            return;
        }

        if (current == head) {
            head = head.next;
            tail.next = head;
        } else if (current == tail) {
            prev.next = head;
            tail = prev;
        } else {
            prev.next = current.next;
        }
    }

    // Execute processes using round-robin scheduling
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int currentTime = 0;
        Map<Integer, Integer> waitingTime = new HashMap<>();
        Map<Integer, Integer> turnAroundTime = new HashMap<>();

        for (Process temp = head; temp.next != head; temp = temp.next)
            waitingTime.put(temp.processId, 0);

        Process prev = tail, current = head;

        System.out.println("\nExecuting Round-Robin Scheduling:");
        while (head != null) {
            displayQueue();

            if (current.remainingTime > timeQuantum) {
                current.remainingTime -= timeQuantum;
                currentTime += timeQuantum;
                prev = current;
                current = current.next;
            } else {
                currentTime += current.remainingTime;
                turnAroundTime.put(current.processId, currentTime);
                waitingTime.put(current.processId, currentTime - current.burstTime);
                removeProcess(prev, current);
                current = prev.next;
            }
        }

        displayAverageTimes(waitingTime, turnAroundTime);
    }

    // Display processes in queue
    public void displayQueue() {
        if (head == null) {
            System.out.println("No processes left in queue.");
            return;
        }

        System.out.print("Processes in queue: ");
        Process temp = head;
        do {
            System.out.print("P" + temp.processId + "(" + temp.remainingTime + "s) → ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    // Calculate and display average waiting & turnaround times
    private void displayAverageTimes(Map<Integer, Integer> waitingTime, Map<Integer, Integer> turnAroundTime) {
        double totalWaitingTime = 0, totalTurnAroundTime = 0;
        int count = waitingTime.size();

        System.out.println("\nProcess Execution Summary:");
        for (int pid : waitingTime.keySet()) {
            System.out.println("Process P" + pid + " → Waiting Time: " + waitingTime.get(pid) + ", Turnaround Time: " + turnAroundTime.get(pid));
            totalWaitingTime += waitingTime.get(pid);
            totalTurnAroundTime += turnAroundTime.get(pid);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / count));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / count));
    }
}

// Main Class
public class RoundRobinCPU {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        scheduler.executeProcesses();
    }
}

