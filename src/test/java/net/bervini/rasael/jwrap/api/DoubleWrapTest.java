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

class DoubleWrapTest {


  public static final Double NULL_DOUBLE = null;

  public static final double ZERO = 0D;
  public static final double ONE = 1D;
  public static final double MINUS_ONE = -1D;
  public static final double FOURTY_TWO = 42D;

  @Test
  void isPositive() {
    assertThat($(ONE).isPositive()).isTrue();
    assertThat($(FOURTY_TWO).isPositive()).isTrue();

    assertThat($(MINUS_ONE).isPositive()).isFalse();
    assertThat($(ZERO).isPositive()).isFalse();
    assertThat($(NULL_DOUBLE).isPositive()).isFalse();
  }

  @Test
  void isZero() {
    assertThat($(ZERO).isZero()).isTrue();

    assertThat($(ONE).isZero()).isFalse();
    assertThat($(FOURTY_TWO).isZero()).isFalse();
    assertThat($(MINUS_ONE).isZero()).isFalse();
    assertThat($(NULL_DOUBLE).isZero()).isFalse();
  }

  @Test
  void isNegative() {
    assertThat($(MINUS_ONE).isNegative()).isTrue();

    assertThat($(ONE).isNegative()).isFalse();
    assertThat($(FOURTY_TWO).isNegative()).isFalse();
    assertThat($(ZERO).isNegative()).isFalse();
    assertThat($(NULL_DOUBLE).isNegative()).isFalse();
  }

  @Test
  void isEven() {
    assertThat($(-3D).isEven()).isFalse();
    assertThat($(MINUS_ONE).isEven()).isFalse();
    assertThat($(ONE).isEven()).isFalse();
    assertThat($(3D).isEven()).isFalse();

    assertThat($(-4D).isEven()).isTrue();
    assertThat($(-2D).isEven()).isTrue();
    assertThat($(ZERO).isEven()).isTrue();
    assertThat($(2D).isEven()).isTrue();
    assertThat($(4D).isEven()).isTrue();
  }

  @Test
  void isOdd() {
    assertThat($(-3D).isOdd()).isTrue();
    assertThat($(MINUS_ONE).isOdd()).isTrue();
    assertThat($(ONE).isOdd()).isTrue();
    assertThat($(3D).isOdd()).isTrue();

    assertThat($(-4D).isOdd()).isFalse();
    assertThat($(-2D).isOdd()).isFalse();
    assertThat($(ZERO).isOdd()).isFalse();
    assertThat($(2D).isOdd()).isFalse();
    assertThat($(4D).isOdd()).isFalse();
  }

  @Test
  void conversions() {
    assertThat($(FOURTY_TWO).asDouble().get()).isEqualTo(42D);
    assertThat($(FOURTY_TWO).doubleValue()).isEqualTo(42D);

    assertThat($(FOURTY_TWO).asLong().get()).isEqualTo(42L);
    assertThat($(FOURTY_TWO).longValue()).isEqualTo(42L);

    assertThat($(FOURTY_TWO).asFloat().get()).isEqualTo(42f);
    assertThat($(FOURTY_TWO).floatValue()).isEqualTo(42f);

    assertThat($(FOURTY_TWO).asInt().get()).isEqualTo(42);
    assertThat($(FOURTY_TWO).intValue()).isEqualTo(42);
  }
}