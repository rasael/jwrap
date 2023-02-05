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

import java.util.function.Predicate;

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

  @Test
  void containsHtml5Tags() {
    assertThat(CharSequences.containsHtml5Tags("""
        """)).isFalse();
    assertThat(CharSequences.containsHtml5Tags("""
        </>""")).isFalse();
    assertThat(CharSequences.containsHtml5Tags("""
        Bla </> </>""")).isFalse();
    assertThat(CharSequences.containsHtml5Tags("""
        hello""")).isFalse();

    assertThat(CharSequences.containsHtml5Tags("""
        if (i<a && c>3)""")).isFalse();

    assertThat(CharSequences.containsHtml5Tags("<b>hello</B>")).isTrue();

    assertThat((Predicate<CharSequence>) CharSequences::containsHtml5Tags)
        .rejects("a","<a>","< a/>","some <text>",
            "some <text attribute='<br/>'>",
            "some <text attribute=\"<br/>\">")
        .accepts(
            "<br />",
            "<br/>",
            "<b>hello</B>",
            "<b href='bla'>hello</b>",
            "some <text attribute></text>",
            "some <text attribute ></text>",
            "some <text attribute ></tEXt >",
            "some <text attribute='hello' ></text >",
            "some <text attribute=\"hello\" ></text >"
        );
  }

  @Test
  void isJavaIdentifier() {
    assertThat((Predicate<CharSequence>) CharSequences::isJavaIdentifier)
        .accepts("a","a1","A1","a$1","a_1","_1")
        .rejects(" a","a a", "1b", "test,test","a.b.c");
  }
}