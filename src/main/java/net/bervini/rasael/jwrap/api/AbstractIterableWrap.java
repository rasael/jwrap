/*
 * Copyright 2022-2023 Rasael Bervini
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

import net.bervini.rasael.jwrap.util.Iterables;
import net.bervini.rasael.jwrap.util.UnsafeConsumer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Iterator;
import java.util.Objects;

@ParametersAreNullableByDefault
public abstract class AbstractIterableWrap<
    ACTUAL extends Iterable<ELEMENT>,
    ELEMENT,
    SELF extends AbstractIterableWrap<ACTUAL, ELEMENT, SELF>>
  extends AbstractWrap<ACTUAL, SELF> implements ObjectIterableWrap<ACTUAL, ELEMENT, SELF> {

  protected AbstractIterableWrap(ACTUAL value) {
    super(value);
  }

  public <X extends Throwable> void forEachChecked(UnsafeConsumer<? super ELEMENT, ? extends X> action) throws X {
    Objects.requireNonNull(action);
    for (ELEMENT t : this) {
        action.accept(t);
    }
  }

  @NotNull
  @Override
  public Iterator<ELEMENT> iterator() {
    return Iterables.iterator(value);
  }

  @Override
  public boolean isNullOrEmpty() {
    return Iterables.isNullOrEmpty(value);
  }

  @Override
  public boolean isEmpty() {
    return Iterables.isEmpty(value);
  }

  @Override
  public boolean contains(@Nullable Iterable<ELEMENT> iterable) {
    return Iterables.contains(value, iterable);
  }

  @Override
  public int size() {
    return Iterables.size(value);
  }

}
