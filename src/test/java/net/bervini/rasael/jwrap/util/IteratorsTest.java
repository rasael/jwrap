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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IteratorsTest {

  @Test
  void filter() {
    // GIVEN
    var iterator = List.of(1,2,3).iterator();

    // WHEN
    var actual = Iterators.filter(iterator, i -> i==2);

    // THEN
    assertThat(actual).toIterable().singleElement().isEqualTo(2);
  }

  @Test
  void filterLast() {
    // GIVEN
    var iterator = List.of(1,2,3).iterator();

    // WHEN
    var actual = Iterators.filter(iterator, i -> i==3);

    // THEN
    assertThat(actual).toIterable().singleElement().isEqualTo(3);
  }

  @Test
  void filterFirst() {
    // GIVEN
    var iterator = List.of(1,2,3).iterator();

    // WHEN
    var actual = Iterators.filter(iterator, i -> i==1);

    // THEN
    assertThat(actual).toIterable().singleElement().isEqualTo(1);
  }

  @Test
  void filterNone() {
    // GIVEN
    var iterator = List.of(1,2,3).iterator();

    // WHEN
    var actual = Iterators.filter(iterator, i -> i==4);

    // THEN
    assertThat(actual).isExhausted();
  }


  @Test
  void append_to_empty_iterator() {
    // GIVEN
    var iterator = Iterators.empty();

    // WHEN
    var actual = Iterators.append(iterator, "a");

    // THEN
    assertThat(actual).toIterable().singleElement().isEqualTo("a");
  }

  @Test
  void append_to_iterator() {
    // GIVEN
    var iterator = List.of("a","b").iterator();

    // WHEN
    var actual = Iterators.append(iterator, "c");

    // THEN
    assertThat(actual).toIterable().containsExactly("a","b","c");
  }

  @Test
  void prepend_to_empty_iterator() {
    // GIVEN
    var iterator = Iterators.empty();

    // WHEN
    var actual = Iterators.prepend(iterator, "a");

    // THEN
    assertThat(actual).toIterable().singleElement().isEqualTo("a");
  }

  @Test
  void prepend_to_iterator() {
    // GIVEN
    var iterator = List.of("a","b").iterator();

    // WHEN
    var actual = Iterators.prepend(iterator, "c");

    // THEN
    assertThat(actual).toIterable().containsExactly("c","a","b");
  }
}
