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

import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Preconditions {

  private Preconditions(){}

  // -------------------------------------------------------------------------------------------------------------------

  @Contract("null -> fail")
  @Nonnull
  public static <T> T requireArgNonNull(@Nullable T value) {
    if (value==null)
      throw new IllegalArgumentException();

    return value;
  }

  public static void checkArgument(boolean expression, String message, Object...args) {
    if (!expression) {
      throw new IllegalArgumentException(formatIf(message, args));
    }
  }

  private static String formatIf(String message, @Nullable Object[] args) {
    if (args==null || args.length==0) {
      return message;
    }
    return message.formatted(args);
  }
}
