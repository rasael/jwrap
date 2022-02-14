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

import static org.assertj.core.api.Assertions.assertThat;

class ArraysTest {

  private static final Object[] EMPTY_ARRAY = {};

  private static final Object[] NULL_ARRAY = null;

  private Object[] nullArray() {
    return NULL_ARRAY;
  }

  private Object[] emptyArray() {
    return EMPTY_ARRAY;
  }

  @Test
  void isEmpty() {
    assertThat(Arrays.isEmpty(nullArray()))
        .isTrue();

    assertThat(Arrays.isEmpty(EMPTY_ARRAY))
        .isTrue();

    assertThat(Arrays.isEmpty(new String[]{"a"}))
        .isFalse();

    assertThat(Arrays.isEmpty(new String[]{null}))
        .isFalse();
  }

  @Test
  void at() {
    String[] array = {"a","b"};
    assertThat(Arrays.at(nullArray(), 0))
        .isNull();
    assertThat(Arrays.at(nullArray(), -1))
        .isNull();
    assertThat(Arrays.at(nullArray(), 1))
        .isNull();

    assertThat(Arrays.at(EMPTY_ARRAY, 0))
        .isNull();
    assertThat(Arrays.at(EMPTY_ARRAY, -1))
        .isNull();
    assertThat(Arrays.at(EMPTY_ARRAY, 1))
        .isNull();

    assertThat(Arrays.at(array, 0))
        .isEqualTo("a");
    assertThat(Arrays.at(array, 1))
        .isEqualTo("b");
    assertThat(Arrays.at(array, 2))
        .isNull();
    assertThat(Arrays.at(array, 3))
        .isNull();
    assertThat(Arrays.at(array, -1))
        .isEqualTo("b");
    assertThat(Arrays.at(array, -2))
        .isEqualTo("a");
    assertThat(Arrays.at(array, -3))
        .isNull();
  }

}