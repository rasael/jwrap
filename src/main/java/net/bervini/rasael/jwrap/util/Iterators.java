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

import java.util.Iterator;
import java.util.Map;

public class Iterators {

  private Iterators(){}

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

  private static class IndexedIterator<E> implements Iterator<Map.Entry<Integer,E>> {

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

  private record SynchronizedIterator<E>(Iterator<E> iterator) implements Iterator<E> {

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
}
