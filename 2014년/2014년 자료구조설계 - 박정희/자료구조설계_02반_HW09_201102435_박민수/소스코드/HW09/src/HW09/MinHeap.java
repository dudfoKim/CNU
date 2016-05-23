package HW09;

public class MinHeap {
	private trecord[] heap;
	private int size;

	public MinHeap(int a) {
		if ( a < 1 )
            throw new IllegalArgumentException("initialCapacity must be >= 1");
		heap = new trecord[a + 1];
		size = 0;
	}

	public MinHeap() {
		this(10);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	public trecord getMin() {
		return (size == 0) ? null : heap[1];
	}

	public void insert(trecord node) {
		if (size == heap.length - 1)
			System.out.println("Heap is full. No insertion.");
		else {
			int currentNode = ++size;
			while (currentNode != 1 && heap[currentNode / 2].freq > node.freq) {
				heap[currentNode] = heap[currentNode / 2]; 
				currentNode /= 2; 
			}
			heap[currentNode] = node;
		}
	}

	public trecord removeMin() {
		if (size == 0)
			return null;
		trecord min = heap[1];
		trecord last = heap[size--];

		int current = 1, child = 2;
		while (child <= size) {
			if (child < size && heap[child].freq > heap[child + 1].freq)
				child++;
			if (last.freq <= heap[child].freq)
				break;
			heap[current] = heap[child];
			current = child;
			child *= 2;
		}
		heap[current] = last;
		return min;
	}

}
