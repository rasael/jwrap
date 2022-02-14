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

class CharSequencesTest {

  CharSequence SAMPLE = "sample";
  CharSequence EMPTY = "";

  @Test
  void codePoints() {
    assertThat(CharSequences.codePoints(null))
        .isNull();
    assertThat(CharSequences.codePoints(SAMPLE).toArray())
        .isEqualTo(SAMPLE.codePoints().toArray());
  }

  @Test
  void isEmpty() {
    assertThat(CharSequences.isEmpty(null)).isTrue();
    assertThat(CharSequences.isEmpty(SAMPLE)).isFalse();
    assertThat(CharSequences.isEmpty(EMPTY)).isTrue();
  }
  @Test
  void length() {
    assertThat(CharSequences.isEmpty(null)).isTrue();
    assertThat(CharSequences.isEmpty(SAMPLE)).isFalse();
    assertThat(CharSequences.isEmpty(EMPTY)).isTrue();
  }

  @Test
  void charAt() {
    assertThat(CharSequences.charAt(null, 0)).isNull();
    assertThat(CharSequences.charAt(SAMPLE, 0)).isEqualTo(SAMPLE.charAt(0));
  }

  @Test
  void at() {
    assertThat(CharSequences.at("dog",0))
        .isEqualTo('d');
    assertThat(CharSequences.at("dog",1))
        .isEqualTo('o');
    assertThat(CharSequences.at("dog",2))
        .isEqualTo('g');
    assertThat(CharSequences.at("dog",3))
        .isNull();

    assertThat(CharSequences.at("dog",-1))
        .isEqualTo('g');
    assertThat(CharSequences.at("dog",-2))
        .isEqualTo('o');
    assertThat(CharSequences.at("dog",-3))
        .isEqualTo('d');
    assertThat(CharSequences.at("dog",-4))
        .isNull();
  }
}