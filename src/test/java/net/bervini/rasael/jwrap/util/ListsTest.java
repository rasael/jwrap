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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListsTest {

  private static final List<String> NULL_LIST = null;
  private static final List<String> EMPTY_LIST = java.util.Collections.emptyList();
  private static final List<String> NULL_FILLED_LIST = java.util.Collections.unmodifiableList(java.util.Arrays.asList(null, null));

  @Test
  void newListWithIterable() {
    Iterable<String> iterable = Arrays.asList("a", "b", "c");
    assertThat(Lists.newList(iterable)).containsExactlyElementsOf(iterable);
  }

  @Test
  void getFirst() {
    assertThat(Lists.getFirst(NULL_LIST)).isNull();
    assertThat(Lists.getFirst(EMPTY_LIST)).isNull();
    assertThat(Lists.getFirst(NULL_FILLED_LIST)).isNull();
    
    assertThat(Lists.getFirst(Arrays.asList("a","b"))).isEqualTo("a");
  }

  @Test
  void getLast() {
    assertThat(Lists.getLast(NULL_LIST)).isNull();
    assertThat(Lists.getLast(EMPTY_LIST)).isNull();
    assertThat(Lists.getLast(NULL_FILLED_LIST)).isNull();

    assertThat(Lists.getLast(Arrays.asList("a","b"))).isEqualTo("b");
  }
  @Test
  void singleton() {
    assertThat(Lists.singleton(null)).containsExactly(new Object[]{null});
    assertThat(Lists.singleton("a")).containsExactly("a");
  }

  @Test
  void empty() {
    assertThat(Lists.empty()).isEmpty();
  }
}