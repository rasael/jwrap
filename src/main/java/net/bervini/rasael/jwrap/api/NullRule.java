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

/**
 * <p>Allows to configure how nulls are handled by jwrap, when no meaningful alternative is available.
 *
 * <p>This class provides null values for all boxed primitive types, and rules on how to compare null
 */
public interface NullRule {

  /**
   * <p>Handle a possible null {@link Character} value.
   *
   * <p>If the value is null, this rule may return a default value, throw a meaningful exception, or return null
   * (possibly causing a {@link NullPointerException} elsewhere in the code).
   */
  char get(@Nullable Character c);

  /**
   * Returns true if {@code null} compares lower than a non null value
   *
   * @implNote default implementation returns {@code true}.
   */
  default boolean nullIsLess() {
    return true;
  }
}
