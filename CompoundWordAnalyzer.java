import java.io.*;
import java.util.*;

public class CompoundWordAnalyzer {
    public static void main(String[] args) {
        String filePath = "Input_02.txt"; // Change this to "Input_02.txt" for larger datasets
        findTopCompoundWords(filePath);
    }

    public static void findTopCompoundWords(String fileLocation) {
        long startTime = System.currentTimeMillis();

        List<String> wordCollection = loadWordsFromFile(fileLocation);
        TrieNode trieRoot = new TrieNode();

        // Insert each word into the Trie
        for (String term : wordCollection) {
            insertIntoTrie(trieRoot, term);
        }

        // Sort words by length in descending order
        wordCollection.sort((word1, word2) -> word2.length() - word1.length());

        String longestCompoundWord = "";
        String secondLongestCompoundWord = "";

        for (String candidate : wordCollection) {
            if (isCompound(candidate, trieRoot, 0, 0)) {
                if (longestCompoundWord.isEmpty()) {
                    longestCompoundWord = candidate;
                } else {
                    secondLongestCompoundWord = candidate;
                    break;  // Stop after finding both longest and second longest compound words
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Longest Compound Word: " + longestCompoundWord);
        System.out.println("Second Longest Compound Word: " + secondLongestCompoundWord);
        System.out.println("Processing time for file " + fileLocation + ": " + elapsedTime + " milliseconds");
    }

    private static List<String> loadWordsFromFile(String fileLocation) {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                wordList.add(currentLine.trim());
            }
        } catch (IOException exception) {
            System.out.println("Error while reading the file: " + exception.getMessage());
        }
        return wordList;
    }

    // Definition of Trie Node
    static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isWordEnd = false;
    }

    private static void insertIntoTrie(TrieNode rootNode, String wordToAdd) {
        TrieNode currentNode = rootNode;
        for (char character : wordToAdd.toCharArray()) {
            currentNode = currentNode.childNodes.computeIfAbsent(character, letter -> new TrieNode());
        }
        currentNode.isWordEnd = true;
    }

    private static boolean isCompound(String wordToCheck, TrieNode rootNode, int startIndex, int partCount) {
        TrieNode currentNode = rootNode;
        for (int i = startIndex; i < wordToCheck.length(); i++) {
            char currentChar = wordToCheck.charAt(i);
            if (!currentNode.childNodes.containsKey(currentChar)) {
                return false;
            }
            currentNode = currentNode.childNodes.get(currentChar);
            if (currentNode.isWordEnd) {
                // If the word can be formed and there are more characters to check recursively
                if (i == wordToCheck.length() - 1) {
                    return partCount >= 1;  // Ensure it’s formed from at least two words
                }
                if (isCompound(wordToCheck, rootNode, i + 1, partCount + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}