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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public interface MutableEnumerableWrap<ELEMENT, SELF extends MutableEnumerableWrap<ELEMENT, SELF>>
    extends EnumerableWrap<ELEMENT, SELF> {

  SELF fill(@Nullable ELEMENT element);

  SELF removeIf(Predicate<? super ELEMENT> predicate);

  default SELF removeWhile(Predicate<? super ELEMENT> predicate) {
    AtomicBoolean stop = new AtomicBoolean(false);
    return removeIf(e -> {
      if (stop.get())
        return false;

      if (!predicate.test(e)) {
        stop.get();
        return false;
      }

      return true;
    });
  }

  SELF remove(ELEMENT element);
}
