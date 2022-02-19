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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    assertThat(Iterables.allMatch(Arrays.asList(""," ","\n"), String::isBlank))
        .isTrue();

    assertThat(Iterables.allMatch(Arrays.asList("","x","\n"), String::isBlank))
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

    assertThat(Iterables.anyMatch(Arrays.asList("x","y","z"), String::isBlank))
        .isFalse();

    assertThat(Iterables.anyMatch(Arrays.asList(""," ","\n"), String::isBlank))
        .isTrue();

    assertThat(Iterables.anyMatch(Arrays.asList("","x","\n"), String::isBlank))
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

    assertThat(Iterables.noneMatch(Arrays.asList("x","y","z"), String::isBlank))
        .isTrue();

    assertThat(Iterables.noneMatch(Arrays.asList(""," ","\n"), String::isBlank))
        .isFalse();

    assertThat(Iterables.noneMatch(Arrays.asList("","x","\n"), String::isBlank))
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

    assertThat(Iterables.size(() -> Arrays.asList("a").iterator())).isOne();
    assertThat(Iterables.size(() -> Arrays.asList("a","b").iterator())).isEqualTo(2);

    assertThat(Iterables.size(Iterables.empty())).isZero();

    var iterable = new HashSet<Object>(0);
    assertThat(Iterables.size(Iterables.like(iterable))).isZero();
  }

  @Test
  void isEmpty() {
    assertThat(Iterables.isEmpty(null)).isTrue();
    assertThat(Iterables.isEmpty(Collections.emptyList())).isTrue();
    assertThat(Iterables.isEmpty(() -> Collections.emptyList().iterator())).isTrue();

    assertThat(Iterables.isEmpty(() -> Arrays.asList("a").iterator())).isFalse();
    assertThat(Iterables.isEmpty(() -> Arrays.asList("a","b").iterator())).isFalse();

    assertThat(Iterables.isEmpty(Iterables.empty())).isTrue();

    var iterable = new HashSet<Object>(0);
    assertThat(Iterables.isEmpty(Iterables.like(iterable))).isTrue();
  }
}