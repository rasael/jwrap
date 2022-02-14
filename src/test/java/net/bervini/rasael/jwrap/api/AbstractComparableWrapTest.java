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

class AbstractComparableWrapTest {

  @Test
  void isLessThan() {
    assertThat($(5).isLessThan(6)).isTrue();

    assertThat($(6).isLessThan(6)).isFalse();
    assertThat($(6).isLessThan(5)).isFalse();

    assertThat($((Integer)null).isLessThan(5)).isFalse();
    assertThat($(5).isLessThan(null)).isFalse();
  }

  @Test
  void isLessThanOrEqualTo() {
    assertThat($(5).isLessThanOrEqualTo(6)).isTrue();
    assertThat($(6).isLessThanOrEqualTo(6)).isTrue();

    assertThat($(6).isLessThanOrEqualTo(5)).isFalse();

    assertThat($((Integer)null).isLessThanOrEqualTo(5)).isFalse();
    assertThat($(5).isLessThanOrEqualTo(null)).isFalse();
  }

  @Test
  void isGreaterThan() {
    assertThat($(6).isGreaterThan(5)).isTrue();

    assertThat($(6).isGreaterThan(6)).isFalse();
    assertThat($(5).isGreaterThan(6)).isFalse();

    assertThat($((Integer)null).isGreaterThan(5)).isFalse();
    assertThat($(5).isGreaterThan(null)).isFalse();
  }

}