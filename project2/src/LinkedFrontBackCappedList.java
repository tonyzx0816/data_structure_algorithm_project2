public class LinkedFrontBackCappedList<T extends Comparable<? super T>> implements FrontBackCappedList<T>, Comparable<LinkedFrontBackCappedList<T>> {

	private Node head, tail;
	private int numberOfElements, capacity;
   
	@Override
	public int compareTo(LinkedFrontBackCappedList<T> other)  {
		Node thisCurrent = head;
		Node otherCurrent = other.head;
		while(thisCurrent!=null & otherCurrent!=null) {
			T thisElement = thisCurrent.data;
			T otherElement = otherCurrent.data;
			int comparison = thisElement.compareTo(otherElement);
			if(comparison<0)
				return -1;
			else if(comparison>0)
				return 1;
			else {
				thisCurrent = thisCurrent.next;
				otherCurrent = otherCurrent.next;
			}
		}
		if(size()>other.size())
			return 1;
		else if(size()<other.size())
			return -1;
		return 0;
	}
	
	public LinkedFrontBackCappedList(int capacity) {
		head = null;
		tail = null;
		this.capacity = capacity;
		numberOfElements = 0;
	}
	
	public String toString() {
		Node currentNode = head;
		String s = "[";
		String frontPart = "]" + "\tsize=" + size() + "\tcapacity=" + capacity;
		while(currentNode!=null) {
			s += currentNode.data + ", ";
			currentNode = currentNode.next;
		}
		if(isEmpty())
			s += frontPart;
		else {
			s = s.substring(0,s.length()-2);
			s += frontPart + "\thead=" + head.data + " tail=" + tail.data;
		} return s;
	}
	
	public boolean isEmpty() {
		return numberOfElements==0 && head==null;
	}
	
	public boolean isFull() {
		return numberOfElements >= capacity;
	}
	
	public int size() {
		return numberOfElements;
	}
	
	private boolean isValidPosition(int position) {
		return position>-1 && position<numberOfElements;
	}
	
	public T getEntry(int index) {
		if(isValidPosition(index)) {
			Node currentNode = head;
			for(int i=0; i<index; i++) {
				currentNode = currentNode.next;
			} return currentNode.data;
		} return null;
	}
	
	public void clear() {
		while(head!=null) {
			head.data = null;
			head = head.next;
		}
		numberOfElements = 0;
	}
	
	public boolean addFront(T entry) {
		if(!isFull()) {
			Node newNode = new Node(entry);
			if(isEmpty()) {
				head = newNode;
				tail = newNode;
				numberOfElements++;
				return true;
			} else {
				newNode.next = head;
				head = newNode;
				numberOfElements++;
				return true;
			}
		} return false;
	}
	
	public boolean addBack(T entry) {
		if(!isFull()) {
			Node newNode = new Node(entry);
			if(isEmpty()) {
				head = newNode;
				tail = newNode;
				numberOfElements++;
				return true;
			} else {
				tail.next = newNode;
				tail = newNode;
				numberOfElements++;
				return true;
			}
		} return false;
	}
	
	public T removeFront() {
		if(!isEmpty()) {
			T originalData = head.data;
			if(size()==1) {
				head = null;
				tail = null;
				numberOfElements--;
			} else {
				head = head.next;
				numberOfElements--;
			} return originalData;
		} return null;
	}
	
	public T removeBack() {
		if(!isEmpty()) {
			T originalData = tail.data;
			if(size()==1) {
				head = null;
				tail = null;
				numberOfElements--;
			} else {
				Node currentNode = head;
				for(int i=0; i<size()-2; i++)
					currentNode = currentNode.next;
				tail = currentNode;
				tail.next = null;
				numberOfElements--;
			} return originalData;
		} return null;
	}
	
	public boolean contains(T entry) {
		int index = indexOf(entry);
		if(index==-1)
			return false;
		return true;
	}
	
	public int indexOf(T entry) {
		Node currentNode = head;
		for(int i=0; i<size(); i++) {
			if(currentNode.data.equals(entry))
				return i;
			currentNode = currentNode.next;
		} return -1;
	}
	
	public int lastIndexOf(T entry) {
		Node currentNode = head;
		int index = -1;
		for(int i=0; i<size(); i++) {
			if(currentNode.data.equals(entry)) {
				index = i;
				currentNode = currentNode.next;
			} else {
				currentNode = currentNode.next;
			}
		} return index;
	}
	
	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	} 
}

