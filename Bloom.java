// my solution to https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
// this uses a bloom filter idea to solve the problem
// this implementation can be sped up by using bitwise operations

public class Bloom {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int[] answer = new int[puzzles.length];
        
        for (int puzzleIndex = 0; puzzleIndex < puzzles.length; puzzleIndex++) {
            int[] asciiOrdinals = new int[26]; // 97 - 122

            for (char ch : puzzles[puzzleIndex].toCharArray()) {
                asciiOrdinals[((int) ch) - 97] = 3; // 3 is just an identifier
            }

            char firstLetter = puzzles[puzzleIndex].toCharArray()[0];

            for (String wd : words) {
                if (wd.indexOf(firstLetter) != -1) {
                    boolean wordCharsInPuzzle = true;
                    for (char c : wd.toCharArray()) {
                        if (asciiOrdinals[(int) c - 97] != 3) { wordCharsInPuzzle = false; break; }
                    }
                    if (wordCharsInPuzzle) answer[puzzleIndex]++;
                }
            }
        }
        
        return IntStream.of(answer).boxed().collect(Collectors.toList());
    }
}