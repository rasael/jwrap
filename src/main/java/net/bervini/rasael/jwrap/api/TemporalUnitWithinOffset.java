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

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalUnitWithinOffset extends TemporalUnitOffset {

  public TemporalUnitWithinOffset(long value, @Nonnull TemporalUnit unit) {
    super(value, unit);
  }

  @Override
  public boolean isBeyondOffset(@Nonnull Temporal temporal1, @Nonnull Temporal temporal2) {
    try {
      return getDifference(temporal1, temporal2) > value;
    }
    catch (@SuppressWarnings("unused") ArithmeticException e) {
      return getAbsoluteDuration(temporal1, temporal2).compareTo(Duration.of(value, unit)) > 0;
    }
  }

}
