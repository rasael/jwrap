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

import java.util.List;

public abstract class Splice<E> {

  public static <E> SpliceArray<E> array() {
    return new SpliceArray<>();
  }

  public static <E> SpliceList<E> list() {
    return new SpliceList<>();
  }

  public E splice(E value) {
    return clone(value);
  }

  abstract E clone(E value);

  private static class SpliceArray<E> extends Splice<E[]> {

    @Override
    E[] clone(E[] value) {
      return Arrays.clone(value);
    }
  }

  private static class SpliceList<E> extends Splice<List<E>> {

    @Override
    List<E> clone(List<E> value) {
      return Lists.clone(value);
    }
  }
}
