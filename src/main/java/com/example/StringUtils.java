package com.example;

public class StringUtils {
    public static boolean isPalindrome(String input){
        if(input==null)return  false;
        String normalized = input.toLowerCase();
        String reversed= new StringBuilder(normalized).reverse().toString();
        return normalized.equals(reversed);
    }

    public static int countVowels(String input){
        if(input==null|| input.isEmpty()){
            return 0;
        }
        final String VOWELS="AEIOUaeiou";
        int counter=0;

        for(char c:input.toCharArray()){
            if(VOWELS.indexOf(c)!=-1){
                counter++;
            }
        }
        return counter;
    }
}
