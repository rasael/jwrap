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

import net.bervini.rasael.jwrap.util.Lists;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

@ParametersAreNullableByDefault
public abstract class AbstractListWrap<
    ACTUAL extends List<ELEMENT>, ELEMENT,
    SELF extends AbstractListWrap<ACTUAL, ELEMENT, SELF>>
  extends AbstractCollectionWrap<ACTUAL, ELEMENT, SELF>
  implements MutableIndexedObjectEnumerableWrap<ELEMENT, SELF> {

  protected AbstractListWrap(ACTUAL value) {
    super(value);
  }

  @Override
  public SELF fill(@Nullable ELEMENT element) {
    Lists.fill(value, element);
    return myself;
  }

  @NotNull
  @Override
  public SELF set(int index, ELEMENT element) {
    Lists.set(value, index, element);
    return myself;
  }

  @Override
  public SELF remove(int index) {
    Lists.remove(value, index);
    return myself;
  }

  @Nullable
  @Override
  public ELEMENT replace(int index, ELEMENT element) {
    return Lists.set(value, index, element);
  }

  @Nullable
  @Override
  public ELEMENT get(int index) {
    return Lists.get(value, index);
  }

  @Nullable
  @Override
  public ELEMENT at(int index) {
    return Lists.at(value, index);
  }

  @Override
  public int indexOf(ELEMENT element) {
    return Lists.indexOf(value, element);
  }

  @Override
  public boolean contains(ELEMENT element, int index) {
    return Lists.contains(value, element, index);
  }

  @Override
  public boolean doesNotContain(ELEMENT element, int index) {
    return Lists.doesNotContain(value, element, index);
  }

  @Override
  public ELEMENT pop() {
    return Lists.pop(value);
  }

  @Override
  public ELEMENT shift() {
    return Lists.shift(value);
  }

}
