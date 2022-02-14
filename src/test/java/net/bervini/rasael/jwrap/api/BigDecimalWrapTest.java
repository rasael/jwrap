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

import java.math.BigDecimal;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BigDecimalWrapTest {


  public static final BigDecimal NULL_BIG_DECIMAL = null;

  public static final BigDecimal MINUS_FOUR = new BigDecimal(-4);
  public static final BigDecimal MINUS_THREE = new BigDecimal(-3);
  public static final BigDecimal MINUS_TWO = new BigDecimal(-2);
  public static final BigDecimal MINUS_ONE = new BigDecimal(-1);

  public static final BigDecimal PLUS_FOUR = new BigDecimal(4);
  public static final BigDecimal PLUS_THREE = new BigDecimal(3);
  public static final BigDecimal PLUS_TWO = new BigDecimal(2);
  public static final BigDecimal PLUS_ONE = new BigDecimal(1);

  public static final BigDecimal FOURTY_TWO = new BigDecimal(42);

  @Test
  void isPositive() {
    assertThat($(BigDecimal.ONE).isPositive()).isTrue();
    assertThat($(BigDecimal.TEN).isPositive()).isTrue();

    assertThat($(MINUS_ONE).isPositive()).isFalse();
    assertThat($(BigDecimal.ZERO).isPositive()).isFalse();
    assertThat($(NULL_BIG_DECIMAL).isPositive()).isFalse();
  }

  @Test
  void isZero() {
    assertThat($(BigDecimal.ZERO).isZero()).isTrue();

    assertThat($(BigDecimal.ONE).isZero()).isFalse();
    assertThat($(BigDecimal.TEN).isZero()).isFalse();
    assertThat($(MINUS_ONE).isZero()).isFalse();
    assertThat($(NULL_BIG_DECIMAL).isZero()).isFalse();
  }

  @Test
  void isOne() {
    assertThat($(BigDecimal.ONE).isOne()).isTrue();

    assertThat($(BigDecimal.ZERO).isOne()).isFalse();
    assertThat($(BigDecimal.TEN).isOne()).isFalse();
    assertThat($(MINUS_ONE).isOne()).isFalse();
    assertThat($(NULL_BIG_DECIMAL).isOne()).isFalse();
  }

  @Test
  void isNegative() {
    assertThat($(MINUS_ONE).isNegative()).isTrue();

    assertThat($(BigDecimal.ONE).isNegative()).isFalse();
    assertThat($(BigDecimal.TEN).isNegative()).isFalse();
    assertThat($(BigDecimal.ZERO).isNegative()).isFalse();
    assertThat($(NULL_BIG_DECIMAL).isNegative()).isFalse();
  }

  @Test
  void isEven() {
    assertThat($(MINUS_THREE).isEven()).isFalse();
    assertThat($(MINUS_ONE).isEven()).isFalse();
    assertThat($(BigDecimal.ONE).isEven()).isFalse();
    assertThat($(PLUS_THREE).isEven()).isFalse();

    assertThat($(MINUS_FOUR).isEven()).isTrue();
    assertThat($(MINUS_TWO).isEven()).isTrue();
    assertThat($(BigDecimal.ZERO).isEven()).isTrue();
    assertThat($(PLUS_TWO).isEven()).isTrue();
    assertThat($(PLUS_FOUR).isEven()).isTrue();
  }

  @Test
  void isOdd() {
    assertThat($(MINUS_THREE).isOdd()).isTrue();
    assertThat($(MINUS_ONE).isOdd()).isTrue();
    assertThat($(BigDecimal.ONE).isOdd()).isTrue();
    assertThat($(PLUS_THREE).isOdd()).isTrue();

    assertThat($(MINUS_FOUR).isOdd()).isFalse();
    assertThat($(MINUS_TWO).isOdd()).isFalse();
    assertThat($(BigDecimal.ZERO).isOdd()).isFalse();
    assertThat($(PLUS_TWO).isOdd()).isFalse();
    assertThat($(PLUS_FOUR).isOdd()).isFalse();
  }

  @Test
  void testReplaceNullWithZero() {
    assertThatThrownBy(() -> {
      int val = $(NULL_BIG_DECIMAL).intValue();
    }).isInstanceOf(NullPointerException.class);

    assertThat($(NULL_BIG_DECIMAL).orZero().intValue()).isNotNull().isEqualTo(0);
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
    assertThat($(FOURTY_TWO).asBigDecimal().isEqualTo(FOURTY_TWO)).isTrue();
  }
}