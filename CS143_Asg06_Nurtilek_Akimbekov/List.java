/**
 * @author Nurtilek Akimbekov
*/
import java.util.Stack;

public class List extends HasState {
    
    // Node class to represent each element in the list
    private class Node {
        int value;   
        Node next;      

        /**
           Constructor.            
           @param val The element to store in the node.
           @param n The reference to the successor node.
        */
        Node(int val, Node n) {
            value = val;
            next = n;
        } 
        
        /**
           Constructor. 
           @param val The element to store in the node.
        */
        Node(int val) {
            this(val, null);            
        }
    }	

    private Node first;  // list head

    /**
       Constructor.
    */
    public List() {
        first = null;  
    }

    /**
       The isEmpty method checks to see 
        if the list is empty.
        @return true if list is empty, false otherwise.
    */
    public boolean isEmpty() {        
        return first == null;       
    }

    /**
       The size method returns the length of the list.
       @return The number of elements in the list.
    */
    public int size() {
        int count = 0;
        Node p = first;     
        while (p != null) {
            count ++;
            p = p.next;
        }
        return count;
    }

    /**
       The add method adds an element to
		 the end of the list.
       @param e The value to add to the
		 end of the list.       
    */
    public void add(int e) {
        if (isEmpty()) {
            first = new Node(e);
        } else {
            Node current = first;
            while (current.next != null) current = current.next;
            current.next = new Node(e);
        }      
    }

    /**
       The add method adds an element at a position.
       @param e The element to add to the list.
       @param index The position at which to add 
		 the element.
       @exception IndexOutOfBoundsException When 
		 index is out of bounds.  
    */
    public void add(int index, int val) {
        if (index < 0) throw new IndexOutOfBoundsException(String.valueOf(index));
        if (index == 0) {
            first = new Node(val, first);     
            return;
        }

        Node cur = first;
        int count = 0;
        while (cur != null && count != index - 1) {
            cur = cur.next;
            count++;
        }
        if (cur == null) throw new IndexOutOfBoundsException(String.valueOf(index));
        Node tmp = new Node(val, cur.next);
        cur.next = tmp;
    }

    /**
       The toString method computes the string
       representation of the list.
       @return The string form of the list.
    */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        
        Node p = first;
        while (p != null) {
            strBuilder.append(p.value);
            if (p.next != null) strBuilder.append("->");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
       The reverse method reverses the linked list.
       The method ensures O(N) complexity and avoids creating new nodes.
    */
    public void reverse() {
        if (first == null || first.next == null) return;  // No need to reverse if the list is empty or contains one element

        Node prev = null, current = first, next;
        while (current != null) {
            next = current.next;  // Save the next node
            current.next = prev;  // Reverse the current node's pointer
            prev = current;       // Move the previous pointer forward
            current = next;       // Move the current pointer forward
        }
        first = prev;  // Update the first pointer to the new head of the reversed list
    }

    /**
       The clone method creates a deep copy of the list.
       It traverses the original list and creates new nodes to form the cloned list.
       @return A new List object that is a deep copy of the original list.
    */
    public List clone() {
        List clonedList = new List();
        
        if (first == null) return clonedList;  // Return empty list if original is empty

        // Copy the state
        clonedList.state = this.getObjState();  // Use the inherited method to copy the state

        // Copy the nodes: Deep copy of the list nodes
        Node currentOriginal = first;
        Node currentCloned = new Node(currentOriginal.value);
        clonedList.first = currentCloned;  // Set the first node for the cloned list

        while (currentOriginal.next != null) {
            currentOriginal = currentOriginal.next;
            currentCloned.next = new Node(currentOriginal.value);  // Clone new node
            currentCloned = currentCloned.next;  // Move to the next node
        }

        return clonedList;
    }

    /**
       The smallestFirst method moves the node with the smallest integer 
       value in the list to the first position. 
       It achieves this in O(N) time complexity with no node copying.
    */
    public void smallestFirst() {
        if (first == null || first.next == null) return;  // No need to move anything if the list is empty or has one element
    
        Node prev = null, current = first;
        Node smallestPrev = null, smallest = first;
    
        // Find the smallest node
        while (current != null) {
            if (current.value < smallest.value) {
                smallestPrev = prev;  // Keep track of the previous node of the smallest node
                smallest = current;   // Update the smallest node
            }
            prev = current;   // Move to the next node
            current = current.next;
        }
    
        // If the smallest node is not already at the front, rewire it
        if (smallest != first) {
            smallestPrev.next = smallest.next;  // Remove smallest node from its current position
            smallest.next = first;  // Move the smallest node to the front
            first = smallest;  // Update the head of the list
        }
    }
    

    /**
       The selectionSort method sorts the list using the selection sort algorithm.
       It operates with O(N^2) time complexity and reorders the nodes by changing their links.
    */
    public void selectionSort() {
        if (first == null || first.next == null) return;  // No need to sort if the list is empty or has one element
    
        Node sortedEnd = null;  // Marks the boundary between the sorted and unsorted parts
    
        while (sortedEnd != first) {
            Node prev = null, current = first;
            Node smallestPrev = null, smallest = first;
    
            // Find the smallest node in the unsorted portion of the list
            while (current.next != sortedEnd) {
                if (current.next.value < smallest.value) {
                    smallestPrev = current;
                    smallest = current.next;
                }
                current = current.next;
            }
    
            // Move the smallest node to the front of the unsorted portion
            if (smallest != first) {
                if (smallestPrev != null) smallestPrev.next = smallest.next;  // Remove smallest node from its current position
                smallest.next = first;  // Place smallest node at the front
                first = smallest;  // Update the head of the list
            }
    
            sortedEnd = smallest;  // Update sortedEnd to include the smallest node in the sorted portion
        }
    }
    

    /**
       The reverseToString method generates a string representation of the list in reversed order using iteration.
       @return A string with the reversed order of elements.
    */
    public String reverseToString() {
        if (isEmpty()) return "";  // Handle empty list
        
        Node current = first;
        Stack<Integer> stack = new Stack<>();
        
        // Push all elements onto the stack
        while (current != null) {
            stack.push(current.value);
            current = current.next;
        }
        
        // Pop elements from the stack to form the reversed string
        StringBuilder reversedStr = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedStr.append(stack.pop()).append(" ");
        }
        
        return reversedStr.toString().trim();
    }

    /**
       The recReverseToString method generates a string representation of the list in reversed order using recursion.
       @return A string with the reversed order of elements.
    */
    public String recReverseToString() {
        return recReverseToStringHelper(first).trim();
    }

    /**
       Helper method for recursive reversal of the list.
       @param node The current node to process.
       @return A string with the reversed order of elements.
    */
    private String recReverseToStringHelper(Node node) {
        if (node == null) return "";
        return recReverseToStringHelper(node.next) + node.value + " ";
    }
}
