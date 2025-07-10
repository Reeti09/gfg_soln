import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // For 'a' through 'z'
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }
}

class Solution {
    public String longestString(String[] words) {
        // Step 1: Sort the words lexicographically.
        // This is crucial for the tie-breaking condition: if multiple strings
        // have the same maximum length, return the lexicographically smallest one.
        // By processing words in alphabetical order, when BFS finds words of
        // equal length, the one discovered first will be lexicographically smaller.
        Arrays.sort(words);

        // Step 2: Build the Trie
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        String longestFoundWord = "";

        // Step 3: Perform BFS to find the longest valid string
        // Queue stores String objects representing the words formed so far.
        Queue<String> queue = new LinkedList<>();

        // Initialize the queue with valid single-character words from the Trie.
        // We iterate through 'a' to 'z' to maintain lexicographical order for the initial words.
        // This loop directly adds words that are single characters and exist in the 'words' array.
        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            if (trie.root.children[i] != null && trie.root.children[i].isEndOfWord) {
                // If the single character itself forms a valid word, add it to the queue
                String initialWord = String.valueOf(ch);
                queue.offer(initialWord);
            }
        }
        
        // The `longestFoundWord` will be updated as valid words are found during the BFS.

        while (!queue.isEmpty()) {
            String currentWord = queue.poll();

            // Update longestFoundWord based on length and lexicographical order
            // If currentWord is longer, it's a new best candidate.
            if (currentWord.length() > longestFoundWord.length()) {
                longestFoundWord = currentWord;
            } 
            // If currentWord has the same length, compare lexicographically.
            // Since `words` was sorted, and we process children in alphabetical order
            // in the BFS, if a word of equal length is found, `currentWord` will be
            // lexicographically smaller or equal to the currently stored `longestFoundWord`.
            // We update if it's strictly smaller.
            else if (currentWord.length() == longestFoundWord.length()) {
                if (currentWord.compareTo(longestFoundWord) < 0) {
                    longestFoundWord = currentWord;
                }
            }
            
            // Find the TrieNode corresponding to the `currentWord`
            TrieNode currentNode = trie.root;
            for(char ch : currentWord.toCharArray()) {
                // This check is implicitly guaranteed to pass because currentWord itself
                // must have had all its prefixes (including itself) marked as isEndOfWord
                // to be added to the queue in the first place.
                // However, for robustness or if the logic were slightly different,
                // a null check here might be warranted. For this specific implementation, it's safe.
                currentNode = currentNode.children[ch - 'a'];
            }

            // Explore children (next characters) from the current node in alphabetical order
            for (int i = 0; i < 26; i++) {
                char nextChar = (char) ('a' + i);
                if (currentNode.children[i] != null && currentNode.children[i].isEndOfWord) {
                    // This is the crucial check: The next character forms a valid prefix
                    // only if the node representing it in the Trie is also marked as an end of a word.
                    String nextWord = currentWord + nextChar;
                    queue.offer(nextWord);
                }
            }
        }

        return longestFoundWord;
    }
}