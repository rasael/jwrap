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

class StringWrapTest {

  private static final String NULL_STRING = null;
  private static final String EMPTY_STRING = "";
  private static final String BLANK_STRING = " ";
  private static final String NON_BLANK_STRING = "aBc";

  @Test
  void isEmpty() {
    assertThat($(NULL_STRING).isEmpty())
        .isTrue();

    assertThat($(EMPTY_STRING).isEmpty())
        .isTrue();

    assertThat($(BLANK_STRING).isEmpty())
        .isFalse();
  }
  @Test
  void isBlank() {
    assertThat($(NULL_STRING).isBlank())
        .isTrue();

    assertThat($(EMPTY_STRING).isBlank())
        .isTrue();

    assertThat($(BLANK_STRING).isBlank())
        .isTrue();

    assertThat($(NON_BLANK_STRING).isBlank())
        .isFalse();
  }

  @Test
  void isNullOrEmpty() {
    assertThat($(EMPTY_STRING).isNullOrEmpty())
        .isTrue();
    assertThat($(NULL_STRING).isNullOrEmpty())
        .isTrue();
    assertThat($(BLANK_STRING).isNullOrEmpty())
        .isFalse();
    assertThat($(NON_BLANK_STRING).isNullOrEmpty())
        .isFalse();
  }

  @Test
  void endsWithIgnoreCase() {
    assertThat($("hello").endsWithIgnoreCase("o"))
        .isTrue();
    assertThat($("hello").endsWithIgnoreCase(new StringBuilder("O")))
        .isTrue();
    assertThat($(NULL_STRING).endsWithIgnoreCase(null)).isTrue();

    assertThat($("hello").endsWithIgnoreCase("HE")).isFalse();
  }

  @Test
  void startsWithIgnoreCase() {
    assertThat($("hello").startsWithIgnoreCase("h"))
        .isTrue();
    assertThat($("hello").startsWithIgnoreCase(new StringBuilder("HELL")))
        .isTrue();

    assertThat($("hello").startsWithIgnoreCase("LO")).isFalse();

    assertThat($(NULL_STRING).startsWithIgnoreCase(null)).isTrue();
  }


  @Test
  void isStartOf() {
    assertThat($("prefix").isStartOf(null)).isFalse();
    assertThat($(NULL_STRING).isStartOf(null)).isTrue();
    assertThat($(NULL_STRING).isStartOf("")).isFalse();

    // Empty string is always the start of anything besides null
    assertThat($(EMPTY_STRING).isStartOf(null)).isFalse();
    assertThat($(EMPTY_STRING).isStartOf(EMPTY_STRING)).isTrue();
    assertThat($(EMPTY_STRING).isStartOf("something")).isTrue();

    assertThat($("prefix").isStartOf("")).isFalse();
    assertThat($("prefix").isStartOf("word")).isFalse();
    assertThat($("pre").isStartOf("prefix")).isTrue();
    assertThat($("prefix").isStartOf("prefix")).isTrue();
    assertThat($("prefix").isStartOf("prefixed")).isTrue();
  }

  @Test
  void trims() {
    assertThat($(NULL_STRING).trim().get()).isNull();
    assertThat($(NULL_STRING).trimToEmpty().get()).isEmpty();
    assertThat($(NULL_STRING).trimToNull().get()).isNull();

    assertThat($(EMPTY_STRING).trim().get()).isEmpty();
    assertThat($(EMPTY_STRING).trimToEmpty().get()).isEmpty();
    assertThat($(EMPTY_STRING).trimToNull().get()).isNull();

    assertThat($(BLANK_STRING).trim().get()).isEmpty();
    assertThat($(BLANK_STRING).trimToEmpty().get()).isEmpty();
    assertThat($(BLANK_STRING).trimToNull().get()).isNull();

    assertThat($(NON_BLANK_STRING).trim().get()).isEqualTo(NON_BLANK_STRING);
    assertThat($(NON_BLANK_STRING).trimToEmpty().get()).isEqualTo(NON_BLANK_STRING);
    assertThat($(NON_BLANK_STRING).trimToNull().get()).isEqualTo(NON_BLANK_STRING);
  }

  @Test
  void isEndOf() {
    assertThat($("prefix").isEndOf(null)).isFalse();
    assertThat($(NULL_STRING).isEndOf(null)).isTrue();
    assertThat($(NULL_STRING).isEndOf("")).isFalse();

    // Empty string is always the start of anything besides null
    assertThat($(EMPTY_STRING).isEndOf(null)).isFalse();
    assertThat($(EMPTY_STRING).isEndOf(EMPTY_STRING)).isTrue();
    assertThat($(EMPTY_STRING).isEndOf("something")).isTrue();

    assertThat($("prefix").isEndOf("")).isFalse();
    assertThat($("prefix").isEndOf("word")).isFalse();
    assertThat($("prefix").isEndOf("prefix")).isTrue();
    assertThat($("fix").isEndOf("prefix")).isTrue();
  }

  @Test
  void reversed() {
    assertThat($(NULL_STRING).reversed().isNull())
        .isTrue();

    assertThat($(NULL_STRING).reversed().get())
        .isNull();

    assertThat($("rasael").reversed().get())
        .isEqualTo("leasar");

    assertThat($("rasael").reversed().reversed().get())
        .isEqualTo("rasael");

    assertThat($("rasael").reversed().isEqualTo("leasar"))
        .isTrue();
  }

  @Test
  void toJson() {
    assertThat($("rasael").json()).isEqualTo("\"rasael\"");
  }

  @Test
  void split() {
    assertThat($("a b c").split().get())
        .containsExactly("a", "b", "c");

    assertThat($("a b c").split().hasSize(3))
        .isTrue();
  }

  @Test
  void splitWithCharSeparator() {
    assertThat($("a b,c").split(',').hasSize(2))
        .isTrue();

    assertThat($("a b,c").split(',').get())
        .containsExactly("a b", "c");
  }

  @Test
  void splitByCharacterType() {
    assertThat($("hello.Wor123").splitByCharacterType().hasSize(5))
        .isTrue();

    assertThat($("hello.Wor123").splitByCharacterType().get())
        .containsExactly("hello",".","W","or","123");
  }

  @Test
  void splitCamelCase() {
    assertThat($("helloWorld").splitByCamelCase().hasSize(2))
        .isTrue();

    assertThat($("helloWorld").splitByCamelCase().get())
        .containsExactly("hello","World");
  }

  @Test
  void splitQuoted() {
    assertThat($("hello wo \"rl d\"").splitQuoted())
        .containsExactly("hello","wo","\"rl d\"");

    assertThat($("hello wo \"rl d\"").splitQuoted('\''))
        .containsExactly("hello","wo", "\"rl", "d\"");

    assertThat($("hello wo 'rl d'").splitQuoted('\''))
        .containsExactly("hello","wo","'rl d'");

    assertThat($("hello wo |rl d|").splitQuoted('|'))
        .containsExactly("hello","wo","|rl d|");
  }
}
