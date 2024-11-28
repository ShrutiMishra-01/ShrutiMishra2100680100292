# ShrutiMishra292

### **Overview:

This program identifies the longest and second-longest compound words from a sorted word list. A **compound word** is a word that can be formed by concatenating two or more shorter words from the same list. The solution is designed to handle large datasets efficiently, focusing on performance and correctness.

### **Approach and Design Decisions:**

1. **Data Structures:**  
   - **Trie:** Used to store and search for words efficiently. This structure ensures quick prefix lookups, reducing the time complexity of word validation.
   - **HashSet:** All words are stored in a HashSet for O(1) lookup, ensuring rapid checks for word existence.

2. **Algorithm:**  
   - **Insertion:** Each word is inserted into the Trie.
   - **Processing:** Words are sorted by length in descending order. Each word is checked recursively to determine if it can be formed by combining other words in the list.
   - **Optimization:** Recursive calls use memoization to avoid redundant computations, improving performance for large files.
   - 
### **Steps to Execute the Code:**

1. **Prerequisites:**  
   - Java Development Kit (JDK) installed (version 8 or later).  
   - Ensure the input file (`Input_01.txt` or `Input_02.txt`) is available in the project directory.

2. **Compilation:**  
   ```bash
   javac CompoundWordAnalyzer.java
   ```

3. **Execution:**  
   ```bash
   java CompoundWordAnalyzer
   ```
   - Modify the `filePath` variable in the code to specify the desired input file.

### **Output:**  
The program prints:
- Longest compound word.
- Second-longest compound word.
- Processing time in milliseconds.

---

