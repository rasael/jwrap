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

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

@ParametersAreNullableByDefault
public class Iterables {

  private static final Iterable<?> NULL_SINGLETON_ITERABLE = Collections.singleton(null);

  private static final Iterable<?> EMPTY = new EmptyIterable();

  private Iterables(){}

  // -------------------------------------------------------------------------------------------------------------------

  public static <T> Iterable<T> empty() {
    return (Iterable<T>) EMPTY;
  }

  @SuppressWarnings("unchecked")
  public static <T> Iterable<T> singleton(T element) {
    if (element==null) {
      return (Iterable<T>) NULL_SINGLETON_ITERABLE;
    }
    return Collections.singleton(element);
  }

  public static <T> boolean noneMatch(Iterable<T> iterable, Predicate<? super T> predicate) {
    if (predicate==null)
      return true; // none match a null predicate

    return Streams.stream(iterable).noneMatch(predicate);
  }

  public static <T> boolean anyMatch(Iterable<T> iterable, Predicate<? super T> predicate) {
    if (predicate==null)
      return false; // none match a null predicate

    return Streams.stream(iterable).anyMatch(predicate);
  }

  public static <T> boolean allMatch(Iterable<T> iterable, Predicate<? super T> predicate) {
    if (predicate==null)
      return false; // none match a null predicate

    return Streams.stream(iterable).allMatch(predicate);
  }

  @Nullable
  public static <T> T getFirst(Iterable<T> iterable) {
    if (iterable==null)
      return null;

    if (iterable instanceof List<T> list) {
      return Lists.getFirst(list);
    }

    var iterator = iterable.iterator();
    return iterator.hasNext() ? iterator.next() : null;
  }

  @Nullable
  public static <T> T getLast(Iterable<T> iterable) {
    if (iterable==null)
      return null;

    if (iterable instanceof List<T> list) {
      return Lists.getLast(list);
    }

    var iterator = iterable.iterator();

    T last = null;
    while(iterator.hasNext()) {
      last = iterator.next();
    }

    return last;
  }

  public static <T> Iterator<T> iterator(Iterable<T> iterable) {
    if (iterable==null)
      return Iterators.empty();

    return iterable.iterator();
  }

  public static boolean isNullOrEmpty(Iterable<?> iterable) {
    return iterable==null || isEmpty(iterable);
  }

  public static <T> int size(Iterable<T> iterable) {
    if (iterable==null)
      return 0;

    if (iterable instanceof Collection<?> c) {
      return c.size();
    }

    Spliterator<?> spliterator = iterable.spliterator();
    long size = spliterator.getExactSizeIfKnown();
    if (size>=0)
      return Math.toIntExact(size);

    // count each element
    int counter = 0;
    for (Object ignore : iterable) {
      counter++;
    }

    return counter;
  }

  public static <T> boolean isEmpty(Iterable<T> iterable) {
    if (iterable==null)
      return true;

    if (iterable instanceof Collection<?> c)
      return c.isEmpty();

    return !iterable.iterator().hasNext();
  }

  /**
   * <p>Returns true if all elements of the sequence are contained in the given iterable</p>
   * <p>Returns false if the {@code iterable} is null</p>
   * <p>Returns true if the {@code sequence} is null or empty</p>
   * @param iterable the iterable to search into
   * @param sequence the sequence of values to search for in the iterable
   * @param <E> the type of elements contained in the iterable
   * @return true if all elements of the sequence are present in the given iterable
   */
  public static <E> boolean contains(Iterable<E> iterable, Iterable<E> sequence) {
    if (iterable==null)
      return false;

    return Streams.stream(sequence).allMatch(value -> contains(iterable, value));
  }

  public static boolean contains(Iterable<?> iterable, Object value) {
    if (iterable==null)
      return false;

    return Streams.stream(iterable).anyMatch(Comparison.isEqualTo(value));
  }

  public static <T> T[] toArray(Iterable<T> iterable, IntFunction<T[]> generator) {
    return Lists.newList(iterable).toArray(generator);
  }

  /**
   * <p>Returns a new iterable that mimics the given iterable iterator and spliterator.</p>
   *
   * <p>This allows to proxy an Iterable interface and retain the characteristics of its Spliterator.</p>
   *
   * <p>If the {@code iterable} is null, this method returns {@link #empty()}</p>
   * @param iterable the iterable to mimic, can be null
   * @param <T> the type of elements contained by the iterable
   * @return a new iterable that mimics the given iterable
   */
  public static <T> Iterable<T> like(Iterable<T> iterable) {
    if (iterable==null)
      return empty();

    return new Iterable<T>() {
      @NotNull
      @Override
      public Iterator<T> iterator() {
        return iterable.iterator();
      }

      @Override
      public Spliterator<T> spliterator() {
        return iterable.spliterator();
      }
    };
  }

  public static <T> Iterable<T> orEmpty(Iterable<T> iterable) {
    if (iterable==null)
      return empty();

    return iterable;
  }

  // -------------------------------------------------------------------------------------------------------------------

  private static class EmptyIterable<T> implements Iterable<T> {

    @NotNull
    @Override
    public Iterator<T> iterator() {
      return Iterators.empty();
    }

    @Override
    public Spliterator<T> spliterator() {
      return Spliterators.empty();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
      Objects.requireNonNull(action);
    }
  }
}
