package com.zahid.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamDemo {

    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10, Genre.ACTION),
                new Movie("b", 15, Genre.THRILLER),
                new Movie("c", 25, Genre.ROMANTIC),
                new Movie("d", 10, Genre.ACTION)
        );

        var count = movies.stream().filter(movie -> movie.getLikes() > 10).count();
        System.out.println("Movies greater than 10 likes: " + count);

        //Creating a stream...
        // From collections,
        // From arrays, 
        // From an arbitrary number of objects
        // Infinite / finite streams
        var stream = Stream.generate(() -> Math.random());
        stream.limit(3).forEach(n -> System.out.println(n));

        Stream.iterate(1, n -> n + 1).limit(10).forEach(n -> System.out.println(n));

        movies.stream().map(movie -> movie.getTitle()).forEach(name -> System.out.println(name));

        var stream2 = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));

        stream2.flatMap(list -> list.stream()).forEach(n -> System.out.println(n));

        //Intermediate operations they return streams
        //.map
        //.filter
        //Terminal operations - they dont return anything
        //ForEach -- they consumes streams...

        //Slicing a stream
        //limit(n), skip(n) i.e. used for paginations
        //dropWhile, takeWhile

        //1000 movies, 10 movies per page, 3rd page ---> skip(20)
        //skip( (page-1) * pageSize)
        //limit(pageSize)

        // movies.stream().skip(2).limit(10).forEach(m -> System.out.println(m.getTitle()));

        movies.stream().takeWhile(m -> m.getLikes() < 20).forEach(m -> System.out.println(m.getTitle()));

        movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle).reversed())
                .forEach(m -> System.out.println(m.getTitle()));

        movies.stream().map(Movie::getLikes)
                .distinct()
                .forEach(System.out::println);

        movies.stream().peek(movie -> System.out.println(movie.getTitle())).map(Movie::getLikes).forEach(System.out::println);

        //Reducers
        //count()
        //anyMatch(predicate)
        //allMatch(predicate)
        //noneMatch(predicate)
        //findFirst()
        //findAny()
        //max(comparator)
        //min(comparator)

        var result = movies.stream().findFirst().get();
        result = movies.stream().max(Comparator.comparing(Movie::getLikes)).get();

        //orElse
        var sum = movies.stream().map(Movie::getLikes).reduce(0, Integer::sum);
        System.out.println("Accumulative Sum: " + sum);


        var r = movies.stream().filter(m -> m.getLikes() > 10).collect(Collectors.toMap(m -> m.getTitle(), m -> m.getLikes()));
        //Collectors.summarizingInt, long and double

        var moviesString = movies.stream().map(Movie::getTitle).collect(Collectors.joining(","));
        System.out.println("Movies name in string: " + moviesString);


        //Grouping elements
        //Collectors.partitionby groupingby

        //Primitive Type Stream

        IntStream.rangeClosed(1, 5).forEach(System.out::println);


        //Streams
        //Mapping
        //Filtering
        //Slicing
        //Sorting
        //Reducing
        //Collectors - Group or Partitioning data

        var coll = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.mapping(Movie::getTitle, Collectors.joining(","))));


        var coll2 = movies.stream().collect(Collectors.partitioningBy(m -> m.getLikes() > 20, Collectors.mapping(Movie::getTitle, Collectors.joining(","))));
        System.out.println(coll2);

    }


}


