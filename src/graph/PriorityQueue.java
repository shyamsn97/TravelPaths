package graph;

/** A priority queue: implemented using a min heap. */
public class PriorityQueue {

	private PriorityObject[] heap; // the array to store the heap
	private int maxsize; // the size of the array
	private int size; // the current number of elements in the array
	private int[] positions;

	/**
	 * Constructor
	 * @param max the maximum size of the heap
	 */
	public PriorityQueue(int max) {

		maxsize = max;
		heap = new PriorityObject[maxsize+1];
		size = 0;
		PriorityObject p = new PriorityObject(0,Integer.MIN_VALUE); 		// Assigned MIN_VALUE so that it's easier to bubble up
		heap[0] = p;
		positions = new int[maxsize];
		for (int i = 1; i < positions.length; i++) {
		    positions[i-1] = i;
        }
	}

	/** Return the index of the left child of the element at index pos
	 *
	 * @param pos the index of the element in the heap array
	 * @return the index of the left child
	 */
	private int leftChild(int pos) {
		return 2 * pos;
	}

	/** Return the index of the right child
	 *
	 * @param pos the index of the element in the heap array
	 * @return the index of the right child
	 */
	private int rightChild(int pos) {
		return 2 * pos + 1;
	}

	/** Return the index of the parent
	 *
	 * @param pos the index of the element in the heap array
	 * @return the index of the parent
	 */
	private int parent(int pos) {
		return pos / 2;
	}

	/** Returns true if the node in a given position is a leaf
	 *
	 * @param pos the index of the element in the heap array
	 * @return true if the node is a leaf, false otherwise
	 */
	private boolean isLeaf(int pos) {
		return ((pos > size / 2) && (pos <= size));
	}

	/** Swap given elements: one at index pos1, another at index pos2
	 *
	 * @param pos1 the index of the first element in the heap
	 * @param pos2 the index of the second element in the heap
	 */
	private void swap(int pos1, int pos2) {
		PriorityObject tmp;
		tmp = heap[pos1];
		int another;
		another = positions[heap[pos1].retID()];
		positions[heap[pos1].retID()] = positions[heap[pos2].retID()];
		positions[heap[pos2].retID()] = another;
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}


	/** Insert a new element (nodeId, priority) into the heap.
     *  For this project, the priority is the current "distance"
     *  for this nodeId in Dikstra's algorithm. */
	public void insert(int nodeId, int priority) {

		PriorityObject elem = new PriorityObject(nodeId,priority);
		size++;
		heap[size] = elem;
		int current = size;
		while (heap[current].retPriority() < heap[parent(current)].retPriority()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

    /**
     * Remove the element with the minimum priority
     * from the min heap and return its nodeId.
     * @return nodeId of the element with the smallest priority
     */
	public int removeMin() {

		swap(1, size); // swap the end of the heap into the root
		size--;  	   // removed the end of the heap
		// fix the heap property - push down as needed
		if (size != 0)
			pushdown(1);
		return heap[size + 1].retID();

	}


	/**
     * Reduce the priority of the element with the given nodeId to newPriority.
     * @param nodeId id of the node
     * @param newPriority new value of priority
     */

	public void reduceKey(int nodeId, int newPriority) {
		heap[positions[nodeId]].setPriority(newPriority);
		int current = positions[nodeId];
		while (heap[current].retPriority() < heap[parent(current)].retPriority()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}


	/** Push the value down the heap if it does not satisfy the heap property
	 *
	 * @param position the index of the element in the heap
	 */
	private void pushdown(int position) {
		int smallestchild;
		while (!isLeaf(position)) {
			smallestchild = leftChild(position); // set the smallest child to left child
			if ((smallestchild < size) && (heap[smallestchild].retPriority() > heap[smallestchild + 1].retPriority()))
				smallestchild = smallestchild + 1; // right child was smaller, so smallest child = right child

			// the value of the smallest child is less than value of current,
			// the heap is already valid
			if (heap[position].retPriority() <= heap[smallestchild].retPriority())
				return;
			swap(position, smallestchild);
			position = smallestchild;
		}
	}

	public void printToString() {
	    for (int i = 1; i < heap.length; i++) {
	        if (heap[i] != null) {
                System.out.println(heap[i].retID() + " : " + heap[i].retPriority());
            }
        }
    }


}

class PriorityObject {

	int nodeid;
	int priority;

	PriorityObject(int nodeid, int priority) {
		this.nodeid = nodeid;
		this.priority = priority;
	}

	public int retID() {
		return nodeid;
	}

	public int retPriority() {
		return priority;
	}

	public void setPriority(int prior) {
		this.priority = prior;
	}
}

