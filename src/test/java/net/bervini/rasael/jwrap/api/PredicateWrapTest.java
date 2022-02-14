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

import net.bervini.rasael.jwrap.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class PredicateWrapTest {

  private <T> Predicate<T> nullPredicate() {
    return null;
  }

  @Test
  void test() {
    assertThat($(nullPredicate()).test(null)).isFalse();
    assertThat($(nullPredicate()).test(new Object())).isFalse();
  }

  @Test
  void accepts() {
    assertThat($(String::isBlank).accepts(""))
        .isTrue();

    assertThat($(String::isBlank).accepts("", " ", "\n", "\t"))
        .isTrue();

    assertThat($(String::isBlank).accepts("", "x", "\n", "\t"))
        .isFalse();

    assertThat($(String::isBlank).accepts())
        .isTrue();

    var samples = Arrays.newList("a", "", " ", "\n");

    assertThat($(String::isBlank).filter(samples))
        .containsExactly("", " ", "\n");

    samples = Arrays.newList("a", "", " ", "\n");
    assertThat($(String::isBlank).remove(samples))
        .containsExactly("a");
  }

  @Test
  void rejects() {
    assertThat($(String::isBlank).rejects("x"))
        .isTrue();

    assertThat($(String::isBlank).rejects("x", " ", "\n", "\t"))
        .isFalse();

    assertThat($(String::isBlank).rejects("", "x", "\n", "\t"))
        .isFalse();

    assertThat($(String::isBlank).rejects())
        .isTrue();
  }

  @Test
  void rejectsAny() {
    assertThat($(String::isBlank).rejectsAny("x"))
        .isTrue();

    assertThat($(String::isBlank).rejectsAny("x", " ", "\n", "\t"))
        .isTrue();

    assertThat($(String::isBlank).rejectsAny("", "x", "\n", "\t"))
        .isTrue();

    assertThat($(String::isBlank).rejectsAny())
        .isTrue();
  }

  @Test
  void whenAccepts() {
    AtomicInteger counter = new AtomicInteger();
    $(String::isBlank).whenAccepts("a").ifNotNull(t -> counter.incrementAndGet());
    assertThat(counter).hasValue(0);

    $(String::isBlank).whenAccepts("").ifNotNull(t -> counter.incrementAndGet());
    assertThat(counter).hasValue(1);
  }

  @Test
  void orTrue() {
    assertThat($(nullPredicate()).orTrue())
        .accepts("a", 1, null, new Object());

    assertThat($(String::isBlank).orTrue())
        .rejects("x");
  }

  @Test
  void orFalse() {
    assertThat($(nullPredicate()).orFalse())
        .rejects("a", 1, null, new Object());

    assertThat($(String::isBlank).orFalse())
        .accepts("");
  }

  @Test
  void not() {
    assertThat($(nullPredicate()).not())
        .isNotNull();
    assertThat($(nullPredicate()).orFalse().not().test(null))
        .isTrue();

    assertThat($(String::isBlank).not().test(""))
        .isFalse();
  }

}