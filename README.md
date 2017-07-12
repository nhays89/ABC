# ABC
Top Coder Problem Set<br>
Problem Statement<br>
You are given two s: N and K. Lun the dog is interested in strings that satisfy the following conditions:<br>

The string has exactly N characters, each of which is either 'A', 'B' or 'C'.<br>
The string s has exactly K pairs (i, j) (0 <= i < j <= N-1) such that s[i] < s[j].<br>
If there exists a string that satisfies the conditions, find and return any such string. Otherwise, return an empty string.<br>
## Definition
Class: ABC<br>
Method: createString<br>
Parameters: int, int<br>
Returns: String<br>
Method signature: String createString(int N, int K)<br>
(be sure your method is public)<br>
Limits<br>
Time limit (s): 2.000<br>
Memory limit (MB): 256<br>
Constraints<br>
- N will be between 3 and 30, inclusive.<br>
- K will be between 0 and N(N-1)/2, inclusive.<br>
## Examples
0)<br>
3<br>
3<br>
Returns: "ABC"<br>
This string has exactly three pairs (i, j) mentioned in the statement: (0, 1), (0, 2) and (1, 2).<br>
1)<br>
3<br>
0<br>
Returns: "CBA"<br>
Please note that there are valid test cases with K = 0.<br>
2)<br>
5<br>
10<br>
Returns: ""<br>
Five characters is too short for this value of K.<br>
3)<br>
15<br>
36<br>
Returns: "CABBACCBAABCBBB"<br>
Please note that this is an example of a solution; other valid solutions will also be accepted.<br>
## Solution
### Idea
Begin tallying the count of each character of the set {A,B,C} in a
sequence of characters by cycling through the set beginning with 'C' then
'B' then 'A' logging the length of the sequence, the number of pairs, and
the current character in the sequence at each step until either the
length of the sequence has reached N, or the number of pairs of the
sequence have reached or exceeded K, in which case the last character
added is removed and a C is inserted in the previous sequence at index r,
where r is the difference between K and the previous sequence pairs.

### Reasoning

By creating the sequence in this manner, at each step we are guaranteed
that all A's will be at indices less than all B's, and all B's will be at
indices less than all C's, and the maximum possible K can be reached at
any N. Thus, when we add a new character to the sequence, whether it be
A, B, or C, we know that it will create a match with every other
character excluding those characters that are equal to the character
itself while also generating the most possible matches for the current
sequence length.<br>

For instance, when adding a B, we know that each A will be at an index
less than the new B, and each C will be at an index greater than the new
B; therefore, the B will match with each A and each C in the sequence..<br>

Likewise, when adding an A, we know that each B and C will be at an index
greater than the new A; thus, the new A will match with each B and C in
the sequence..<br>

Finally, when adding a C, we know that each A and B will be at an index
less than the new C. Hence, the new C will match with each A and B in the
sequence..<br>

Thus, for each new character added to the sequence we can determine the
number of new pairs created by accumulating the total of all distinct
characters. Together with the previous sequence total, we can track the
total number of pairs possible for each new sequence. We continue this
cycle until the sequence has reached exactly K pairs. Once it has, we can
append As to the sequence until its length is N; however, if the number
of pairs exceeds K then we must rearrange the characters in the current
sequence in order to reduce the number of total pairs to K, or,
alternatively, remove the last character added and return to the previous
sequence and insert a character, C, such that it increases the number of
pairs, r, by the difference between K and the previous sequence total.
For simplicity sake, we will choose the latter. Inserting a 'C' at index
r will accomplish this task because each character at index less than r
will be less than C. Thus, the previous sequence total plus r will equal
K pairs..<br>
