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

package net.bervini.rasael.jwrap.util;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
public class OptionalString extends AbstractOptional<String, OptionalString> {

  private static final OptionalString EMPTY = new OptionalString(null);

  private OptionalString(@Nullable String value) {
    super(value);
  }

  @Override
  protected OptionalString self() {
    return this;
  }

  @Override
  protected OptionalString with(@Nullable String value) {
    return ofNullable(value);
  }

  public static OptionalString of(String value) {
    return new OptionalString(Objects.requireNonNull(value));
  }

  public static OptionalString ofNullable(@Nullable String value) {
    return value!=null ? new OptionalString(value) : empty();
  }

  public static OptionalString empty() {
    return EMPTY;
  }
}
