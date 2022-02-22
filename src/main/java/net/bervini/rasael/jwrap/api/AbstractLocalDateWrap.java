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

import net.bervini.rasael.jwrap.util.LocalDates;
import net.bervini.rasael.jwrap.util.Temporals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AbstractLocalDateWrap<SELF extends AbstractLocalDateWrap<SELF>>
  extends AbstractTemporalWrap<LocalDate, SELF> {

  protected AbstractLocalDateWrap(LocalDate value) {
    super(value);
  }

  public boolean isBefore(ChronoLocalDate other) {
    return LocalDates.isBefore(value, other);
  }

  public boolean isAfter(ChronoLocalDate other) {
    return LocalDates.isAfter(value, other);
  }

  public boolean isYesterday() {
    return LocalDates.isYesterday(value);
  }

  public boolean isToday() {
    return LocalDates.isToday(value);
  }

  public boolean isTomorrow() {
    return LocalDates.isTomorrow(value);
  }

  public boolean isCloseTo(LocalDate other, Duration offset) {
    return Temporals.isCloseTo(value, other, new TemporalUnitWithinOffset(offset.toDays(), ChronoUnit.DAYS));
  }

}
