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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@ParametersAreNonnullByDefault
public abstract class AbstractOptional<T, SELF extends AbstractOptional<T, SELF>> {

  private final T value;

  protected AbstractOptional(@Nullable T value) {
    this.value = value;
  }

  public boolean isPresent() {
    return value!=null;
  }

  public boolean isEmpty() {
    return value==null;
  }

  public T orElse(T other) {
    return value!=null ? value : other;
  }

  public T orElseGet(Supplier<? extends T> supplier) {
    return value!=null ? value : supplier.get();
  }

  public T orElseThrow() {
    if (value==null)
      throw new NoSuchElementException();

    return value;
  }

  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (value != null) {
      return value;
    }
    else {
      throw exceptionSupplier.get();
    }
  }

  public void ifPresent(Consumer<? super T> consumer) {
    if (value!=null)
      consumer.accept(value);
  }

  public void ifPresentOrElse(Consumer<? super T> consumer, Runnable orElseRun) {
    if (value!=null) {
      consumer.accept(value);
    }
    else {
      orElseRun.run();
    }
  }

  public OptionalBoolean mapToBoolean(Predicate<? super T> predicate) {
    return value!=null ? OptionalBoolean.of(predicate.test(value)) : OptionalBoolean.empty();
  }

  public SELF filter(Predicate<? super T> predicate) {
    if (value==null)
      return self();

    return predicate.test(value) ? self() : with(null);
  }

  public <R> Optional<R> map(Function<? super T, ? extends R> mapper) {
    if (value==null)
      return Optional.empty();

    return Optional.ofNullable(mapper.apply(value));
  }

  public OptionalString mapToString(Function<? super T, String> mapper) {
    if (value==null)
      return OptionalString.empty();

    return OptionalString.ofNullable(mapper.apply(value));
  }

  public SELF apply(UnaryOperator<T> operator) {
    if (value==null)
      return self();

    return with(operator.apply(value));
  }

  protected abstract SELF self();

  protected abstract SELF with(@Nullable T value);
}
