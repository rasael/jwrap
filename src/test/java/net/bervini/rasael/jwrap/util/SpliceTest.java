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

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpliceTest {


  @Test
  void testArray() {
    var data = newArray();
    assertThat(Splice.splice(data, 0).result()).isEmpty();
    assertThat(Splice.splice(data, 1).result()).containsExactly("a");
    assertThat(Splice.splice(data, 2).result()).containsExactly("a","b");
    assertThat(Splice.splice(data, 3).result()).containsExactly("a","b","c");
    assertThat(Splice.splice(data, 4).result()).containsExactly("a","b","c");

    assertThat(Splice.splice(data, -1).result()).containsExactly("a","b");
    assertThat(Splice.splice(data, -2).result()).containsExactly("a");
    assertThat(Splice.splice(data, -3).result()).isEmpty();
    assertThat(Splice.splice(data, -4).result()).isEmpty();
  }

  @Test
  void testList() {
    var data = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
    assertThat(Splice.splice(data, 0).result()).isEmpty();
    assertThat(Splice.splice(data, 1).result()).containsExactly("a");
    assertThat(Splice.splice(data, 2).result()).containsExactly("a","b");
    assertThat(Splice.splice(data, 3).result()).containsExactly("a","b","c");
    assertThat(Splice.splice(data, 4).result()).containsExactly("a","b","c");

    assertThat(Splice.splice(data, -1).result()).containsExactly("a","b");
    assertThat(Splice.splice(data, -2).result()).containsExactly("a");
    assertThat(Splice.splice(data, -3).result()).isEmpty();
    assertThat(Splice.splice(data, -4).result()).isEmpty();
  }

  @Test
  void spliceToList() {
    var data = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
    assertThat(Splice.splice(data, 0).removed()).containsExactly("a","b","c");
    assertThat(Splice.splice(data, 1).removed()).containsExactly("b","c");
    assertThat(Splice.splice(data, 2).removed()).containsExactly("c");
    assertThat(Splice.splice(data, 3).removed()).isEmpty();
    assertThat(Splice.splice(data, 4).removed()).isEmpty();
  }

  @Test
  void splice() {
    assertThat(Splice.splice(newArray(), 0, -1).result())
        .containsExactly("a","b","c");
    assertThat(Splice.splice(newArray(), 0, 0).result())
        .containsExactly("a","b","c");
    assertThat(Splice.splice(newArray(), 0, 1).result())
        .containsExactly("b","c");
    assertThat(Splice.splice(newArray(), 0, 2).result())
        .containsExactly("c");
    assertThat(Splice.splice(newArray(), 0, 3).result())
        .isEmpty();
    assertThat(Splice.splice(newArray(), 0, 4).result())
        .isEmpty();
  }

  @Test
  void spliceList() {
    var none = Collections.<String>emptyList();
    assertThat(Splice.splice(newList(), 0, -1, none).result())
        .containsExactly("a","b","c");
    assertThat(Splice.splice(newList(), 0, 0, none).result())
        .containsExactly("a","b","c");
    assertThat(Splice.splice(newList(), 0, 1, none).result())
        .containsExactly("b","c");
    assertThat(Splice.splice(newList(), 0, 2, none).result())
        .containsExactly("c");
    assertThat(Splice.splice(newList(), 0, 3, none).result())
        .isEmpty();
    assertThat(Splice.splice(newList(), 0, 4, none).result())
        .isEmpty();
  }

  private List<String> newList() {
    return new ArrayList<>(Arrays.asList(newArray()));
  }

  @NotNull
  private String[] newArray() {
    return new String[]{"a", "b", "c"};
  }

  private static final List<String> NULL_LIST = null;
  private static final List<String> EMPTY_LIST = java.util.Arrays.asList();
  @Test
  void testSpliceList() {
    // Splicing null results in a new empty list
    assertThat(Splice.splice(NULL_LIST))
        .isNotNull()
        .isEmpty();

    // Splicing empty list results in a new empty list
    assertThat(Splice.splice(EMPTY_LIST))
        .isNotNull()
        .isNotSameAs(EMPTY_LIST)
        .isEmpty();

    var list = Lists.newList("a","b","c","d");
    var result = Splice.splice(list);

    assertThat(result)
        .hasSize(4)
        .containsExactlyElementsOf(list);
  }

  @Test
  void testSpliceListWithStart() {
    // Splicing null results in a new empty list
    assertThat(Splice.splice(NULL_LIST, 0).result()).isNull();
    assertThat(Splice.splice(NULL_LIST, 0).removed()).isEmpty();

    assertThat(Splice.splice(EMPTY_LIST, 0).result()).isEmpty();
    assertThat(Splice.splice(EMPTY_LIST, 0).removed()).isEmpty();

    var list = Lists.newList("a","b","c","d");
    var spliced = Splice.splice(list, 1);
    assertThat(spliced.result())
        .containsExactly("a")
        .isNotSameAs(list);

    assertThat(spliced.removed())
        .containsExactly("b","c","d");
  }

  @Test
  void testSpliceListWithNegativeStart() {
    // Splicing null results in a new empty list
    assertThat(Splice.splice(NULL_LIST, -1).result()).isNull();
    assertThat(Splice.splice(NULL_LIST, -1).removed()).isEmpty();

    assertThat(Splice.splice(EMPTY_LIST, -1).result()).isEmpty();
    assertThat(Splice.splice(EMPTY_LIST, -1).removed()).isEmpty();

    var list = Lists.newList("a","b","c","d");
    var spliced = Splice.splice(list, -1);
    assertThat(spliced.result()).containsExactly("a","b","c").isNotSameAs(list);
    assertThat(spliced.removed()).containsExactly("d");
  }

  @Test
  void spliceTo() {
  }

  @Test
  void testSplice2() {
  }

  @Test
  void testSplice3() {
  }
}