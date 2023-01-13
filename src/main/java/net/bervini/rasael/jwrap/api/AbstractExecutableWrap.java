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

import java.lang.reflect.Executable;
import java.util.OptionalInt;

public abstract class AbstractExecutableWrap<E extends Executable, SELF extends AbstractExecutableWrap<E, SELF>>
    extends AbstractWrap<E, SELF>
    implements AbstractModifiableElement<SELF> {

  protected AbstractExecutableWrap(E value) {
    super(value);
  }

  @Override
  public OptionalInt modifiers() {
    return value!=null ? OptionalInt.of(value.getModifiers()) : OptionalInt.empty();
  }
}
