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


import net.bervini.rasael.jwrap.util.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNullableByDefault;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * JWrap Convenience Operator factory class.
 *
 * @author Rasael Bervini
 * @version 0.1
 * @since 2022-02-05
 */
@ParametersAreNullableByDefault
public class JWrap {

  private static final String VERSION = "0.1";

  private static final Object AUTHORS = "Rasael Bervini";

  private static final String LICENSE = "Apache License 2.0";

  @Nonnull
  public static <T> ObjectArrayWrap<T> $(T[] val) {
    return new ObjectArrayWrap<>(val);
  }

  @Nonnull
  public static <T> ObjectArrayWrap<T> $(AtomicReferenceArray<T> ref) {
    return new ObjectArrayWrap<>(ref);
  }
  @Nonnull
  public static <T> ListWrap<T> $(List<T> list) {
    return new ListWrap<>(list);
  }

  @Nonnull
  public static StringWrap $(String val) {
    return new StringWrap(val);
  }

  @Nonnull
  public static IntWrap $(Integer val) {
    return new IntWrap(val);
  }

  @Nonnull
  public static IntWrap $(int val) {
    return new IntWrap(val);
  }

  @Nonnull
  public static LongWrap $(Long val) {
    return new LongWrap(val);
  }

  @Nonnull
  public static LongWrap $(long val) {
    return new LongWrap(val);
  }

  @Nonnull
  public static FloatWrap $(Float val) {
    return new FloatWrap(val);
  }

  @Nonnull
  public static FloatWrap $(float val) {
    return new FloatWrap(val);
  }

  @Nonnull
  public static DoubleWrap $(Double val) {
    return new DoubleWrap(val);
  }

  @Nonnull
  public static DoubleWrap $(double val) {
    return new DoubleWrap(val);
  }

  @Nonnull
  public static BigDecimalWrap $(BigDecimal val) {
    return new BigDecimalWrap(val);
  }

  @Nonnull
  public static <T> PredicateWrap<T> $(Predicate<T> val) {
    return new PredicateWrap<>(val);
  }

  @Nonnull
  public static <T> SupplierWrap<T> $(Supplier<T> val) {
    return new SupplierWrap<>(val);
  }
  
  @Nonnull
  public static <T> StreamWrap<T> $(Stream<T> val) {
    return new StreamWrap<>(val);
  }

  @Nonnull
  public static LocalDateWrap $(LocalDate val) {
    return new LocalDateWrap(val);
  }

  @Nonnull
  public static FileWrap $(File val) {
    return new FileWrap(val);
  }

  @Nonnull
  public static PathWrap $(Path val) {
    return new PathWrap(val);
  }

  public static <T> ObjectWrap<T> $(T val) {
    return new ObjectWrap<>(val);
  }

  // -------------------------------------------------------------------------------------------------------------------
  @Nonnull
  public static <T> ObjectArrayWrap<T> Wrap(T[] val) {
    return new ObjectArrayWrap<>(val);
  }
  @Nonnull
  public static <T> ObjectArrayWrap<T> Wrap(AtomicReferenceArray<T> val) {
    return new ObjectArrayWrap<>(val);
  }

  @Nonnull
  public static <T> ListWrap<T> Wrap(List<T> val) {
    return new ListWrap<>(val);
  }

  @Nonnull
  public static StringWrap Wrap(String string) {
    return new StringWrap(string);
  }

  @Nonnull
  public static IntWrap Wrap(Integer integer) {
    return new IntWrap(integer);
  }

  @Nonnull
  public static IntWrap Wrap(int integer) {
    return new IntWrap(integer);
  }

  @Nonnull
  public static LongWrap Wrap(Long val) {
    return new LongWrap(val);
  }
  
  @Nonnull
  public static FloatWrap Wrap(float val) {
    return new FloatWrap(val);
  }
  
  @Nonnull
  public static FloatWrap Wrap(Float val) {
    return new FloatWrap(val);
  }

  @Nonnull
  public static LongWrap Wrap(long val) {
    return new LongWrap(val);
  }

  @Nonnull
  public static DoubleWrap Wrap(Double val) {
    return new DoubleWrap(val);
  }

  @Nonnull
  public static DoubleWrap Wrap(double val) {
    return new DoubleWrap(val);
  }

  @Nonnull
  public static BigDecimalWrap Wrap(BigDecimal val) {
    return new BigDecimalWrap(val);
  }

  @Nonnull
  public static <T> PredicateWrap<T> Wrap(Predicate<T> val) {
    return new PredicateWrap<>(val);
  }

  @Nonnull
  public static <T> StreamWrap<T> Wrap(Stream<T> val) {
    return new StreamWrap<>(val);
  }

  @Nonnull
  public static LocalDateWrap Wrap(LocalDate val) {
    return new LocalDateWrap(val);
  }

  @Nonnull
  public static FileWrap Wrap(File val) {
    return new FileWrap(val);
  }

  @Nonnull
  public static PathWrap Wrap(Path val) {
    return new PathWrap(val);
  }

  public static <T> ObjectWrap<T> Wrap(T val) {
    return new ObjectWrap<>(val);
  }
  // -------------------------------------------------------------------------------------------------------------------

  public static TemporalUnitOffset within(Duration duration) {
    Preconditions.requireArgNonNull(duration);
    return new TemporalUnitWithinOffset(duration.toNanos(), ChronoUnit.NANOS);
  }

  public static TemporalUnitOffset within(long value, TemporalUnit unit) {
    Preconditions.checkArgument(value>=0, "Argument must be positive, provided <%d>", value);
    Preconditions.requireArgNonNull(unit);
    return new TemporalUnitWithinOffset(value, unit);
  }

  // -------------------------------------------------------------------------------------------------------------------

  private JWrap(){}

  static {
    if (System.out!=null && !System.getProperties().containsKey("jwrap-no-splash")) {
      System.out.println(onliner());
    }
  }

  private static String onliner() {
    return "This application is empowered by JWrap v%s, written by %s".formatted(VERSION, AUTHORS);
  }

  public static String about() {
    return """
        JWrap - Convenience Java Operator
        Version %s - Written by %s
        Released under %s, Copyright 2022-%s
        """.formatted(VERSION, AUTHORS, LICENSE, LocalDate.now().getYear());
  }
}