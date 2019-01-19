package com.asidorov.anagram.service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnagramService {
    public static boolean isAnagram(String lhs, String rhs) {
        //In case any of params is null - return false.
        if (Objects.isNull(lhs) || Objects.isNull(rhs)) {
            return false;
        }
        //Prepare both string for comparison: remove all spaces and transform both strings to one case(lower)
        String cleanedFirst = lhs.replaceAll(" ", "").toLowerCase();
        String cleanedSecond = rhs.replaceAll(" ", "").toLowerCase();

        //Check that there is at least one character in each string
        if (cleanedFirst.isEmpty() || cleanedSecond.isEmpty()) {
            return false;
        }

        //First check for anagram - length of both strings should be the same size.
        if (cleanedFirst.length() != cleanedSecond.length()) {
            return false;
        }

        //Check that at least one letter from lhs stands on other position in rhs
        boolean textIsEqual = cleanedFirst.equalsIgnoreCase(cleanedSecond);
        if (textIsEqual) {
            return false;
        }


        //Convert first argument to list of characters and count number of occurrences for each char
        Map<Character, Long> charactersWithCountFromFirst = cleanedFirst.chars()
                .mapToObj(c -> (char) c).collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Convert second argument to list of characters and count number of occurrences for each char
        Map<Character, Long> charactersWithCountFromSecond = cleanedSecond.chars()
                .mapToObj(c -> (char) c).collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Check that each element from first argument is present in second argument.
        for (Map.Entry<Character, Long> entry : charactersWithCountFromFirst.entrySet()) {
            //Check that character from lhs is present in rhs
            if (charactersWithCountFromSecond.containsKey(entry.getKey())) {
                Long valueForKey = charactersWithCountFromSecond.get(entry.getKey());
                //Check that character occurs exact same amount of times
                if (!entry.getValue().equals(valueForKey)) {
                    //Character from lhs is present in rhs but occurs less or more times. This is not anagram
                    return false;
                }
            } else {
                //Character from lhs is absent in rhs
                return false;
            }
        }

        //Check that number of unique characters is the same in both elements.
        //Equality for all characters is checked above
        return charactersWithCountFromFirst.keySet().size() == charactersWithCountFromSecond.keySet().size();

    }
}
