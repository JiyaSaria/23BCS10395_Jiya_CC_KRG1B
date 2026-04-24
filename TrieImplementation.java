import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;

    TrieNode() {
        isEnd = false;
    }
}

public class Trie {

    static TrieNode root = new TrieNode();

    // Insert word
    static void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];
        }

        node.isEnd = true;
    }

    // Search word
    static boolean search(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) return false;

            node = node.children[idx];
        }

        return node.isEnd;
    }

    // StartsWith
    static boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) return false;

            node = node.children[idx];
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of operations

        while (n-- > 0) {
            String op = sc.next();
            String word = sc.next();

            if (op.equals("insert")) {
                insert(word);
            } else if (op.equals("search")) {
                System.out.println(search(word));
            } else if (op.equals("prefix")) {
                System.out.println(startsWith(word));
            }
        }
    }
}