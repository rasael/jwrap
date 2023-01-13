/*
 * Copyright 2022-2023 Rasael Bervini
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

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Iterators {

  private Iterators() {
  }

  // -------------------------------------------------------------------------------------------------------------------

  public static <T> Iterator<T> empty() {
    return java.util.Collections.emptyIterator();
  }

  public static <ELEMENT> Iterator<Map.Entry<Integer, ELEMENT>> indexed(Iterator<ELEMENT> iterator) {
    if (iterator==null)
      return empty();

    return synchronizedIterator(new IndexedIterator<>(iterator));
  }

  public static <E> Iterator<E> synchronizedIterator(Iterator<E> iterator) {
    return new SynchronizedIterator<>(iterator);
  }

  @SafeVarargs
  public static <T> Iterator<T> of(T... values) {
    if (values==null)
      return empty();

    return Stream.of(values).iterator();
  }

  public static <E> FunctionalIterator<E> append(Iterator<E> iterator, E... element) {
    if (iterator==null) return null;
    Objects.requireNonNull(element, "element is null");
    return new IteratorChain<>(iterator, List.of(element));
  }

  public static <E> FunctionalIterator<E> append(Iterator<E> iterator, Iterable<E> elements) {
    if (iterator==null) return null;
    Objects.requireNonNull(elements, "elements is null");
    return new IteratorChain<>(iterator, elements);
  }

  public static <E> FunctionalIterator<E> prepend(Iterator<E> iterator, E... element) {
    if (iterator==null) return null;
    Objects.requireNonNull(element, "element is null");
    return new IteratorChain<>(of(element), iterator);
  }

  public static <E> FunctionalIterator<E> prepend(Iterator<E> iterator, Iterable<E> elements) {
    if (iterator==null) return null;
    Objects.requireNonNull(elements, "elements is null");
    return new IteratorChain<>(elements.iterator(), iterator);
  }

  public static <E, R> FunctionalIterator<R> map(Iterator<E> iterator, Function<? super E, ? extends R> function) {
    return new FunctionIterator<>(iterator, function);
  }

  public static <E> FunctionalIterator<E> filter(Iterator<E> iterator, Predicate<? super E> predicate) {
    return new FilterIterator<>(iterator, predicate);
  }

  public static <T> Iterator<T> singleton(T value) {
    return Stream.of(value).iterator();
  }

  // -------------------------------------------------------------------------------------------------------------------

  private static class IndexedIterator<E>
      implements FunctionalIterator<Map.Entry<Integer, E>> {

    private final Iterator<E> iterator;
    private int index;

    private IndexedIterator(Iterator<E> iterator) {
      this.iterator = Preconditions.requireArgNonNull(iterator);
      this.index = 0;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Map.Entry<Integer, E> next() {
      return Map.entry(index++, iterator.next());
    }
  }

  private record SynchronizedIterator<E>(Iterator<E> iterator)
      implements FunctionalIterator<E> {

    private SynchronizedIterator(Iterator<E> iterator) {
      this.iterator = Preconditions.requireArgNonNull(iterator);
    }

    @Override
    public synchronized boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public synchronized E next() {
      return iterator.next();
    }
  }

  private record IteratorChain<E>(Iterator<E> before, Iterator<E> after)
      implements FunctionalIterator<E> {

    IteratorChain(Iterator<E> before, Iterable<E> after) {
      this(before, after.iterator());
    }

    @Override
    public boolean hasNext() {
      return before.hasNext() || after.hasNext();
    }

    @Override
    public E next() {
      return before.hasNext() ? before.next() : after.next();
    }
  }

  private record FunctionIterator<E, R>(Iterator<E> iterator, Function<? super E, ? extends R> function)
      implements FunctionalIterator<R> {

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public R next() {
      return function.apply(iterator.next());
    }
  }

  private static final class FilterIterator<E>
      implements FunctionalIterator<E> {

    private final Iterator<E> iterator;
    private final Predicate<? super E> predicate;
    private boolean isNextReady;
    private boolean hasNext;
    private E next;

    private FilterIterator(@Nonnull Iterator<E> iterator, @Nonnull Predicate<? super E> predicate) {
      this.iterator = iterator;
      this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
      if (!isNextReady) readNext();
      return hasNext;
    }

    @Override
    public E next() {
      if (!isNextReady) readNext();

      if (!hasNext)
        throw new NoSuchElementException();

      isNextReady = false;
      return next;
    }

    private void readNext() {
      hasNext = false;
      next = null;
      while(iterator.hasNext()) {
        var next = iterator.next();
        if (predicate.test(next)) {
          this.hasNext = true;
          this.next = next;
          this.isNextReady = true;
          return;
        }
      }
    }
  }
}
