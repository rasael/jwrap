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

import net.bervini.rasael.jwrap.data.Offset;
import org.junit.jupiter.api.Test;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class IntWrapTest {


  public static final Integer NULL_INTEGER = null;

  public static final int ZERO = 0;
  public static final int ONE = 1;
  public static final int MINUS_ONE = -1;
  public static final int FOURTY_TWO = 42;

  @Test
  void isNotPositive() {
    assertThat($(NULL_INTEGER).isNotPositive()).isTrue();
    assertThat($(MINUS_ONE).isNotPositive()).isTrue();
    assertThat($(ZERO).isNotPositive()).isTrue();
    assertThat($(ONE).isNotPositive()).isFalse();
  }

  @Test
  void isNotNegative() {
    assertThat($(NULL_INTEGER).isNotNegative()).isTrue();
    assertThat($(MINUS_ONE).isNotNegative()).isFalse();
    assertThat($(ZERO).isNotNegative()).isTrue();
    assertThat($(ONE).isNotNegative()).isTrue();
  }

  @Test
  void isNotZero() {
    assertThat($(NULL_INTEGER).isNotZero()).isTrue();
    assertThat($(MINUS_ONE).isNotZero()).isTrue();
    assertThat($(ZERO).isNotZero()).isFalse();
    assertThat($(ONE).isNotZero()).isTrue();
  }

  @Test
  void isBetween() {
    assertThat($(NULL_INTEGER).isBetween(ZERO, ONE)).isFalse();
    assertThat($(ONE).isBetween(ZERO, ONE)).isTrue();
    assertThat($(ONE).isBetween(ONE,2)).isTrue();
    assertThat($(ONE).isStrictlyBetween(ONE,2)).isFalse();
    assertThat($(ONE).isBetween(MINUS_ONE,2)).isTrue();
  }
  @Test
  void isPositive() {
    assertThat($(ONE).isPositive()).isTrue();
    assertThat($(FOURTY_TWO).isPositive()).isTrue();

    assertThat($(MINUS_ONE).isPositive()).isFalse();
    assertThat($(ZERO).isPositive()).isFalse();
    assertThat($(NULL_INTEGER).isPositive()).isFalse();
  }

  @Test
  void isZero() {
    assertThat($(ZERO).isZero()).isTrue();

    assertThat($(ONE).isZero()).isFalse();
    assertThat($(FOURTY_TWO).isZero()).isFalse();
    assertThat($(MINUS_ONE).isZero()).isFalse();
    assertThat($(NULL_INTEGER).isZero()).isFalse();
  }

  @Test
  void isNegative() {
    assertThat($(MINUS_ONE).isNegative()).isTrue();

    assertThat($(ONE).isNegative()).isFalse();
    assertThat($(FOURTY_TWO).isNegative()).isFalse();
    assertThat($(ZERO).isNegative()).isFalse();
    assertThat($(NULL_INTEGER).isNegative()).isFalse();
  }

  @Test
  void isEven() {
    assertThat($(-3).isEven()).isFalse();
    assertThat($(MINUS_ONE).isEven()).isFalse();
    assertThat($(ONE).isEven()).isFalse();
    assertThat($(3).isEven()).isFalse();

    assertThat($(-4).isEven()).isTrue();
    assertThat($(-2).isEven()).isTrue();
    assertThat($(ZERO).isEven()).isTrue();
    assertThat($(2).isEven()).isTrue();
    assertThat($(4).isEven()).isTrue();
  }

  @Test
  void isOdd() {
    assertThat($(-3).isOdd()).isTrue();
    assertThat($(MINUS_ONE).isOdd()).isTrue();
    assertThat($(ONE).isOdd()).isTrue();
    assertThat($(3).isOdd()).isTrue();

    assertThat($(-4).isOdd()).isFalse();
    assertThat($(-2).isOdd()).isFalse();
    assertThat($(ZERO).isOdd()).isFalse();
    assertThat($(2).isOdd()).isFalse();
    assertThat($(4).isOdd()).isFalse();
  }

  @Test
  void isCloseTo() {
    assertThat($(NULL_INTEGER).isCloseTo(50, Offset.offset(10))).isFalse();
    assertThat($(FOURTY_TWO).isCloseTo(50, Offset.offset(10))).isTrue();
    assertThat($(FOURTY_TWO).isCloseTo(50, Offset.offset(8))).isTrue();

    assertThat($(FOURTY_TWO).isCloseTo(50, Offset.offset(5))).isFalse();
    assertThat($(FOURTY_TWO).isCloseTo(50, Offset.strictOffset(8))).isFalse();
  }

  @Test
  void isNotCloseTo() {
    assertThat($(NULL_INTEGER).isNotCloseTo(50, Offset.offset(10))).isTrue();
    assertThat($(FOURTY_TWO).isNotCloseTo(50, Offset.offset(10))).isFalse();
    assertThat($(FOURTY_TWO).isNotCloseTo(50, Offset.offset(8))).isFalse();

    assertThat($(FOURTY_TWO).isNotCloseTo(50, Offset.offset(5))).isTrue();
    assertThat($(FOURTY_TWO).isNotCloseTo(50, Offset.strictOffset(8))).isTrue();
  }

  @Test
  void toJson() {
    assertThat($(FOURTY_TWO).json()).isEqualTo("42");
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