import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        this.heap = new int[capacity];
        this.size = 0;
    }

    public MinHeap(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.heap = Arrays.copyOf(data, data.length);
        this.size = data.length;
        buildHeap();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty");
        }
        return heap[0];
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        siftUp(size);
        size++;
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            heapify(0);
        }
        return min;
    }

    // Heapify (sift-down) from index i to restore min-heap property.
    public void heapify(int i) {
        while (true) {
            int left = left(i);
            int right = right(i);
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    public void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            heapify(i);
        }
    }

    private void siftUp(int i) {
        while (i > 0) {
            int p = parent(i);
            if (heap[p] <= heap[i]) {
                break;
            }
            swap(p, i);
            i = p;
        }
    }

    private void ensureCapacity() {
        if (size < heap.length) {
            return;
        }
        int newCap = Math.max(1, heap.length * 2);
        heap = Arrays.copyOf(heap, newCap);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // Simple demo
    public static void main(String[] args) {
        int[] data = {5, 3, 8, 1, 2, 7, 6, 4};
        MinHeap h = new MinHeap(data);
        System.out.println("Min: " + h.peek());
        h.insert(0);
        while (!h.isEmpty()) {
            System.out.print(h.extractMin() + " ");
        }
        System.out.println();
    }
}
