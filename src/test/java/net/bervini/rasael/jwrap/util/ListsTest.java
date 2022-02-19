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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

  @Test
  void addAllWithVarArgs() {
    assertThatCode(() -> Lists.addAll(null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.addAll(null, "a","b","c")).doesNotThrowAnyException();
    assertThatCode(() -> Lists.addAll(Arrays.asList("a","b","c"), (String[])null)).doesNotThrowAnyException();

    var list = Arrays.toList("a","b","c");
    Lists.addAll(list, "d","e","f");
    assertThat(list).containsExactly("a","b","c","d","e","f");
  }

  @Test
  void addAlWithIterable() {
    assertThatCode(() -> Lists.addAll(null, (Iterable)null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.addAll(null, Arrays.asList("a","b","c"))).doesNotThrowAnyException();
    assertThatCode(() -> Lists.addAll(Arrays.asList("a","b","c"), (Iterable)null)).doesNotThrowAnyException();

    var list = Arrays.toList("a","b","c");
    var toAdd = Arrays.toList("d","e","f");

    Lists.addAll(list, toAdd);
    assertThat(list).containsExactly("a","b","c","d","e","f");
  }

  @Test
  void fill() {
    assertThatCode(() -> Lists.fill(null, null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.fill(null, "a")).doesNotThrowAnyException();
    assertThatCode(() -> Lists.fill(new ArrayList<>(), null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.fill(Arrays.asList(), null)).doesNotThrowAnyException();

    var list = Arrays.toList("a","b","c");
    Lists.fill(list, "d");
    assertThat(Predicate.isEqual("d")).acceptsAll(list);
  }

  @Test
  void set() {
    assertThatCode(() -> Lists.set(null, -1, null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.set(null, 0, null)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.set(null, 1, "a")).doesNotThrowAnyException();


    var list = Arrays.toList("a","b","c");
    assertThat(Lists.set(list, 0, "d")).isEqualTo("a");
    assertThat(list).containsExactly("d","b","c");

    Lists.set(list, -1, null);
    assertThat(list).containsExactly("d","b","c");

    Lists.set(list, 10, null);
    assertThat(list).containsExactly("d","b","c");

    assertThat(Lists.set(list, 2, "a")).isEqualTo("c");
    assertThat(Lists.set(list, 0, "c")).isEqualTo("d");
    assertThat(list).containsExactly("c","b","a");
  }

  @Test
  void get() {
    assertThatCode(() -> Lists.get(null, -1)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.get(null, 0)).doesNotThrowAnyException();
    assertThatCode(() -> Lists.get(null, 1)).doesNotThrowAnyException();

    assertThat(Lists.get(Arrays.toList("a","b","c"), -1)).isNull();
    assertThat(Lists.get(Arrays.toList("a","b","c"), 0)).isEqualTo("a");
    assertThat(Lists.get(Arrays.toList("a","b","c"), 1)).isEqualTo("b");
    assertThat(Lists.get(Arrays.toList("a","b","c"), 2)).isEqualTo("c");
    assertThat(Lists.get(Arrays.toList("a","b","c"), 3)).isNull();
  }

  @Test
  void isListIndexValid() {
    assertThat(Lists.isListIndexValid(null, -1)).isFalse();
    assertThat(Lists.isListIndexValid(null, 0)).isFalse();
    assertThat(Lists.isListIndexValid(null, 1)).isFalse();

    assertThat(Lists.isListIndexValid(Lists.empty(), -1)).isFalse();
    assertThat(Lists.isListIndexValid(Lists.empty(), 0)).isFalse();
    assertThat(Lists.isListIndexValid(Lists.empty(), 1)).isFalse();

    assertThat(Lists.isListIndexValid(Arrays.toList("a"), -1)).isFalse();
    assertThat(Lists.isListIndexValid(Arrays.toList("a"), 0)).isTrue();
    assertThat(Lists.isListIndexValid(Arrays.toList("a"), 1)).isFalse();
  }

  @Test
  void listcopy() {
    assertThatCode(() -> Lists.listcopy(null, 0, null, 0, 0))
        .doesNotThrowAnyException();
  }

  @Test
  void testToString() {
    assertThat(Lists.toString(null))
        .isNull();

    assertThat(Lists.toString(Arrays.asList("a","b","c")))
        .isEqualTo(Arrays.asList("a","b","c").toString());
  }

  @Test
  void at() {
    List<String> list = Arrays.asList("a", "b", "c");
    assertThat(Lists.at(list, 0)).isEqualTo("a");
    assertThat(Lists.at(list, 1)).isEqualTo("b");
    assertThat(Lists.at(list, 2)).isEqualTo("c");
    assertThat(Lists.at(list, 3)).isNull();
    assertThat(Lists.at(list, -1)).isEqualTo("c");
    assertThat(Lists.at(list, -2)).isEqualTo("b");
    assertThat(Lists.at(list, -3)).isEqualTo("a");
    assertThat(Lists.at(list, -4)).isNull();

  }

  @Test
  void indexOf() {
    assertThat(Lists.indexOf(null, null)).isEqualTo(-1);
    assertThat(Lists.indexOf(null, "a")).isEqualTo(-1);
    assertThat(Lists.indexOf(Arrays.asList("c","a"), "a")).isEqualTo(1);
    assertThat(Lists.indexOf(Arrays.asList("c","a", null), null)).isEqualTo(2);
  }

  @Test
  void contains() {
    assertThat(Lists.contains(null, null, 0)).isFalse();
    assertThat(Lists.contains(null, null, 0)).isFalse();

    var list = Arrays.asList("a", null);
    assertThat(Lists.contains(list, "a", -1)).isFalse();
    assertThat(Lists.contains(list, "a", 0)).isTrue();
    assertThat(Lists.contains(list, "a", 1)).isFalse();

    assertThat(Lists.contains(list, null, -1)).isFalse();
    assertThat(Lists.contains(list, null, 0)).isFalse();
    assertThat(Lists.contains(list, null, 1)).isTrue();


    var listOfIntArray = Arrays.asList(new int[]{1,2,3});
    assertThat(Lists.contains(listOfIntArray, new int[]{3,2,1}, 0)).isFalse();
    assertThat(Lists.contains(listOfIntArray, new int[]{1,2,3}, 0)).isTrue();
  }

  @Test
  void doesNotContain() {
    assertThat(Lists.doesNotContain(NULL_LIST, null, 0)).isTrue();
    assertThat(Lists.doesNotContain(NULL_LIST, null, 0)).isTrue();

    var list = Arrays.asList("a", null);
    assertThat(Lists.doesNotContain(list, "a", -1)).isTrue();
    assertThat(Lists.doesNotContain(list, "a", 0)).isFalse();
    assertThat(Lists.doesNotContain(list, "a", 1)).isTrue();

    assertThat(Lists.doesNotContain(list, null, -1)).isTrue();
    assertThat(Lists.doesNotContain(list, null, 0)).isTrue();
    assertThat(Lists.doesNotContain(list, null, 1)).isFalse();


    var listOfIntArray = Arrays.asList(new int[]{1,2,3});
    assertThat(Lists.doesNotContain(listOfIntArray, new int[]{3,2,1}, 0)).isTrue();
    assertThat(Lists.doesNotContain(listOfIntArray, new int[]{1,2,3}, 0)).isFalse();
  }

  @Test
  void push() {
    assertThatCode(() -> Lists.pushAll(NULL_LIST, null)).doesNotThrowAnyException();

    var list = new ArrayList<String>();
    Lists.push(list, "a");
    assertThat(list).containsExactly("a");

    var set = new LinkedHashSet<String>(){
      {
        add("b");
        add("c");
      }
    };
    Lists.pushAll(list, set);
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  void pop() {
    assertThat(Lists.pop(NULL_LIST)).isNull();
    assertThat(Lists.pop(EMPTY_LIST)).isNull();

    var list = Arrays.toList("a","b");
    assertThat(Lists.pop(list)).isEqualTo("b");
    assertThat(list).containsExactly("a");
  }

  @Test
  void shift() {
    assertThat(Lists.shift(NULL_LIST)).isNull();
    assertThat(Lists.shift(new ArrayList<String>())).isNull();

    var list = Arrays.toList("a","b");

    Lists.shift(list);
    assertThat(list).containsExactly("b");

    Lists.shift(list);
    assertThat(list).isEmpty();
  }

  @Test
  void unshift() {
    assertThat(Lists.unshift(NULL_LIST, "a")).containsExactly("a");
    assertThat(Lists.unshift(new ArrayList<>(), "a")).containsExactly("a");

    var list = Lists.unshift(null, "c");
    assertThat(list).containsExactly("c");

    Lists.unshift(list, "b");
    assertThat(list).containsExactly("b","c");

    Lists.unshift(list, "a");
    assertThat(list).containsExactly("a","b","c");
  }

  @Test
  void remove() {
    assertThat(Lists.remove(NULL_LIST, -1)).isNull();
    assertThat(Lists.remove(NULL_LIST, 0)).isNull();
    assertThat(Lists.remove(NULL_LIST, 1)).isNull();

    assertThat(Lists.remove(EMPTY_LIST, -1)).isNull();
    assertThat(Lists.remove(EMPTY_LIST, 0)).isNull();
    assertThat(Lists.remove(EMPTY_LIST, 1)).isNull();

    var list = Lists.newList("a","b","c");
    assertThat(Lists.remove(list, -1)).isNull();
    assertThat(list).containsExactly("a","b","c");

    assertThat(Lists.remove(list, 0)).isEqualTo("a");
    assertThat(list).containsExactly("b","c");

    assertThat(Lists.remove(list, 1)).isEqualTo("c");
    assertThat(list).containsExactly("b");
  }

  @Test
  void testClone() {
    assertThat(Lists.clone(NULL_LIST)).isNull();
    assertThat(Lists.clone(NULL_FILLED_LIST)).containsExactlyElementsOf(NULL_FILLED_LIST);

    var list = Lists.newList("a", "b", "c");
    var cloned = Lists.clone(list);
    assertThat(cloned).containsExactlyElementsOf(list);
    list.add("d");

    assertThat(cloned).isNotEqualTo(list);
  }
}
