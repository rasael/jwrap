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

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class LongWrapTest {


  public static final Long NULL_LONG = null;

  @Test
  void isPositive() {
    assertThat($(1L).isPositive()).isTrue();
    assertThat($(42L).isPositive()).isTrue();

    assertThat($(-1L).isPositive()).isFalse();
    assertThat($(0L).isPositive()).isFalse();
    assertThat($(NULL_LONG).isPositive()).isFalse();
  }

  @Test
  void isZero() {
    assertThat($(0L).isZero()).isTrue();

    assertThat($(1L).isZero()).isFalse();
    assertThat($(42L).isZero()).isFalse();
    assertThat($(-1L).isZero()).isFalse();
    assertThat($(NULL_LONG).isZero()).isFalse();
  }

  @Test
  void isNegative() {
    assertThat($(-1L).isNegative()).isTrue();

    assertThat($(1L).isNegative()).isFalse();
    assertThat($(42L).isNegative()).isFalse();
    assertThat($(0L).isNegative()).isFalse();
    assertThat($(NULL_LONG).isNegative()).isFalse();
  }

  @Test
  void isEven() {
    assertThat($(-3L).isEven()).isFalse();
    assertThat($(-1L).isEven()).isFalse();
    assertThat($(1L).isEven()).isFalse();
    assertThat($(3L).isEven()).isFalse();

    assertThat($(-4L).isEven()).isTrue();
    assertThat($(-2L).isEven()).isTrue();
    assertThat($(0L).isEven()).isTrue();
    assertThat($(2L).isEven()).isTrue();
    assertThat($(4L).isEven()).isTrue();
  }

  @Test
  void isOdd() {
    assertThat($(-3L).isOdd()).isTrue();
    assertThat($(-1L).isOdd()).isTrue();
    assertThat($(1L).isOdd()).isTrue();
    assertThat($(3L).isOdd()).isTrue();

    assertThat($(-4L).isOdd()).isFalse();
    assertThat($(-2L).isOdd()).isFalse();
    assertThat($(0L).isOdd()).isFalse();
    assertThat($(2L).isOdd()).isFalse();
    assertThat($(4L).isOdd()).isFalse();
  }

  @Test
  void asString() {
    assertThat($(42L).asString().startsWith("42"))
        .isTrue();

    assertThat($((Long)null).asString().isEqualTo("null"))
        .isTrue();
  }
  @Test
  void conversions() {
    assertThat($(42L).asDouble().get()).isEqualTo(42D);
    assertThat($(42L).doubleValue()).isEqualTo(42D);

    assertThat($(42L).asLong().get()).isEqualTo(42L);
    assertThat($(42L).longValue()).isEqualTo(42L);

    assertThat($(42L).asFloat().get()).isEqualTo(42f);
    assertThat($(42L).floatValue()).isEqualTo(42f);

    assertThat($(42L).asInt().get()).isEqualTo(42);
    assertThat($(42L).intValue()).isEqualTo(42);
  }
}