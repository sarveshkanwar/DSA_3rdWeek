package week3_assignment1;


import java.util.*;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;
    Ticket prev;

    Ticket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
        this.prev = null;
    }
}

class TicketSystem {
    Ticket head;

    public void bookAtBeginning(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket ticket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        ticket.next = head;
        ticket.prev = null;
        if (head != null) {
            head.prev = ticket;
        }
        head = ticket;
    }

    public void bookAtEnd(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket ticket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = ticket;
            return;
        }
        Ticket temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = ticket;
        ticket.prev = temp;
    }

    public void bookAtPos(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime, int pos) {
        Ticket ticket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (pos == 1) {
            ticket.next = head;
            if (head != null) head.prev = ticket;
            head = ticket;
            return;
        }
        Ticket temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Invalid position");
            return;
        }
        ticket.next = temp.next;
        if (temp.next != null) temp.next.prev = ticket;
        temp.next = ticket;
        ticket.prev = temp;
    }

    void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets booked.");
        } else {
            Ticket temp = head;
            while (temp != null) {
                if (temp.customerName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                    System.out.println("Ticket Found: " + temp.ticketId + " | " + temp.customerName +
                            " | " + temp.movieName + " | Seat: " + temp.seatNumber + " | Time: " + temp.bookingTime);
                    return;
                }
                temp = temp.next;
            }
            System.out.println("No ticket found for: " + keyword);
        }
    }

    void displayTickets() {
        Ticket temp = head;
        while (temp != null) {
            System.out.println("Ticket ID: " + temp.ticketId + "\n"
                    + "Customer: " + temp.customerName + "\n"
                    + "Movie: " + temp.movieName + "\n"
                    + "Seat: " + temp.seatNumber + "\n"
                    + "Time: " + temp.bookingTime);
            System.out.println("----------------------------------");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();

        system.bookAtBeginning(101, "Alice", "Inception", 12, "5:00 PM");
        system.bookAtBeginning(102, "Bob", "Avatar", 15, "6:30 PM");
        system.bookAtEnd(103, "Charlie", "Titanic", 9, "7:00 PM");
        system.bookAtPos(104, "David", "The Godfather", 20, "8:00 PM", 3);
        
        system.displayTickets();
        
        system.searchTicket("Alice");
        system.searchTicket("Interstellar");
    }
}
