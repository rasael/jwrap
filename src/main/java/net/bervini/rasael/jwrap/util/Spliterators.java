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

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class Spliterators {

  private Spliterators(){}

  // -------------------------------------------------------------------------------------------------------------------

  public static <T> Spliterator<T> empty() {
    return java.util.Spliterators.emptySpliterator();
  }

  public static <T> Spliterator<T> orderedArray(T[] value) {
    if (value==null ||value.length==0)
      return empty();

    return java.util.Spliterators.spliterator(value, Spliterator.ORDERED);
  }

  public static <T> Spliterator<T> generate(Supplier<T> supplier, int size) {
    if (supplier==null || size<1)
      return empty();

    return new FiniteSupplyingSpliterator<>(supplier, size);
  }

  private static class FiniteSupplyingSpliterator<T> implements Spliterator<T> {

    private final Supplier<T> supplier;
    private final int size;

    private int counter;

    public FiniteSupplyingSpliterator(Supplier<T> supplier, int size) {
      this.supplier = Preconditions.requireArgNonNull(supplier);
      this.size = size;

    }
    @Override
    public long estimateSize() {
      return size;
    }

    @Override
    public int characteristics() {
      return IMMUTABLE | ORDERED | SIZED;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
      Objects.requireNonNull(action);

      if (counter++<size) {
        action.accept(supplier.get());
        return true;
      }
      return false;
    }

    @Override
    public Spliterator<T> trySplit() {
      return null; // TODO add support
    }

    @Override
    public Comparator<? super T> getComparator() {
      return null; // SORTED, but naturally
    }
  }
  private static int createCharacteristics(int...characteristics) {
    Preconditions.requireArgNonNull(characteristics);

    int c = 0;
    for (int characteristic : characteristics) {
      c |= characteristic;
    }
    return c;
  }
}
