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

import net.bervini.rasael.jwrap.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;

@ParametersAreNullableByDefault
public interface MutableIndexedObjectEnumerableWrap<
    ELEMENT,
    SELF extends MutableIndexedObjectEnumerableWrap<ELEMENT, SELF>>
    extends IndexedObjectEnumerableWrap<ELEMENT, SELF>,
    MutableEnumerableWrap<ELEMENT, SELF> {

  @Nonnull
  SELF set(int index, ELEMENT element);

  @Nullable
  ELEMENT replace(int index, ELEMENT element);

  default SELF push(ELEMENT... elements) {
    return pushAll(Arrays.asList(elements));
  }

  SELF pushAll(Iterable<ELEMENT> elements);

  /**
   * Removes and returns the last element of this enumerable
   */
  ELEMENT pop();

  /**
   * Removes and returns the first element of this enumerable
   */
  ELEMENT shift();

  /**
   * Adds the given element at the beginning of this enumerable
   */
  SELF unshift(ELEMENT element);

  SELF remove(int index);

  @Override
  default SELF remove(ELEMENT element) {
    return remove(indexOf(element));
  }
}
