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

import static org.assertj.core.api.Assertions.assertThat;

class StringsTest {

  @Test
  void startsWith() {
    assertThat(Strings.startsWith(null, null))
        .isTrue();

    assertThat(Strings.startsWith("a", null))
        .isFalse();

    assertThat(Strings.startsWith(null, "a"))
        .isFalse();

    assertThat(Strings.startsWith("abc", "a"))
        .isTrue();
    assertThat(Strings.startsWith("abc", "ab"))
        .isTrue();
    assertThat(Strings.startsWith("abc", "abd"))
        .isFalse();
  }

  @Test
  void startsWithIgnoreCase() {
    assertThat(Strings.startsWithIgnoreCase(null, null))
        .isTrue();

    assertThat(Strings.startsWithIgnoreCase("a", null))
        .isFalse();

    assertThat(Strings.startsWithIgnoreCase(null, "a"))
        .isFalse();

    assertThat(Strings.startsWithIgnoreCase("abc", "A"))
        .isTrue();
    assertThat(Strings.startsWithIgnoreCase("abc", "aB"))
        .isTrue();
    assertThat(Strings.startsWithIgnoreCase("abc", "aBd"))
        .isFalse();
  }

  @Test
  void endsWith() {
    assertThat(Strings.endsWith(null, null))
        .isTrue();

    assertThat(Strings.endsWith("a", null))
        .isFalse();

    assertThat(Strings.endsWith(null, "a"))
        .isFalse();

    assertThat(Strings.endsWith("abc", "c"))
        .isTrue();
    assertThat(Strings.endsWith("abc", "bc"))
        .isTrue();
    assertThat(Strings.endsWith("abc", "abd"))
        .isFalse();
  }

  @Test
  void endsWithIgnoreCase() {
    assertThat(Strings.endsWithIgnoreCase(null, null))
        .isTrue();

    assertThat(Strings.endsWithIgnoreCase("a", null))
        .isFalse();

    assertThat(Strings.endsWithIgnoreCase(null, "a"))
        .isFalse();

    assertThat(Strings.endsWithIgnoreCase("abc", "C"))
        .isTrue();
    assertThat(Strings.endsWithIgnoreCase("abc", "bC"))
        .isTrue();
    assertThat(Strings.endsWithIgnoreCase("abc", "aBd"))
        .isFalse();
  }

  @Test
  void concat() {
    assertThat(Strings.concat(null)).isEqualTo("null");
    assertThat(Strings.concat(Strings.EMPTY)).isEmpty();
    assertThat(Strings.concat(Strings.EMPTY, Strings.EMPTY)).isEmpty();

    assertThat(Strings.concat(Strings.EMPTY, "a", "b", null, "c")).isEqualTo("abnullc");
    assertThat(Strings.concat("hello", " ", "w", "orl", "D")).isEqualTo("hello worlD");
  }

  @Test
  void concatLines() {
    var lines2 = String.join("\n", "a", "b");
    var lines12 = String.join("\n", "c", "d");
    var lines1 = String.join("\n", "e");
    var lines3 = String.join("\n", "f", "g", "h");

    assertThat(Strings.concatLines("-", lines2, lines12))
        .isEqualTo("a-c\nb-d");
    assertThat(Strings.concatLines("-", lines2, lines1))
        .isEqualTo("a-e\nb");
    assertThat(Strings.concatLines("-", lines2, lines3))
        .isEqualTo("a-f\nb-g\nh");
    assertThat(Strings.concatLines("-", lines1, lines2))
        .isEqualTo("e-a\nb");
    assertThat(Strings.concatLines("-", lines3, lines2))
        .isEqualTo("f-a\ng-b\nh");
  }
}