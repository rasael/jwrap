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
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;

@ParametersAreNullableByDefault
public class Collections {

  private Collections() {
  }

  // -------------------------------------------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static <T> List<T> emptyList() {
    return Lists.empty();
  }

  public static <T> Iterable<T> singleton(T element) {
    return java.util.Collections.singleton(element);
  }

  @SuppressWarnings("unchecked")
  public static <T> Set<T> emptySet() {
    return java.util.Collections.EMPTY_SET;
  }

  public static <T> List<T> newList() {
    return Lists.newList();
  }

  public static <T> Collection<T> toCollection(Iterable<T> iterable) {
    if (iterable instanceof Collection<T> c) {
      return c;
    }
    else if (iterable==null) {
      return emptyList();
    }
    else {
      return Lists.newList(iterable);
    }
  }

  public static int size(Collection<?> value) {
    if (value==null)
      return 0;

    return value.size();
  }

  public static boolean isEmpty(Collection<?> value) {
    if (value==null)
      return true;

    return value.isEmpty();
  }

  public static <E> E[] toArray(Collection<E> value, IntFunction<E[]> generator) {
    if (value==null)
      return null;

    return value.toArray(generator);
  }

  public static <T> List<T> unmodifiableList(List<? extends T> list) {
    if (list==null)
      return emptyList();

    return java.util.Collections.unmodifiableList(list);
  }
}
