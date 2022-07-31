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

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.IntPredicate;

public interface AbstractModifiableElement<SELF extends AbstractWrap<?, SELF>> {

  default boolean isAbstract() {
    return checkModifier(Modifier::isAbstract);
  }

  default boolean isStatic() {
    return checkModifier(Modifier::isStatic);
  }

  default boolean isPublic() {
    return checkModifier(Modifier::isPublic);
  }

  default boolean isFinal() {
    return checkModifier(Modifier::isFinal);
  }

  default boolean isProtected() {
    return checkModifier(Modifier::isProtected);
  }

  default boolean isPrivate() {
    return checkModifier(Modifier::isPrivate);
  }

  default int getModifiers() {
    return modifiers().orElse(0);
  }

  OptionalInt modifiers();

  private boolean checkModifier(IntPredicate predicate) {
    return Objects.requireNonNull(predicate).test(getModifiers());
  }
}
