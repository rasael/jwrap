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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;

@ParametersAreNullableByDefault
public interface IndexedObjectEnumerableWrap<ELEMENT, SELF extends IndexedObjectEnumerableWrap<ELEMENT, SELF>>
  extends ObjectEnumerableWrap<ELEMENT, SELF> {

  /**
   * <p>The get() method takes an integer value and returns the item at that index.</p>
   *
   * <p>Unlike {@link #at(int)}, negative integers <strong>are not</strong> allowed.</p>
   * 
   * <p>Any index outside of the array bounds will return null</p>
   *
   * @implSpec This method returns null for any index outside of the array bounds
   * @return the element at that given position, or null otherwise
   * @see #at(int) 
   */
  @Nullable
  ELEMENT get(int index);

  /**
   * <p>The at() method takes an integer value and returns the item at that index, 
   * allowing for positive and negative integers.</p>
   * 
   * <p>Negative integers count back from the last item in the array.</p>
   * 
   * <pre>
   *   var array = new String[]{"Apple","Banana"};
   *
   *   console.log($(array).at(-1));
   *   // "b"
   * </pre>
   *
   * @implSpec This method returns <strong>null</strong> for any index outside of the array bounds
   * 
   * @param index index of the element to return, negative indexes start from the end of this enumerable
   * @return the element at that given position, or null otherwise
   * @see #get(int) 
   */
  @Nullable
  ELEMENT at(int index);

  int indexOf(ELEMENT element);

  boolean contains(ELEMENT element, int index);

  boolean doesNotContain(ELEMENT element, int index);
}
