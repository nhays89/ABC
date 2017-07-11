import java.util.ArrayList;

/* Author Nicholas A. Hays */

class abc {

    public static void main(String[] args) {

        System.out.println(createString(4, 4));
        System.out.println(createString(5, 6));
        System.out.println(createString(5, 10));
        System.out.println(createString(15, 36));
        System.out.println(createString(3, 3));
        System.out.println(createString(3, 0));

    }

    public static String createString(int N, int K) {
        char current = 'C';
        ArrayList<Integer> pair_history = new ArrayList<Integer>();
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
            pair_history.add(pairs);
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
                if (pair_history.size() > 1) {
                    int prev_pairs = pair_history.get(pair_history.size() - 2);
                    int diff = K - prev_pairs;
                    char last_char = seq.charAt(seq_length - 1);
                    seq = seq.insert(diff, last_char);
                    seq = seq.deleteCharAt(seq_length);
                }

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