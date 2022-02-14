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

public interface EnumerableWrap<ELEMENT, SELF extends EnumerableWrap<ELEMENT, SELF>>
    extends NonPrimitiveWrap, IterableWrap<ELEMENT> {

  default boolean isNullOrEmpty() {
    return isNull() || isEmpty();
  }

  default boolean isEmpty() {
    return hasSize(0);
  }

  default boolean isNotEmpty() {
    return !isEmpty();
  }

  default boolean isSingleton() {
    return hasSize(1);
  }

  int size();

  default boolean hasSize(int size) {
    return size()==size;
  }

}
