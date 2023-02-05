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
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Immutable
@ThreadSafe
@ParametersAreNonnullByDefault
public class OptionalBoolean implements BooleanSupplier {

  private static final OptionalBoolean EMPTY = new OptionalBoolean();
  public static final OptionalBoolean TRUE = new OptionalBoolean(true);
  public static final OptionalBoolean FALSE = new OptionalBoolean(false);
  private final Boolean value;

  private OptionalBoolean(boolean value) {
    this.value = value;
  }

  private OptionalBoolean() {
    this.value = null;
  }

  public static OptionalBoolean of(boolean value) {
    return value ? TRUE : FALSE;
  }

  public static OptionalBoolean ofNullable(@Nullable Boolean value) {
    if (value==null) return empty();
    return value ? TRUE : FALSE;
  }

  public static OptionalBoolean empty() {
    return EMPTY;
  }

  @Override
  public boolean getAsBoolean() {
    if (value==null)
      throw new NoSuchElementException();

    return value;
  }

  public boolean isTrue() {
    return Boolean.TRUE.equals(value);
  }

  public boolean isFalse() {
    return Boolean.FALSE.equals(value);
  }


  public boolean isPresent() {
    return value!=null;
  }

  public boolean isEmpty() {
    return value==null;
  }

  public boolean orElseTrue() {
    return orElse(true);
  }

  public boolean orElseFalse() {
    return orElse(false);
  }

  public boolean orElse(boolean other) {
    return value!=null ? value : other;
  }

  public boolean orElseGet(BooleanSupplier supplier) {
    return value!=null ? value : supplier.getAsBoolean();
  }

  public void ifPresent(BooleanConsumer consumer) {
    if (value!=null)
      consumer.accept(value);
  }

  public void ifTrue(Runnable runnable) {
    if (Boolean.TRUE.equals(value))
      runnable.run();
  }

  public void ifFalse(Runnable runnable) {
    if (Boolean.FALSE.equals(value))
      runnable.run();
  }

  public Optional<Boolean> boxed() {
    return value!=null ? Optional.of(value) : Optional.empty();
  }

  public boolean orElseThrow() {
    if (value==null)
      throw new NoSuchElementException();

    return value;
  }
}
