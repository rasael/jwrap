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

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static net.bervini.rasael.jwrap.api.JWrap.within;
import static org.assertj.core.api.Assertions.assertThat;

class LocalDateWrapTest {

  private static final LocalDate NULL_LOCAL_DATE = null;
  private static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);

  @Test
  void isBefore() {
    assertThat($(NULL_LOCAL_DATE).isBefore(NULL_LOCAL_DATE)).isFalse();
    assertThat($(NULL_LOCAL_DATE).isBefore(TODAY)).isFalse();

    assertThat($(YESTERDAY).isBefore(TODAY)).isTrue();
    assertThat($(TODAY).isBefore(TODAY)).isFalse();
    assertThat($(TODAY).isBefore(YESTERDAY)).isFalse();
    assertThat($(TOMORROW).isBefore(TODAY)).isFalse();
    assertThat($(TODAY).isBefore(TOMORROW)).isTrue();
  }

  @Test
  void isAfter() {
    assertThat($(NULL_LOCAL_DATE).isAfter(NULL_LOCAL_DATE)).isFalse();
    assertThat($(NULL_LOCAL_DATE).isAfter(TODAY)).isFalse();

    assertThat($(YESTERDAY).isAfter(TODAY)).isFalse();
    assertThat($(TODAY).isAfter(TODAY)).isFalse();
    assertThat($(TODAY).isAfter(YESTERDAY)).isTrue();
    assertThat($(TOMORROW).isAfter(TODAY)).isTrue();
    assertThat($(TODAY).isAfter(TOMORROW)).isFalse();
  }

  @Test
  void isYesterday() {
    assertThat($(NULL_LOCAL_DATE).isYesterday()).isFalse();
    assertThat($(YESTERDAY).isYesterday()).isTrue();
    assertThat($(TODAY).isYesterday()).isFalse();
    assertThat($(TOMORROW).isYesterday()).isFalse();
  }

  @Test
  void isToday() {
    assertThat($(NULL_LOCAL_DATE).isToday()).isFalse();
    assertThat($(YESTERDAY).isToday()).isFalse();
    assertThat($(TODAY).isToday()).isTrue();
    assertThat($(TOMORROW).isToday()).isFalse();
  }

  @Test
  void isTomorrow() {
    assertThat($(NULL_LOCAL_DATE).isTomorrow()).isFalse();
    assertThat($(YESTERDAY).isTomorrow()).isFalse();
    assertThat($(TODAY).isTomorrow()).isFalse();
    assertThat($(TOMORROW).isTomorrow()).isTrue();
  }

  @Test
  void isCloseTo() {
    Duration offset = Duration.ofDays(1);
    assertThat($(NULL_LOCAL_DATE).isCloseTo(NULL_LOCAL_DATE, offset))
        .isFalse();

    assertThat($(YESTERDAY).isCloseTo(TOMORROW, offset)).isFalse();
    assertThat($(YESTERDAY).isCloseTo(TODAY, offset)).isTrue();
    assertThat($(TODAY).isCloseTo(TOMORROW, offset)).isTrue();

    assertThat($(YESTERDAY).isCloseTo(TOMORROW, within(1, ChronoUnit.DAYS))).isFalse();
    assertThat($(YESTERDAY).isCloseTo(TODAY, within(1, ChronoUnit.DAYS))).isTrue();
    assertThat($(TODAY).isCloseTo(TOMORROW, within(1, ChronoUnit.DAYS))).isTrue();
  }
}