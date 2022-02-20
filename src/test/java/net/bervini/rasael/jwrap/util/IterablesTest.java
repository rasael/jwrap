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

import java.util.HashSet;
import java.util.List;

import static net.bervini.rasael.jwrap.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;


class IterablesTest {

  static <T> Iterable<T> emptyIterable() {
    return Collections.emptySet();
  }

  static <T> Iterable<T> nullIterable() {
    return null;
  }

  static Iterable<String> singletonIterable() {
    return Collections.singleton("a");
  }

  @Test
  void allMatch() {
    assertThat(Iterables.allMatch(nullIterable(), null))
        .isFalse();
    assertThat(Iterables.allMatch(emptyIterable(), null))
        .isFalse();
    assertThat(Iterables.allMatch(singletonIterable(), null))
        .isFalse();

    assertThat(Iterables.allMatch(asList(""," ","\n"), String::isBlank))
        .isTrue();

    assertThat(Iterables.allMatch(asList("","x","\n"), String::isBlank))
        .isFalse();
  }

  @Test
  void anyMatch() {
    assertThat(Iterables.anyMatch(nullIterable(), null))
        .isFalse();
    assertThat(Iterables.anyMatch(emptyIterable(), null))
        .isFalse();
    assertThat(Iterables.anyMatch(singletonIterable(), null))
        .isFalse();

    assertThat(Iterables.anyMatch(asList("x","y","z"), String::isBlank))
        .isFalse();

    assertThat(Iterables.anyMatch(asList(""," ","\n"), String::isBlank))
        .isTrue();

    assertThat(Iterables.anyMatch(asList("","x","\n"), String::isBlank))
        .isTrue();
  }

  @Test
  void noneMatch() {
    assertThat(Iterables.noneMatch(nullIterable(), null))
        .isTrue();
    assertThat(Iterables.noneMatch(emptyIterable(), null))
        .isTrue();
    assertThat(Iterables.noneMatch(singletonIterable(), null))
        .isTrue();

    assertThat(Iterables.noneMatch(asList("x","y","z"), String::isBlank))
        .isTrue();

    assertThat(Iterables.noneMatch(asList(""," ","\n"), String::isBlank))
        .isFalse();

    assertThat(Iterables.noneMatch(asList("","x","\n"), String::isBlank))
        .isFalse();
  }


  @Test
  void test() {
    List<? super CharSequence> list = Lists.newList();
    list.add("a");
  }

  @Test
  void size() {
    assertThat(Iterables.size(null)).isZero();
    assertThat(Iterables.size(Collections.emptyList())).isZero();
    assertThat(Iterables.size(() -> Collections.emptyList().iterator())).isZero();

    assertThat(Iterables.size(() -> asList("a").iterator())).isOne();
    assertThat(Iterables.size(() -> asList("a","b").iterator())).isEqualTo(2);

    assertThat(Iterables.size(Iterables.empty())).isZero();

    var iterable = new HashSet<Object>(0);
    assertThat(Iterables.size(Iterables.like(iterable))).isZero();
  }

  @Test
  void isEmpty() {
    assertThat(Iterables.isEmpty(null)).isTrue();
    assertThat(Iterables.isEmpty(Collections.emptyList())).isTrue();
    assertThat(Iterables.isEmpty(() -> Collections.emptyList().iterator())).isTrue();

    assertThat(Iterables.isEmpty(() -> asList("a").iterator())).isFalse();
    assertThat(Iterables.isEmpty(() -> asList("a","b").iterator())).isFalse();

    assertThat(Iterables.isEmpty(Iterables.empty())).isTrue();

    var iterable = new HashSet<Object>(0);
    assertThat(Iterables.isEmpty(Iterables.like(iterable))).isTrue();

    // Calling forEach with null action must throw a NPE
    var empty = Iterables.empty();
    assertThatThrownBy(() -> empty.forEach(null))
        .isInstanceOf(NullPointerException.class);

    // Calling forEach with a non-null action must not throw any exception
    assertThatCode(() -> Iterables.empty().forEach(e -> fail("")))
        .doesNotThrowAnyException();
  }

  @Test
  void orEmpty() {
    assertThat(Iterables.orEmpty(null)).isEmpty();
    assertThat(Iterables.orEmpty(asList("a","b","c"))).containsExactly("a","b","c");
  }

  private static final Iterable<String> NULL_ITERABLE = null;
  private static final Iterable<String> EMPTY_ITERABLE = Iterables.empty();

  @Test
  void getFirst() {
    assertThat(Iterables.getFirst(NULL_ITERABLE)).isNull();
    assertThat(Iterables.getFirst(EMPTY_ITERABLE)).isNull();

    assertThat(Iterables.getFirst(asList("a","b"))).isEqualTo("a");
    assertThat(Iterables.getFirst(Arrays.toSet("a","b","a"))).isEqualTo("a");
  }

  @Test
  void getLast() {
    assertThat(Iterables.getLast(NULL_ITERABLE)).isNull();
    assertThat(Iterables.getLast(EMPTY_ITERABLE)).isNull();

    assertThat(Iterables.getLast(asList("a","b"))).isEqualTo("b");
    assertThat(Iterables.getLast(Arrays.toSet("a","b","a"))).isEqualTo("b");
  }

  @Test
  void contains() {
    assertThat(Iterables.contains(NULL_ITERABLE, null)).isFalse();
    assertThat(Iterables.contains(EMPTY_ITERABLE, null)).isTrue();
    assertThat(Iterables.contains(EMPTY_ITERABLE, EMPTY_ITERABLE)).isTrue();
    assertThat(Iterables.contains(EMPTY_ITERABLE, "")).isFalse();

    assertThat(Iterables.contains(asList("a","b","c"), asList("a"))).isTrue();
    assertThat(Iterables.contains(asList("a","b","c"), asList("b"))).isTrue();
    assertThat(Iterables.contains(asList("a","b","c"), asList("c"))).isTrue();

    assertThat(Iterables.contains(asList("a","b","c"), asList("a","b"))).isTrue();
    assertThat(Iterables.contains(asList("a","b","c"), asList("a","b","c"))).isTrue();
    assertThat(Iterables.contains(asList("a","b","c"), asList("b","c"))).isTrue();
    assertThat(Iterables.contains(asList("a","b","c"), asList("a","c"))).isTrue();

    assertThat(Iterables.contains(asList("a","b","c"), asList("d"))).isFalse();
    assertThat(Iterables.contains(asList("a","b","c"), asList("a","b","c","d"))).isFalse();
  }
}