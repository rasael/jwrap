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

package net.bervini.rasael.jwrap.data;

import net.bervini.rasael.jwrap.util.Preconditions;

public record Offset<T extends Number>(T value, boolean strict) {

  public Offset {
    Preconditions.requireArgNonNull(value);
    if (strict) {
      Preconditions.checkArgument(value.doubleValue() > 0D, "Strict offset must be greater than zero");
    }
    else {
      Preconditions.checkArgument(value.doubleValue() >= 0D, "Offset must be positive");
    }
  }

  public static <T extends Number> Offset<T> offset(T value) {
    Preconditions.requireArgNonNull(value);
    Preconditions.checkArgument(value.doubleValue() >= 0D, "Offset must be positive");
    return new Offset<>(value, false);
  }

  public static <T extends Number> Offset<T> strictOffset(T value) {
    Preconditions.requireArgNonNull(value);
    Preconditions.checkArgument(value.doubleValue() > 0D, "Strict offset must be greater than zero");
    return new Offset<>(value, true);
  }
}
