import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

class Lru {
    static int pageFaults(int pages[], int n, int capacity) {
        // HashSet for storing current pages in memory
        HashSet<Integer> s = new HashSet<>(capacity);
        
        // HashMap to store least recently used indexes
        HashMap<Integer, Integer> indexes = new HashMap<>();
        
        int page_faults = 0;
        
        for (int i = 0; i < n; i++) {
            // If memory has space available
            if (s.size() < capacity) {
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
            // If memory is full
            else {
                if (!s.contains(pages[i])) {
                    int lru = Integer.MAX_VALUE;
                    int val = Integer.MIN_VALUE;
                    
                    // Find the least recently used page
                    Iterator<Integer> itr = s.iterator();
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru) {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    
                    // Remove least recently used page and add new page
                    s.remove(val);
                    indexes.remove(val);
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
        }
        return page_faults;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of pages
        System.out.print("Enter the number of pages: ");
        int n = scanner.nextInt();
        
        // Create array to store page reference sequence
        int pages[] = new int[n];
        
        // Input the page reference sequence
        System.out.println("Enter the page reference sequence:");
        for(int i = 0; i < n; i++) {
            System.out.print("Enter page " + (i+1) + ": ");
            pages[i] = scanner.nextInt();
        }
        
        // Get the capacity of memory frames
        System.out.print("Enter the number of frames: ");
        int capacity = scanner.nextInt();
        
        // Calculate and display the number of page faults
        int faults = pageFaults(pages, n, capacity);
        System.out.println("\nPage reference sequence:");
        for(int i = 0; i < n; i++) {
            System.out.print(pages[i] + " ");
        }
        System.out.println("\nNumber of frames: " + capacity);
        System.out.println("Number of page faults: " + faults);
        
        scanner.close();
    }
}