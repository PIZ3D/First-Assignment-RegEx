import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercises {

    /*
        complete the method below, so it will validate an email address
     */
    public boolean validateEmail(String email) {
        String regex = "^(?!.*\\.@)(?!.*@[-.])[a-zA-Z0-9][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /*
        this method should find a date in string
        note that it should be in british or american format
        if there's no match for a date, return null
     */
    public String findDate(String string) {
        String regex = "\\b(0?[1-9]|[12][0-9]|3[01])[-/](0?[1-9]|1[0-2])[-/](\\d{4})\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        
        return matcher.find() ? matcher.group() : null;
    }

    /*
        given a string, implement the method to detect all valid passwords
        then, it should return the count of them

        a valid password has the following properties:
        - at least 8 characters
        - has to include at least one uppercase letter, and at least a lowercase
        - at least one number and at least a special char "!@#$%^&*"
        - has no white-space in it
     */
    // public int findValidPasswords(String string) {
    //     String regex = "(?<!\\S)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}(?!\\S)";
    //     Pattern pattern = Pattern.compile(regex);
    //     Matcher matcher = pattern.matcher(string);
        
    //     int count = 0;
    //     while (matcher.find()) {
    //         count++;
    //     }
    //     return count;
    // }

    public int findValidPasswords(String string) {
        // Split the string into individual words (space-separated)
        String[] words = string.split("\\s+");
        
        // String regex = "(?<!\\S)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}(?=\\s|[.,!?)}\"']|$)";
        String regex = "(?<!\\s)(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*_.])(?=.*[A-Z])[A-Za-z0-9_!@#$%^&*]{8,}";
        Pattern pattern = Pattern.compile(regex);
        
        int count = 0;
        
        // Iterate over each word and check if it matches the pattern
        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                count++;
            }
        }
        
        return count;
    }
    

    /*
        you should return a list of *words* which are palindromic
        by word we mean at least 3 letters with no whitespace in it

        note: your implementation should be case-insensitive, e.g. Aba -> is palindrome
     */
    public List<String> findPalindromes(String string) {
        List<String> list = new ArrayList<>();
        String regex = "\\b[a-zA-Z]{3,}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        
        while (matcher.find()) {
            String word = matcher.group();
            String lowerWord = word.toLowerCase();   //tabdilesh mikonim be lowercase ke motmaen bashim
            if (isPalindrome(lowerWord)) {
                list.add(word);
            }
        }
        return list;
    }

    private boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equals(word);
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        // Testing email validation
        System.out.println(exercises.validateEmail("test@example.com")); // true
        System.out.println(exercises.validateEmail("john.doe@example.com"));
        System.out.println(exercises.validateEmail("hello world@example.com"));
        System.out.println(exercises.validateEmail("alice_bob123@research-lab.co.uk"));
        System.out.println(exercises.validateEmail("user@ex_ample.com"));
        System.out.println(exercises.validateEmail("user.@-example.com"));
        System.out.println(exercises.validateEmail("parsa-ziadloo@com")); // false

        // Testing date finder
        System.out.println(exercises.findDate("Today is 10-05-2005")); // behemoon 10-05-2005 ro mide
        System.out.println(exercises.findDate("behet tarikh nemidam")); // null

        // Testing valid password count
        System.out.println(exercises.findValidPasswords("StrongPass1! weakpass anotherStrong1@")); // 2
        System.out.println(exercises.findValidPasswords("P@ssw0rd is my secret")); 
        System.out.println(exercises.findValidPasswords("Alice's new password is SecureP@ss1 but Bob still uses weakpass123.\\s\r\n" + //
                        "                Charlie upgraded to Ultra_Strong99! while David set his to Admin123 (which is not secure).\\s\r\n" + //
                        "                Their boss? He went with SuperSafe#42 and banned \"password123"));
        System.out.println(exercises.findValidPasswords("[09:15] Dev1: Just changed my password to CodeMaster@2025. \\s\r\n" + //
                        "                [09:17] Dev2: Haha, mine's still qwerty123, no special chars. \\s\r\n" + //
                        "                [09:19] Dev3: I use GitHubSuper#1 but need a better one. \\s\r\n" + //
                        "                [09:21] Dev4: AdminPass42! is good, right? \\s\r\n" + //
                        "                [09:23] Dev5: No, too simple. I switched to UltraSecure$99 last week. \\s\r\n" + //
                        "                [09:25] Dev6: Wait, are we sharing passwords here? \\uD83D\\uDE02 \\s"));
        System.out.println(exercises.findValidPasswords("NoSpecial1 WeakPass! NoDigits@"));

        // Testing palindrome finder
        System.out.println(exercises.findPalindromes("Madam Arora teaches civic duties and ehsan teaches java")); // [Madam, Arora, civic]
        System.out.println(exercises.findPalindromes("madam racecar level")); 
        System.out.println(exercises.findPalindromes("hello world")); 
        System.out.println(exercises.findPalindromes("Step on no pets")); 
        System.out.println(exercises.findPalindromes("Madam, did you see Bob running? I asked Kayak and radar to wait at the civic center.\\s\r\n" + //
                        "                The level of security was high, but I noticed a racecar driving past. A deed was done in the noon,\\s\r\n" + //
                        "                and many said it was a referable situation.")); 
    }
}
