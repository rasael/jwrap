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
import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

import static java.lang.Math.abs;

public abstract class TemporalUnitOffset implements TemporalOffset<Temporal> {

  protected final TemporalUnit unit;
  protected final long value;

  /**
   * Creates a new temporal offset for a given temporal unit.
   * @param value the value of the offset.
   * @param unit temporal unit of the offset.
   * @throws NullPointerException if the given unit is {@code null}.
   * @throws IllegalArgumentException if the given value is negative.
   */
  public TemporalUnitOffset(long value, @Nonnull TemporalUnit unit) {
    Preconditions.requireArgNonNull(unit);
    Preconditions.checkArgument(value>=0, "the value of an offset must be greater than or equal to zero");
    this.value = value;
    this.unit = unit;
  }

  /**
   * Returns absolute value of the difference according to time unit.
   *
   * @param temporal1 the first {@link Temporal}
   * @param temporal2 the second {@link Temporal}
   * @return absolute value of the difference according to time unit.
   */
  protected long getDifference(@Nonnull Temporal temporal1, @Nonnull Temporal temporal2) {
    return abs(unit.between(temporal1, temporal2));
  }

  /**
   * Returns absolute value of the difference as Duration.
   *
   * @param temporal1 the first {@link Temporal}
   * @param temporal2 the second {@link Temporal}
   * @return absolute value of the difference as Duration.
   */
  protected Duration getAbsoluteDuration(@Nonnull Temporal temporal1, @Nonnull Temporal temporal2) {
    return Duration.between(temporal1, temporal2).abs();
  }
}
