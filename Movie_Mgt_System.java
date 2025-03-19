package week3_assignment1;

import java.util.*;

class Node {
	String movieTitle;
	String director;
	String releaseDate;
	double rating;
	Node next;
	Node prev;
	
	Node(String movieTitle, String director, String releaseDate, double rating) {
		this.movieTitle = movieTitle;
		this.director = director;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.next = null;
		this.prev = null;
	}
}

class DoublyLL {
	Node head;
	
	public void addAtBeginning(String movieTitle, String director, String releaseDate, double rating) {
		Node node = new Node(movieTitle, director, releaseDate, rating);
		
		node.next = head;
		node.prev = null;
		if(head != null) {
			head.prev = node;
		}
		head = node;
	}
	
	
	public void addAtEnd(String movieTitle, String director, String releaseDate, double rating) {
		Node node = new Node(movieTitle, director, releaseDate, rating);
		
		if(head == null) {
			head = node;
			return;
		}
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
		node.prev = temp;
		
	}
	
	
	
	public void display() {
		Node node = head;
		
		while(node != null) {
			System.out.println("Movie 		 : "+node.movieTitle+"\n"+
							   "Director 	 : "+node.director+"\n"+
							   "Release Date  	 : "+node.releaseDate+"\n"+
							   "Rating       	 : "+node.rating);
			System.out.println("----------------------------------");
			node = node.next;
		}
		System.out.println();
	}
}


public class MovieManagementSystem {

	public static void main(String[] args) throws Exception {
		DoublyLL list = new DoublyLL();
		
		list.addAtBeginning("Baaghi 3", "Sajid Nadiadwala","24-01-2025", 3.7);
		list.addAtBeginning("Chaava", "Sanjay Lila Bansali","14-02-2025", 4.9);
		
		list.addAtEnd("Simbha","Disney","30-01-2025",4.8);
		
		list.display();

	}

}
