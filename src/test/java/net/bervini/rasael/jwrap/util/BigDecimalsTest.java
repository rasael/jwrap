/*
 * Copyright 2022-2023 Rasael Bervini
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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalsTest {

  @Test
  void negate() {
    assertThat(BigDecimals.negate(BigDecimal.ZERO)).isZero();

    assertThat(BigDecimals.negate(BigDecimal.TEN)).isEqualTo("-10");
    assertThat(BigDecimals.negate(BigDecimals.MINUS_ONE)).isEqualTo(BigDecimal.ONE);

    assertThat(BigDecimals.negate(new BigDecimal("-1.234"))).isEqualTo("1.234");
    assertThat(BigDecimals.negate(new BigDecimal("1.234"))).isEqualTo("-1.234");
  }
}