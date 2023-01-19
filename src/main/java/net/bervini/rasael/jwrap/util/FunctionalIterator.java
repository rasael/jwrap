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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunctionalIterator<E> extends Iterator<E> {

  default FunctionalIterator<E> prepend(E... e) {
    return Iterators.prepend(this, e);
  }

  default FunctionalIterator<E> append(E... e) {
    return Iterators.append(this, e);
  }

  default FunctionalIterator<E> filter(Predicate<? super E> predicate) {
    return Iterators.filter(this, predicate);
  }

  default <R> FunctionalIterator<R> map(Function<? super E, ? extends R> filter) {
    return Iterators.map(this, filter);
  }

  default List<E> toList() {
    var list = new ArrayList<E>();
    forEachRemaining(list::add);
    return List.copyOf(list);
  }

  static <E> FunctionalIterator<E> of(Iterator<E> iterator) {
    return new Impl<>(iterator);
  }

  record Impl<E>(Iterator<E> back) implements FunctionalIterator<E> {

    @Override
    public boolean hasNext() {
      return back.hasNext();
    }

    @Override
    public E next() {
      return back.next();
    }
  }

}
