/**
 * Lab: Fix Singly Linked Ordered List -----------------------------------
 * The insertNode() and deleteNode() methods contain logic bugs. 
 * 1. Predict what each method should do (draw before/after pictures).
 * 2. Use the debugger or print statements to trace previous/current.
 * 3. Fix the code so the list stays in sorted
 * order after insertions and nodes are correctly deleted when found.
 * Add comments above your fixes explaining what was wrong and why.
 */

public class FixSinglyLinkedOrderedList
{

	// Test the Singly Linked List
	public static void main(String[] args)
	{

		SinglyLinkedListFix list = new SinglyLinkedListFix();
		list.printList();

		// Use your unit testing to ensure it handles all cases
		list.insertNode(2);
		list.insertNode(4);
		list.insertNode(6);

		//list.printList();

		list.printList();
		
		//deletes 4 and it would print 2 -> 6 -> null
		list.deleteNode(4);
		list.printList();
	}

}

class SinglyLinkedListFix
{
	NodeFix head;

	public void insertNode(int number)
	{
		NodeFix newNode = new NodeFix(number);
		NodeFix current = head;
		NodeFix previous = null;

		while (current != null && current.data < number)
		{
			previous = current;
			current = current.next;
		}

		if (previous == null)
		{
			//Inserts at the head
			newNode.next = head;
			head = newNode;
		} else
		{
			previous.next = newNode;
			newNode.next = current;//Points new node to the current node
		}
	}

	public void deleteNode(int number)
	{
		NodeFix current = head;
		NodeFix previous = null;

		/**Fixed while loop by deleting .next in current.
		 * Problem was that it skipped last node and gave out a NullPointerException
		 */
		while (current != null && current.data != number)
		{
			previous = current;
			current = current.next;
		}
 
		if (previous == null)
		{
			head = current.next;
			
		  //Added an if else statement to prevent error after runtime when last node was not found.
		} if (current == null) {
			System.out.println("Node " + number + " was not found");
		}
		if (previous == null) {		
			previous = current.next; // Bug #5: Should be previous.next = current.next
		}
		else {			
			previous.next = current.next; //Between the middle of the nodes (4)
		}
	}

	public void printList()
	{
		NodeFix current = head;
		while (current != null)
		{
			System.out.print(current.data + " → ");
			current = current.next;
		}
		System.out.println("null");
	}

	private static class NodeFix
	{
		int data;
		NodeFix next;

		public NodeFix(int data)
		{
			this.data = data;
			this.next = null;
		}
	}
}