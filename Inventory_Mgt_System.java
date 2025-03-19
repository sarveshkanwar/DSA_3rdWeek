package week3_assignment1;


class Item {
	String itemName;
	int itemID;
	int quantity;
	double price;
	Item next;
	
	public Item(String itemName, int itemID, int quantity, double price) {
		this.itemName = itemName;
		this.itemID = itemID;
		this.quantity = quantity;
		this.price = price;
	}	
}

class ItemList {
	Item head;
	
	void insert(ItemList itemlist, String itemName, int itemID, int quantity, double price) {
		Item node = new Item(itemName, itemID, quantity, price);
		
		if(itemlist.head == null) {
			itemlist.head = node;
		}else {
			Item temp = itemlist.head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
		}
	}
	
	void addAtBeginning(ItemList itemlist, String itemName, int itemID, int quantity, double price) {
		Item node = new Item(itemName, itemID, quantity, price);
		
		Item temp = head;
		head = node;
		head.next = temp;
		
	}
	
	void addAtEnd(ItemList itemlist, String itemName, int itemID, int quantity, double price) {
		Item node = new Item(itemName, itemID, quantity, price);
		
		if(itemlist.head == null) {
			itemlist.head = node;
		}else {
			Item temp = itemlist.head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
		}
	}
	
	
	void removeItem(ItemList itemlist, String itemName, int itemID, int quantity, double price,int id) {
		Item node = new Item(itemName, itemID, quantity, price);
		Item temp = itemlist.head;
		while(temp.next != null) {
			if(itemlist.head.itemID == id){
				itemlist.head = itemlist.head.next.next;
				System.out.println("Item removed from list");
				break;
			}else {
				System.out.println("Item not found in the list");
			}
			temp = temp.next;
		}
	}
	
	
	void updateQuantity(ItemList itemlist, String itemName, int itemID, int quantity, double price,int id,int qty) {
		Item node = new Item(itemName, itemID, quantity, price);
		if(itemlist.head == null) {
			System.out.println("List is empty");
		}else {
			Item temp = itemlist.head;
			while(temp.next != null) {
				if(itemlist.head.itemID == id) {
					temp.quantity = qty;
					System.out.println("Quantity updated");
					break;
				}
				temp = temp.next;
			}
		}
	}
	
	
	boolean searchItem(ItemList itemlist, String itemName, int itemID, int quantity, double price,int id, String name) {
		Item node = new Item(itemName, itemID, quantity, price);
		if(itemlist.head == null) {
			System.out.println("List is empty");
		}else {
			Item temp = itemlist.head;
			while(temp.next != null) {
				if(itemlist.head.itemID == id && itemlist.head.itemName == name) {
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
	}
	
	
	void calculateTotal(ItemList itemlist) {
	    if (itemlist.head == null) {
	        System.out.println("Inventory is empty. Total cost: 0");
	        return;
	    }

	    Item temp = itemlist.head;
	    double total = 0;

	    while (temp.next != null) {
	        total += temp.price * temp.quantity;
	        temp = temp.next;
	    }

	    System.out.println("Total cost: " + total);
	}

	
	
	
	
	void displayInventory(ItemList itemlist) {
		if(itemlist.head == null) {
			System.out.println("List is empty");
		}else {
			Item temp = itemlist.head;
			while(temp!= null) {
				System.out.println("Item Name : "+temp.itemName + "\n" + 
								   "Item ID   : "+temp.itemID + "\n" + 
								   "Quantity  : "+temp.quantity+"\n" + 
								   "Price     : "+temp.price);
				System.out.println();
				temp = temp.next;
			}
		}
	}
}



public class InventoryManagementSystem {

	public static void main(String[] args) {
		
		ItemList itemlist = new ItemList();
		itemlist.insert(itemlist, "Chips",1, 2, 10.00);
		itemlist.insert(itemlist, "Oil",2, 1, 45.00);
		itemlist.insert(itemlist, "WashingPowder",3,5, 30.00);
		itemlist.insert(itemlist, "Shampoo",4, 2, 25.00);
		
		System.out.println("----------------------- Displaying Inventory -----------------------");
		itemlist.displayInventory(itemlist);
		
		System.out.println();
		
		
		System.out.println("----------------------- Adding Item at the beginning in Inventory -----------------------");
		itemlist.addAtBeginning(itemlist, "Maggie", 10, 6, 15.00);
		itemlist.displayInventory(itemlist);
		
		System.out.println();
		
		
		
		System.out.println("----------------------- Adding Item at the End in Inventory -----------------------");
		itemlist.addAtEnd(itemlist, "Soap", 15, 10, 20.00);
		itemlist.displayInventory(itemlist);
		
		System.out.println();
		
		
		
		//itemlist.removeItem();
		
		System.out.println("----------------------- Updating quantity of the item -----------------------");
		itemlist.updateQuantity(itemlist, "Chips",1, 2, 10.00,1,2);
		itemlist.displayInventory(itemlist);
		
		System.out.println();
		
		
		
		
		System.out.println("----------------------- Searching the item from the inventory -----------------------");
		if(itemlist.searchItem(itemlist, "Maggie", 10, 6, 15.00,10,"Maggie") == true) {
			System.out.println("Item found in the inventory");
		}else {
			System.out.println("Item not found in the inventory or itemID is entered wrong of the item!!!");
		}
		
		System.out.println();
		
		
		
		System.out.println("----------------------- Calculating the total cost of inventory -----------------------");
		itemlist.calculateTotal(itemlist);
	}

}
