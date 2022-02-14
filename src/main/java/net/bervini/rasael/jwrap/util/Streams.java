/*
 * Copyright 2022-2022 Rasael Bervini
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bervini.rasael.jwrap.util;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@ParametersAreNullableByDefault
public class Streams {

  private Streams() {}

  // -------------------------------------------------------------------------------------------------------------------

  public static <T> Stream<T> stream(Collection<T> collection) {
    if (collection==null)
      return empty();

    return collection.stream();
  }

  public static <T> Stream<T> stream(Iterable<T> iterable) {
    if (iterable==null) {
      return empty();
    }

    if (iterable instanceof Collection<T> c) {
      return c.stream();
    }

    return StreamSupport.stream(iterable.spliterator(), false);
  }

  public static <T> Stream<T> stream(Iterator<T> iterator) {
    if (iterator==null) {
      return Stream.empty();
    }

    return StreamSupport.stream(java.util.Spliterators.spliteratorUnknownSize(iterator, 0), false);
  }

  public static <T> Stream<T> stream(T[] array) {
    if (array==null)
      return Stream.empty();

    return Stream.of(array);
  }

  public static <T> Stream<T> filter(Stream<T> stream, Predicate<? super T> predicate) {
    if (stream==null || predicate==null)
      return stream;

    return stream.filter(predicate);
  }

  public static <T, R> Stream<R> filterByType(Stream<T> stream, Class<R> type) {
    if (type==null)
      return empty();

    stream = Streams.filter(stream, type::isInstance);
    return map(stream, type::cast);
  }

  public static <T, R> Stream<R> map(Stream<T> stream, Function<? super T, ? extends R> mapper) {
    if (stream==null || mapper==null) {
      return Streams.empty();
    }

    return stream.map(mapper);
  }

  public static <R> Stream<R> empty() {
    return Stream.empty();
  }

  public static <T> Stream<T> sorted(Stream<T> stream, Comparator<? super T> comparator) {
    if (comparator==null)
      return sorted(stream);

    if (stream!=null)
      stream = stream.sorted(comparator);

    return stream;
  }

  public static <T> Stream<T> sorted(Stream<T> stream) {
    if (stream!=null)
      stream = stream.sorted();

    return stream;
  }

  public static <T, U extends Comparable<? super U>> Stream<T> sortedBy(Stream<T> stream,
                                                                        Function<? super T,? extends U> keyExtractor) {
    if (keyExtractor==null)
      return sorted(stream);

    if (stream!=null)
      stream = stream.sorted(Comparator.comparing(keyExtractor));

    return stream;
  }

  public static <T> Stream<T> sortReversed(Stream<T> stream, Comparator<? super T> comparator) {
    if (comparator==null)
      return sortReversed(stream);

    if (stream!=null)
      stream = stream.sorted(comparator.reversed());

    return stream;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <T> Stream<T> sortReversed(Stream<T> stream) {
    if (stream!=null)
      stream = stream.sorted((Comparator)Comparator.reverseOrder());

    return stream;
  }

  public static <T, U extends Comparable<? super U>> Stream<T> sortReversedBy(Stream<T> stream,
                                                                        Function<? super T,? extends U> keyExtractor) {
    if (keyExtractor==null)
      return sorted(stream);

    if (stream!=null)
      stream = stream.sorted(Comparator.comparing(keyExtractor).reversed());

    return stream;
  }

  public static <T> Stream<T> generate(Supplier<T> supplier) {
    if (supplier==null)
      return Streams.empty();

    return Stream.generate(supplier);
  }

  public static <T> Stream<T> generate(Supplier<T> supplier, int size) {
    return StreamSupport.stream(Spliterators.generate(supplier, size), false);
  }
}
