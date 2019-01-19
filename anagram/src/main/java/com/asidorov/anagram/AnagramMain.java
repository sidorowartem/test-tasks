package com.asidorov.anagram;

import static com.asidorov.anagram.service.AnagramService.isAnagram;

public class AnagramMain {

    public static void main(String[] args) {
        //expected result = false, words are equal
        boolean firstCheck = isAnagram("test", "test");
        //expected result = false, this is not a classic anagram
        boolean secondCheck = isAnagram("test", "tes t");
        //expected result = false, this is not a classic anagram
        boolean thirdCheck = isAnagram("Test", "tes t");
        //expected result = true, from example
        boolean fourthCheck = isAnagram("anagram", "nagaram");
        //expected result = true
        boolean fifthCheck = isAnagram("silent", "Listen");
        //expected result = true
        boolean sixthCheck = isAnagram("William Shakespeare", "I am a weakish speller");

        System.out.println("Result = " + firstCheck + " " + secondCheck + " " + thirdCheck + " " + fourthCheck + " " + fifthCheck + " " + sixthCheck);
        System.out.println("Expected = false false false true true true");
    }
}
