package com.github.orest.car_shop.service;

import java.util.*;
import java.util.stream.Collectors;

public class ExercisesService {

    public static void main(String[] args) {
        arrayOfStrings();
    }

    public static void arrayOfStrings() {

        // 1. Принять массив стрингов, именно массив, сделать стрим из него, найти элемент который начинается пусть на букву z и вернуть именно этот элемент.
        String[] stringArray = new String[] {"some", "random", "words", "zoo"};
        Arrays.asList(stringArray).stream().filter(a -> a.startsWith("z")).collect(Collectors.toList());

        // 2. Принять лист стрингов, сделать из него мапу в которой ключ стринг, а значение его длина
        ArrayList<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("tralala");
        listOfStrings.add("blablabla");
        listOfStrings.add("hello");

        // 2.1
        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();
        listOfStrings.forEach(a -> myHashMap.put(a, a.length()));

        // 2.2
        HashMap<String, Integer> otherHashMap = (HashMap<String, Integer>) listOfStrings.stream().map(a -> new HashMap<String, Integer>().put(a, a.length())).collect(Collectors.toList());
    }
}
