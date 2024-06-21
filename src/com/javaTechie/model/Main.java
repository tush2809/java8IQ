package com.javaTechie.model;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Student> studentList = Stream.of(
                        new Student(1, "Rohit", 30, "Male", "Mechanical Engineering", "Mumbai", 122, Arrays.asList("+912632632782", "+1673434729929")),
                        new Student(2, "Pulkit", 56, "Male", "Computer Engineering", "Delhi", 67, Arrays.asList("+912632632762", "+1673434723929")),
                        new Student(3, "Ankit", 25, "Female", "Mechanical Engineering", "Kerala", 164, Arrays.asList("+912632633882", "+1673434709929")),
                        new Student(4, "Satish Ray", 30, "Male", "Mechanical Engineering", "Kerala", 26, Arrays.asList("+9126325832782", "+1671434729929")),
                        new Student(5, "Roshan", 23, "Male", "Biotech Engineering", "Mumbai", 12, Arrays.asList("+012632632782")),
                        new Student(6, "Chetan", 24, "Male", "Mechanical Engineering", "Karnataka", 90, Arrays.asList("+9126254632782", "+16736784729929")),
                        new Student(7, "Arun", 26, "Male", "Electronics Engineering", "Karnataka", 324, Arrays.asList("+912632632782", "+1671234729929")),
                        new Student(8, "Nam", 31, "Male", "Computer Engineering", "Karnataka", 433, Arrays.asList("+9126326355782", "+1673434729929")),
                        new Student(9, "Sonu", 27, "Female", "Computer Engineering", "Karnataka", 7, Arrays.asList("+9126398932782", "+16563434729929", "+5673434729929")),
                        new Student(10, "Shubham", 26, "Male", "Instrumentation Engineering", "Mumbai", 98, Arrays.asList("+912632646482", "+16734323229929")))
                .collect(Collectors.toList());

        // 1. Find the list of students whose rank is in between 50 and 100
        studentList.stream().filter(s->s.getRank()>=50 && s.getRank()<=100).collect(Collectors.toList());
                //.forEach(System.out::println);

        //2. Find the Students who stays in Karnataka and sort them by their names
        studentList.stream().filter(s->s.getCity().
                equalsIgnoreCase("Karnataka")).sorted(Comparator.comparing(Student::getFirstName)).
                   collect(Collectors.toList());//.forEach(System.out::println);

        // 3. Find all departments names
        studentList.stream().map(Student::getDept).distinct().collect(Collectors.toList());//.forEach(System.out::println);

        //4.  Find all the contact numbers
        studentList.stream().flatMap(s->s.getContacts().stream()).
                  collect(Collectors.toList());//forEach(System.out::println);

        //5.  Group The Student By Department Names
        studentList.stream().collect(Collectors.groupingBy(Student::getDept)).entrySet().
                      stream().collect(Collectors.toList());//.forEach(System.out::println);

        //6. Find the department who is having maximum number of students
        Long maxNumber = studentList.stream().collect(Collectors.groupingBy(Student::getDept,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        //System.out.println(maxNumber);

        //7. Find the average age of male and female students
        studentList.stream().collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge))).
                entrySet().stream().collect(Collectors.toList());//.forEach(System.out::println);
        //8. Find the highest rank in each department
     Map<String,Optional<Student>> st= studentList.stream().collect(Collectors.groupingBy
                 (Student::getDept,Collectors.minBy(Comparator.comparing(Student::getRank))));
        //9 .Find the student who has second rank
        String firstName = studentList.stream().sorted(Comparator.comparing(Student::getRank)).skip(2).findFirst().get().getFirstName();
        //System.out.println(firstName);
        //10. sorted the Student by rank
        studentList.stream().sorted(Comparator.comparing(Student::getRank)).map(Student::getRank).
                collect(Collectors.toList());//.forEach(System.out::println);

        String inpput = "i love india";

         ArrayList output = (ArrayList) Arrays.stream(inpput.split("")).collect(Collectors.
                 groupingBy(Function.identity(),Collectors.counting())).entrySet().
                 stream().filter(s->!s.getKey().equalsIgnoreCase(" ")).collect(Collectors.toList());
        System.out.println(output);


        //find duplicate elements given string
        Arrays.stream(inpput.split("")).collect(Collectors.groupingBy
                (Function.identity(),Collectors.counting())).entrySet().stream().
                   filter(s->s.getValue()>1).collect(Collectors.toList());//.forEach(System.out::println);

        //first Non repeat element
         String outputs = Arrays.stream(inpput.split("")).
                collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                  .entrySet().stream().filter(s->s.getValue()==1).findFirst().get().getKey();

        System.out.println(outputs);

        int[] num = {5,9,11,2,8,21,1};

       Integer in = Arrays.stream(num).boxed().sorted(Comparator.reverseOrder()).
                      collect(Collectors.toList()).stream().skip(1).findFirst().get();
       System.out.println(in);

       String[] strArray =  {"java", "techie","springboot","microservices"};

       Arrays.stream(strArray).reduce((w1,w2) -> w1.length()>w2.length()?w1:w2).get();












    }
}