package week3_assignment1;

import java.util.*;

class LibNode {
	String bookTitle;
	String author;
	String Genre;
	int bookId;
	String available;
	LibNode next;
	LibNode prev;
	
	
	LibNode(String bookTitle, String author, String Genre, int bookId, String available){
		this.bookTitle = bookTitle;
		this.author = author;
		this.Genre = Genre;
		this.bookId = bookId;
		this.available = available;
		this.next = null;
		this.prev = null;
	}
}

class Library {
	LibNode head;
	
	public void addAtBeginning(String bookTitle, String author, String Genre, int bookId, String available) {
		LibNode node = new LibNode(bookTitle, author, Genre, bookId, available);
		
		node.next = head;
		node.prev = null;
		if(head != null) {
			head.prev = node;
		}
		head = node;
	}
	
	
	public void addAtEnd(String bookTitle, String author, String Genre, int bookId, String available) {
		LibNode node = new LibNode(bookTitle, author, Genre, bookId, available);
		
		if(head == null) {
			head = node;
			return;
		}
		LibNode temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
		node.prev = temp;
		
	}
	
	
	public void addAtPos(String bookTitle, String author, String Genre, int bookId, String available,int pos) {
		LibNode node = new LibNode(bookTitle, author, Genre, bookId, available);
		
		if (pos == 1) {
			node.next = head;
			if (head != null) head.prev = node;
			head = node;
			return;
		}
		
		LibNode temp = head;
		for (int i = 1; temp != null && i < pos - 1; i++) 
			temp = temp.next;
		
		if (temp == null) {
			System.out.println("Invalid position");
			return;
		}
		
		node.next = temp.next;
		if (temp.next != null) temp.next.prev = node;
		temp.next = node;
		node.prev = temp;
		
	}
	
	
	void searchByTitle_Author(Library list,String bookTitle, String author) {
		if(list.head == null ) {
			System.out.println("Library record is empty");
		}else {
			LibNode temp = list.head;
			while(temp.next != null) {
				if(temp.author == author || temp.bookTitle == bookTitle) {
					System.out.println("Book found!!!");
				}
				temp = temp.next;
			}
		}
	}
	
	
	boolean bookAvailable(String bookTitle) {
		LibNode node = head;
		while(node != null) {
			if(node.bookTitle == bookTitle) {	
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	
	
	void display() {
		LibNode node = head;
		
		while(node != null) {
			System.out.println("Book 	 : "+node.bookTitle+"\n"+
							   "Author 	 : "+node.author+"\n"+
							   "Genre 	 : "+node.Genre+"\n"+
							   "BookID   : "+node.bookId+"\n"+
							   "Book available ?  : "+node.available);
			System.out.println("----------------------------------");
			node = node.next;
		}
		System.out.println();
	}
	
}

public class LibraryManagementSystem {

	public static void main(String[] args) {
		
		Library list = new Library();
		
		list.addAtBeginning("Three Man in a boat", "James", "Adventure", 1, "Yes");
		list.addAtBeginning("SSB Preparation", "S Chand", "Competition", 2, "Yes");
		
		list.addAtEnd("Atomic Habits", "Henry", "Mind", 3, "Yes");
		list.addAtPos("The Jungle Book", "Champak", "adventure", 4, "No", 3);
		
		list.display();
		
		
		list.searchByTitle_Author(list, "Three Man in a boat", "James");
		
		System.out.println();
		
		list.searchByTitle_Author(list, "JJK", "Momoshiki");
		
	}

}
