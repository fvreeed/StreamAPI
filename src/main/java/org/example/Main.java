package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Natasha", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Ernest", Arrays.asList("Java", "Python", "Scala"));
        Developer dev3 = new Developer("Ella", Arrays.asList("C#", "Python", "JavaScript"));
        Developer dev4 = new Developer("Boris", Arrays.asList("Python", "JavaScript", "Scala"));
        Developer dev5 = new Developer("Alex", Arrays.asList("Python", "JavaScript", "Go"));
        Developer dev6 = new Developer("Teodor", Arrays.asList("Python", "JavaScript", "Pascal"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3, dev4, dev5, dev6);
        Stream<Developer> streamForLanguages = Stream.of(dev1, dev2, dev3, dev4, dev5, dev6);

        List<String> uniqueLanguages = uniqueValues(streamForLanguages);

        List<Developer> developers = developerStream.filter(developer -> developer.getLanguages()
                .stream()
                .anyMatch(uniqueLanguages::contains))
                .toList();
        developers.forEach(developer -> System.out.println(developer.getName()));

        //        List<Developer> developers = developerStream.flatMap(developer -> developer
//                .getLanguages()
//                .stream()
//                .anyMatch(new List[]{developerStream.flatMap(developer1 -> developer1
//                                .getLanguages()
//                                .stream())
//                        .distinct()
//                        .filter(language -> Collections.frequency(developerStream
//                                .flatMap(developer2 -> developer2
//                                        .getLanguages()
//                                        .stream()).toList(), language) > 1).toList()}::contains)).toList();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Developer {
        private String name;
        private List<String> languages;

        @Override
        public String toString() {
            return "Developer{" +
                    "name='" + name + '\'' +
                    ", languages=" + languages +
                    '}';
        }
    }

    private static List<String> uniqueValues(Stream<Developer> stream) {
        List<String> preUniqueLanguages = stream
                .flatMap(developer -> developer
                        .getLanguages()
                        .stream())
                .toList();
        List<String> uniqueLanguages = preUniqueLanguages.stream()
                .distinct()
                .collect(Collectors.toList());
        uniqueLanguages.removeIf(language -> Collections.frequency(preUniqueLanguages, language) > 1);
        return uniqueLanguages;
    }
}