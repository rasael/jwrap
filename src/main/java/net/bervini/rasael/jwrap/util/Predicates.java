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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.function.Predicate;

@ParametersAreNullableByDefault
public class Predicates {

  private Predicates(){}

  // -------------------------------------------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static <T> Predicate<T> not(Predicate<? super T> predicate) {
    return predicate!=null ? (Predicate<T>) predicate.negate() : null;
  }

  @SafeVarargs
  public static <T> boolean accepts(Predicate<T> predicate, T... values) {
    if (predicate==null)
      return false;

    if (values==null)
      return false; // never accept null arrays

    if (values.length == 1)
      return predicate.test(values[0]);

    return acceptsAll(predicate, Arrays.asList(values));
  }

  public static <T> boolean acceptsAll(Predicate<T> predicate, Iterable<T> iterable) {
    return Iterables.allMatch(iterable, predicate);
  }

  @SafeVarargs
  public static <T> boolean rejectsAny(Predicate<T> predicate, T... values) {
    if (predicate==null)
      return true;

    if (Arrays.isEmpty(values))
      return true; // always reject empty arrays

    if (values.length == 1){
      return !predicate.test(values[0]);
    }

    return rejectsAny(predicate, Arrays.asList(values));
  }

  @SafeVarargs
  public static <T> boolean rejects(Predicate<T> predicate, T... values) {
    if (predicate==null)
      return true;

    if (values==null)
      return true; // always reject null arrays

    if (values.length == 1){
      return !predicate.test(values[0]);
    }

    return rejectsAll(predicate, Arrays.asList(values));
  }

  public static <T> boolean rejectsAll(@Nullable Predicate<T> predicate, Iterable<T> iterable) {
    return Iterables.noneMatch(iterable, predicate);
  }

  public static <T> boolean rejectsAny(@Nullable Predicate<T> predicate, Iterable<T> iterable) {
    return Iterables.anyMatch(iterable, not(predicate));
  }

  public static <T> Predicate<T> acceptAll() {
    return t -> true;
  }

  public static <T> Predicate<T> rejectAll() {
    return t -> false;
  }
}
