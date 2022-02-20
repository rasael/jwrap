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

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sets {

  private Sets(){}

  // -------------------------------------------------------------------------------------------------------------------

  public static <E> Set<E> newSet(Iterable<E> iterable) {
    if (iterable instanceof Collection<E> c)
      return new HashSet<>(c);

    Set<E> set = newSet();
    if (iterable!=null) {
      iterable.forEach(set::add);
    }
    return set;
  }

  public static <E> Set<E> newSet() {
    return new HashSet<>();
  }

  public static <E> Set<E> newOrderedSet(Iterable<E> iterable) {
    if (iterable instanceof Collection<E> c)
      return new LinkedHashSet<>(c);

    Set<E> set = newOrderedSet();
    if (iterable!=null) {
      iterable.forEach(set::add);
    }
    return set;
  }

  public static <E> Set<E> newOrderedSet() {
    return new LinkedHashSet<>();
  }
}
