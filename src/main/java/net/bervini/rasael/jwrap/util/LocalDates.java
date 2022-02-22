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

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class LocalDates {

  private LocalDates(){}

  public static boolean isBefore(ChronoLocalDate date, ChronoLocalDate other) {
    if (date==null || other==null)
      return false;

    return date.isBefore(other);
  }

  public static boolean isAfter(LocalDate date, ChronoLocalDate other) {
    if (date==null || other==null)
      return false;

    return date.isAfter(other);
  }

  public static boolean isToday(ChronoLocalDate date) {
    return date!=null && date.isEqual(today());
  }

  public static boolean isYesterday(ChronoLocalDate date) {
    return date!=null && date.isEqual(yesterday());
  }

  public static boolean isTomorrow(ChronoLocalDate date) {
    return date!=null && date.isEqual(tomorrow());
  }

  public static LocalDate today() {
    return LocalDate.now();
  }

  public static LocalDate tomorrow() {
    return LocalDate.now().plusDays(1);
  }

  public static LocalDate yesterday() {
    return LocalDate.now().minusDays(1);
  }
}
