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

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDatesTest {

  @Test
  void isBefore() {
    LocalDate before = LocalDate.of(2000, 1, 1);
    LocalDate after = LocalDate.of(2012, 12, 21);
    assertThat(LocalDates.isBefore(null, null)).isFalse();
    assertThat(LocalDates.isBefore(before, null)).isFalse();
    assertThat(LocalDates.isBefore(null, after)).isFalse();


    assertThat(LocalDates.isBefore(after, before)).isFalse();
    assertThat(LocalDates.isBefore(before, after)).isTrue();
  }
}