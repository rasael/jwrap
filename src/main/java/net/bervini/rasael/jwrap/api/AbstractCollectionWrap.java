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

import net.bervini.rasael.jwrap.util.Collections;
import net.bervini.rasael.jwrap.util.Streams;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

@ParametersAreNullableByDefault
public abstract class AbstractCollectionWrap<
    ACTUAL extends Collection<ELEMENT>, ELEMENT,
    SELF extends AbstractCollectionWrap<ACTUAL, ELEMENT, SELF>>
  extends AbstractIterableWrap<ACTUAL, ELEMENT, SELF>
  implements MutableEnumerableWrap<ELEMENT, SELF> {

  protected AbstractCollectionWrap(ACTUAL value) {
    super(value);
  }

  @Override
  public int size() {
    return Collections.size(value);
  }

  @Override
  public boolean isEmpty() {
    return Collections.isEmpty(value);
  }

  @Override
  public SELF remove(ELEMENT element) {
    if (value!=null)
      value.remove(element);

    return myself;
  }

  @Override
  public SELF removeIf(Predicate<? super ELEMENT> predicate) {
    if (value!=null)
      value.removeIf(predicate);

    return myself;
  }

  @Override
  public Stream<ELEMENT> pureStream() {
    return Streams.stream(value);
  }

  @Override
  public ELEMENT[] pureArray(IntFunction<ELEMENT[]> generator) {
    return Collections.toArray(value, generator);
  }

}
