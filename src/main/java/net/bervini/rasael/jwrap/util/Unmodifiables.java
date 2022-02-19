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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;

public class Unmodifiables {

  private Unmodifiables(){}

  // -------------------------------------------------------------------------------------------------------------------

  private static final Class<?> UNMODIFIABLE_COLLECTION;
  private static final Class<?> UNMODIFIABLE_LIST;
  private static final Class<?> UNMODIFIABLE_SET;

  static {
    UNMODIFIABLE_COLLECTION = java.util.Collections.unmodifiableCollection(new ArrayList<>(0)).getClass();
    UNMODIFIABLE_LIST = java.util.Collections.unmodifiableList(new ArrayList<>(0)).getClass();
    UNMODIFIABLE_SET = java.util.Collections.unmodifiableSet(new HashSet<>()).getClass();
  }


  /**
   * Returns true when the given collection is the direct result of {@link Collections#unmodifiableCollection(Collection)} or
   * {@link Collections#unmodifiableSet(Set)}, {@link Collections#unmodifiableSortedSet(SortedSet)},
   * {@link Collections#unmodifiableNavigableSet(NavigableSet)} or {@link Collections#unmodifiableList(List)}
   * @param c a collection to test
   * @return true if the given collection is an instance of {@code java.util.Collections.UnmodifiableCollection}
   */
  public static boolean isUnmodifiableCollection(Collection<?> c) {
    return UNMODIFIABLE_COLLECTION.isInstance(c);
  }

  /**
   * Returns true when the given List is the result of {@link Collections#unmodifiableList(List)}
   */
  public static boolean isUnmodifiableList(List<?> list) {
    return UNMODIFIABLE_LIST.isInstance(list);
  }

  /**
   * Returns true when the given List is the result of {@link Collections#unmodifiableSet(Set)}
   */
  public static boolean isUnmodifiableSet(Set<?> set) {
    return UNMODIFIABLE_SET.isInstance(set);
  }
}
