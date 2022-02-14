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

import javax.annotation.Nullable;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class PredicatesTest {

  static <T> Predicate<T> nullPredicate() {
    return null;
  }

  private boolean isStringBlank(@Nullable String s) {
    return s!=null && s.isBlank();
  }

  @Test
  void rejects() {
    assertThat(Predicates.rejects(nullPredicate(), ""))
        .isTrue();
    assertThat(Predicates.rejects(this::isStringBlank, (String[])null))
        .isTrue();
    assertThat(Predicates.rejects(this::isStringBlank, (String)null))
        .isTrue();

    assertThat(Predicates.rejects(this::isStringBlank, ""))
        .isFalse();
    assertThat(Predicates.rejects(this::isStringBlank, "", " "))
        .isFalse();

    assertThat(Predicates.rejects(this::isStringBlank, "x"))
        .isTrue();
    assertThat(Predicates.rejects(this::isStringBlank, "x", "y"))
        .isTrue();
    assertThat(Predicates.rejects(this::isStringBlank, "x", " ", "y"))
        .isFalse();
    assertThat(Predicates.rejects(this::isStringBlank, "", " ", "x"))
        .isFalse(); // does not reject *all*
  }

  @Test
  void rejectsAny() {
    assertThat(Predicates.rejectsAny(nullPredicate(), ""))
        .isTrue();
    assertThat(Predicates.rejectsAny(this::isStringBlank, (String[])null))
        .isTrue();
    assertThat(Predicates.rejectsAny(this::isStringBlank, (String)null))
        .isTrue();

    assertThat(Predicates.rejectsAny(this::isStringBlank, ""))
        .isFalse();
    assertThat(Predicates.rejectsAny(this::isStringBlank, "", " "))
        .isFalse();

    assertThat(Predicates.rejectsAny(this::isStringBlank, "x"))
        .isTrue();
    assertThat(Predicates.rejectsAny(this::isStringBlank, "x", "y"))
        .isTrue();
    assertThat(Predicates.rejectsAny(this::isStringBlank, "x", " ", "y"))
        .isTrue();
    assertThat(Predicates.rejectsAny(this::isStringBlank, "", " ", "x"))
        .isTrue();
  }

  @Test
  void constants() {
    assertThat(Predicates.acceptAll())
        .accepts("a",1, null, new Object());

    assertThat(Predicates.rejectAll())
        .rejects("a",1, null, new Object());
  }
}
