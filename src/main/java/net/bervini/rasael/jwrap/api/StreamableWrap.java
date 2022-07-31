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

package net.bervini.rasael.jwrap.api;

import net.bervini.rasael.jwrap.annotation.Beta;

import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

public interface StreamableWrap<ELEMENT> {

  /**
   * Returns a sequential ordered stream whose elements are the specified values<br>
   *
   * The returned stream <strong>is not</strong> wrapped.
   */
  Stream<ELEMENT> pureStream();

  /**
   * Returns a sequential ordered stream whose elements are the specified values<br>
   *
   * The returned stream <strong>is</strong> wrapped
   */
  default StreamWrap<ELEMENT> stream() {
    return Wrap(pureStream());
  }

  /**
   * Returns a new List out of the current streamable wrap
   */
  default List<ELEMENT> toList() {
    return pureStream().toList();
  }

  default ListWrap<ELEMENT> toListWrap() {
    return Wrap(pureStream().collect(Collectors.toList()));
  }

  /**
   * Returns a new Set out of the current streamable wrap
   */
  default Set<ELEMENT> toSet() {
    return pureStream().collect(Collectors.toSet());
  }

  /**
   *
   * Returns a new Array out of the current streamable wrap
   */
  default ObjectArrayWrap<ELEMENT> toArray(IntFunction<ELEMENT[]> generator) {
    return Wrap(pureArray(generator));
  }

  /**
   * Beta - the name still has to be decided
   */
  @Beta
  default ELEMENT[] pureArray(IntFunction<ELEMENT[]> generator) {
    return pureStream().toArray(generator);
  }

  /**
   * Count the number of elements that match the current streamable wrap
   */
  default long count(Predicate<? super ELEMENT> element) {
    return pureStream().filter(element).count();
  }
}
