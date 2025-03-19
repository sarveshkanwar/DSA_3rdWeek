package week3_assignment1;

class Student {
	int RollNumber;
	String Name;
	int age;
	String grade;
	Student next;
	
	public Student(int RollNumber, String Name, int age, String grade) {
		this.RollNumber = RollNumber;
		this.Name = Name;
		this.age = age;
		this.grade = grade;
		this.next = null;
	}
}

class StudentList {
	Student head;
	
	void insert(StudentList studentlist,int RollNumber, String Name, int age, String grade) {
		Student studentNode = new Student(RollNumber, Name, age, grade);
		
		if(studentlist.head == null) {
			studentlist.head = studentNode;
		}else {
			Student temp = studentlist.head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = studentNode;
		}
	}
	
	void insertAtBeginning(StudentList studentlist,int RollNumber, String Name, int age, String grade) {
		Student studentNode = new Student(RollNumber, Name, age, grade);
		Student temp  = head;
		head = studentNode;
		head.next = temp;
		
	}
	
	
	void insertAtEnd(StudentList studentlist,int RollNumber, String Name, int age, String grade) {
		Student studentNode = new Student(RollNumber, Name, age, grade);
		
		if(studentlist.head == null) {
			studentlist.head = studentNode;
		}else {
			Student temp = studentlist.head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = studentNode;
		}
		
	}
	
	
	void insertAtPosition(int RollNumber, String Name, int age, String grade,int position ,StudentList studentlist ) {
		Student studentNode = new Student(RollNumber, Name, age, grade);
		int cnt = 1;
		
		 Student curr = studentlist.head;
		 Student prev  = null;
		while(cnt != position && curr != null) {
			prev = curr;
			curr = curr.next;
			cnt++;
		}
		
		prev.next = studentNode;
		studentNode.next = curr;
		
		
	}
	
	
	void deleteByRollNumber(StudentList studentlist, int rollnumber) {
		if(studentlist.head == null) {
			System.out.println("Student record is empty");
		}else {
			if(studentlist.head.RollNumber == rollnumber) { 
				studentlist.head = studentlist.head.next.next;
			}
		}
		
	}
	
	void searchByRollNumber(StudentList studentlist, int rollnumber) {
		if(studentlist.head == null) {
			System.out.println("Student record is empty");
		}else {
			Student temp = studentlist.head;
			while(temp.next != null) {
				if(temp.RollNumber == rollnumber) {
					System.out.println("Rollnumber found");
					break;
				}
				temp = temp.next;
			}
		}
	}
	
	void updateGrade(StudentList studentlist, String updategrade,int rollnumber) {
		if(studentlist.head == null) {
			System.out.println("Student grade is not found");
		}else {
			Student temp = studentlist.head;
			while(temp.next != null) {
				if(temp.RollNumber == rollnumber){
					temp.grade = updategrade;
					break;
				}
				temp = temp.next;
			}
		}
	}
	
	
	void displayRecords(StudentList studentlist) {
		if(studentlist.head == null) {
			System.out.println("Student record is not found");
		}else {
			Student temp = studentlist.head;
			while(temp != null) {
				System.out.println("Rollnumber : "+temp.RollNumber+"\n"+"Student name : "+temp.Name+"\n"+"Student age : "+temp.age+"\n"+"Student grade : "+temp.grade);	
				temp = temp.next;
			}
		}
	}
}


public class StudentManagement {
	public static void main(String[] args) {
		
		StudentList studentlist = new StudentList();
		
		studentlist.insert(studentlist,2110990935,"Nazim",21, "A");
		studentlist.insert(studentlist,2110991389,"Subham",21, "A");
		studentlist.insert(studentlist,2110992017,"Rahul",21, "A");
		
		studentlist.displayRecords(studentlist);
        System.out.println();

		
		
		
		studentlist.insertAtBeginning(studentlist, 2110991388, "Ajay", 22, "B");
		studentlist.displayRecords(studentlist);
		System.out.println();
		
		
		
		
		studentlist.insertAtEnd(studentlist, 2110991313, "Farhan", 22, "A");
		studentlist.displayRecords(studentlist);
		System.out.println();
		
		
		
		
		studentlist.insertAtPosition(21109954, "Manav", 20, "A", 2, studentlist);
		studentlist.displayRecords(studentlist);
		System.out.println();
		
		
		
		studentlist.deleteByRollNumber(studentlist, 2110991388);
		studentlist.displayRecords(studentlist);
		System.out.println();
		
		
		
		
		studentlist.searchByRollNumber(studentlist, 2110990935);
		System.out.println();
		
	
		
		studentlist.updateGrade(studentlist,"O",2110990935);
		studentlist.displayRecords(studentlist);
		
		

	}

}
