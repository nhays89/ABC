
/* Author Nicholas A. Hays */

import java.util.Scanner;

class ABC {

    /**
     * 
     * Creates a sequence, s, consisting of letters 'A', 'B', or 'C' of length N
     * such that exactly K pairs exist in s, where each pair (i,j) (0 <= i < j
     * <= N-1) such that s[i] < s[j].
     * 
     * Example:
     * 
     * N: 3, K: 3 Returns: "ABC" Pairs: [A,B], [A,C], [B,C]
     * 
     * [A,B] = A is at index 0, B is at index 1. Thus because s[0] = 'A' < s[1]
     * ='B' [A,B] at [0,1] is a pair.
     * 
     * [A,C] = A is at index 0, C is at index 2. Thus because s[0] = 'A' < s[2]
     * = 'C'. [A,C] at [0,2] is a pair.
     * 
     * [B,C] = B is at index 1, C is at index 2. Thus because s[0] = 'B' < s[1]
     * = 'C'.[B,C] at [1,2] is a pair.
     * 
     * Reads from input file N K space seperated integer pairs.
     * 
     * To test pull repo and navigate to src folder on cmd line and enter the
     * following 2 commands:
     * 
     * javac ABC.java
     * 
     * java ABC < input.in
     * 
     * Time complexity: O(n)
     * 
     * @param args
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            int n = s.nextInt();
            int k = s.nextInt();
            System.out.println(createString(n, k));
        }
        s.close();
    }

    /*
     * 
     * Sample model:
     * 
     * N: 2, K: 3
     * 
     * A: 0 B: 0 C: 0 seq_length: 0 pairs: 0 current: 'C' seq: ''
     * 
     * A: 0 B: 0 C: 1 seq_length: 1 pairs: 0 current = 'B' seq: 'C'
     * 
     * A: 0 B: 1 C: 1 seq_length: 2 pairs: 1 current = 'A' seq: 'BC'
     * 
     * A: 1 B: 1 C: 1 seq_length: 3 pairs: 3 current = 'C' seq: 'ABC'
     * 
     * A: 1 B: 1 C: 1 seq_length: 3 pairs: 2 current = 'C' seq: 'ACB'
     * 
     * Idea:
     * 
     * 
     * Begin tallying the count of each character of the set {A,B,C} in a
     * sequence of characters by cycling through the set beginning with 'C' then
     * 'B' then 'A' logging the length of the sequence, the number of pairs, and
     * the current character in the sequence at each step until either the
     * length of the sequence has reached N, or the number of pairs of the
     * sequence have reached or exceeded K, in which case the last character
     * added is removed and a C is inserted in the previous sequence at index r,
     * where r is the difference between K and the previous sequence pairs.
     * 
     * Reasoning:
     * 
     * By creating the sequence in this manner, at each step we are guaranteed
     * that all A's will be at indices less than all B's, and all B's will be at
     * indices less than all C's, and the maximum possible K can be reached at
     * any N. Thus, when we add a new character to the sequence, whether it be
     * A, B, or C, we know that it will create a match with every other
     * character excluding those characters that are equal to the character
     * itself while also generating the most possible matches for the current
     * sequence length.
     * 
     * For instance, when adding a B, we know that each A will be at an index
     * less than the new B, and each C will be at an index greater than the new
     * B; therefore, the B will match with each A and each C in the sequence.
     * 
     * Likewise, when adding an A, we know that each B and C will be at an index
     * greater than the new A; thus, the new A will match with each B and C in
     * the sequence.
     * 
     * Finally, when adding a C, we know that each A and B will be at an index
     * less than the new C. Hence, the new C will match with each A and B in the
     * sequence.
     * 
     * Thus, for each new character added to the sequence we can determine the
     * number of new pairs created by accumulating the total of all distinct
     * characters. Together with the previous sequence total, we can track the
     * total number of pairs possible for each new sequence. We continue this
     * cycle until the sequence has reached exactly K pairs. Once it has, we can
     * append As to the sequence until its length is N; however, if the number
     * of pairs exceeds K then we must rearrange the characters in the current
     * sequence in order to reduce the number of total pairs to K, or,
     * alternatively, remove the last character added and return to the previous
     * sequence and insert a character, C, such that it increases the number of
     * pairs, r, by the difference between K and the previous sequence total.
     * For simplicity sake, we will choose the latter. Inserting a 'C' at index
     * r will accomplish this task because each character at index less than r
     * will be less than C. Thus, the previous sequence total plus r will equal
     * K pairs.
     * 
     * 
     * 
     * @param n - the maximum length of the string
     * 
     * @param k - the number of i,j pairs
     * 
     */
    public static String createString(int N, int K) {
        char current = 'C';
        int a = 0, b = 0, c = 0, pairs = 0, seq_length = 0;
        StringBuilder seq = new StringBuilder();
        while (seq_length < N && pairs < K) {

            if (current == 'A') {
                current = 'C';
                a++;
                pairs += b;
                pairs += c;
            } else if (current == 'B') {
                current = 'A';
                b++;
                pairs += a;
                pairs += c;
            } else if (current == 'C') {
                current = 'B';
                c++;
                pairs += a;
                pairs += b;
            }
            seq_length++;

        }

        if (pairs < K) {
            seq.append("");
        } else {
            for (int i = 0; i < a + b + c; i++) {
                if (i < a) {
                    seq.append('A');
                } else if (i < b + a) {
                    seq.append('B');
                } else if (i < c + b + a) {
                    seq.append('C');
                }
            }
            if (pairs > K) {
                int r = pairs - K;
                int index = seq_length - c - r;
                char last_char = seq.charAt(seq_length - 1);
                seq = seq.insert(index, last_char);
                seq = seq.deleteCharAt(seq_length);
            }

            if (seq_length < N) {
                for (int i = 0; i < N - seq_length; i++) {
                    seq.append('A');
                }
            }
        }

        return seq.toString();

    }
}