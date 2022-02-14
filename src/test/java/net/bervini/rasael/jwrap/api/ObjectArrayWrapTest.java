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

import net.bervini.rasael.jwrap.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class ObjectArrayWrapTest {

  static String[] singletonArray() {
    return new String[]{"a"};
  }

  private static final String[] EMPTY_ARRAY = new String[0];
  private static final String[] NULL_ARRAY = null;

  @Test
  void contains() {
    String[] array = {"a","b","c"};

    assertThat($(array).contains("a")).isTrue();
    assertThat($(array).contains("a", "b")).isTrue();
    assertThat($(array).contains("a", "c")).isTrue();
    assertThat($(array).contains("c", "b")).isTrue();
    assertThat($(array).contains("a", "b", "c")).isTrue();
    assertThat($(array).contains("d")).isFalse();

    AtomicReferenceArray<String> ref = new AtomicReferenceArray<>(array);
    assertThat($(ref).contains("a")).isTrue();
    assertThat($(ref).contains("a", "b")).isTrue();
    assertThat($(ref).contains("a", "c")).isTrue();
    assertThat($(ref).contains("c", "b")).isTrue();
    assertThat($(ref).contains("a", "b", "c")).isTrue();
    assertThat($(ref).contains("d")).isFalse();

    assertThat($(array).contains(Lists.singleton("b"))).isTrue();
  }

  @Test
  void componentType() {
    assertThat($(NULL_ARRAY).componentType())
        .isNull();

    assertThat($(EMPTY_ARRAY).componentType())
        .isSameAs(EMPTY_ARRAY.getClass().getComponentType());
  }
  @Test
  void arrayToString() {
    String[] array = {"a","b","c"};

    assertThat($(NULL_ARRAY))
        .hasToString("null");

    assertThat($(EMPTY_ARRAY))
        .hasToString("[]");

    assertThat($(array))
        .hasToString(Arrays.toString(array));
  }

  @Test
  void sort() {
    String[] array = {"c","a","b"};
    assertThat($(array).sort().get()).containsExactly("a","b","c");
  }

  @Test
  void reverse() {
    String[] array = {"c","b","a"};
    assertThat($(array).reverse().get()).containsExactly("a","b","c");
    assertThat($(array).reverse().reverse().get()).containsExactly(array);
  }


  @Test
  void sortWithComparator() {
    String[] array = {"a","c","b"};
    assertThat($(array).sort(Comparator.reverseOrder()).get()).containsExactly("c","b","a");
  }

  @Test
  void indexOf() {
    String[] array = {"a","b","c"};

    assertThat($(NULL_ARRAY).indexOf(null)).isEqualTo(-1);
    assertThat($(EMPTY_ARRAY).indexOf(null)).isEqualTo(-1);

    assertThat($(new String[]{null}).indexOf(null)).isZero();

    assertThat($(array).indexOf("a")).isZero();
    assertThat($(array).indexOf("b")).isEqualTo(1);
    assertThat($(array).indexOf("c")).isEqualTo(2);
    assertThat($(array).indexOf("d")).isEqualTo(-1);
  }

  @Test
  void json() {
    String[] array = {"a","b","c"};
    assertThat($(array).json()).isEqualTo("""
        ["a","b","c"]""");
  }
  @Test
  void size() {
    String[] array = {"a","b","c"};
    assertThat($(array).size()).isEqualTo(3);
    assertThat($(singletonArray()).isSingleton()).isTrue();
    assertThat($(EMPTY_ARRAY).size()).isZero();
    assertThat($(NULL_ARRAY).size()).isZero();

    assertThat($(singletonArray()).isEmpty()).isFalse();
    assertThat($(EMPTY_ARRAY).isEmpty()).isTrue();
    assertThat($(NULL_ARRAY).isEmpty()).isTrue();
  }

  @Test
  void fill() {
    String[] array = {"a","b","c"};
    assertThat($(array).fill("z").get())
        .containsExactly("z","z","z");
  }

  @Test
  void clear() {
    String[] array = {"a","b","c"};
    assertThat($(array).clear().get())
        .hasSize(3)
        .allMatch(Objects::isNull);

    assertThat($(NULL_ARRAY).or(singletonArray())
                            .clear()
                            .get())
        .hasSize(1)
        .allMatch(Objects::isNull);
  }

  @Test
  void toList() {
    String[] array = {"a","b","c"};
    assertThat($(array).toList()).containsExactly(array);
  }

  @Test
  void shuffle() {
    // Important: this test works only with odd array sizes
    Character[] original = {'a','B','C'};
    Character[] array = original.clone();
    assertThat($(array).shuffle().get())
        .containsExactlyInAnyOrder(original);
  }

  @Test
  void copyViaSplice() {
    Character[] original = {'a','B','C'};
    assertThat($(original).splice().get())
        .isEqualTo(original)
        .isNotSameAs(original);
  }
  @Test
  void filter() {
    assertThat($(NULL_ARRAY).filter(String::isBlank).get())
        .isNull();

    assertThat($(EMPTY_ARRAY).filter(String::isBlank))
        .isEmpty();

    assertThat($(newAbcArray()).filter(String::isBlank))
        .isEmpty();
    assertThat($(newAbcArray()).concat(new String[]{"", " "})
                               .filter(String::isBlank))
        .containsExactly("", " ");
  }

  @Test
  void entries() {
    assertThat($(NULL_ARRAY).entries())
        .isEmpty();

    assertThat($(EMPTY_ARRAY).entries())
        .isEmpty();

    assertThat($(newAbcArray()).entries())
        .containsExactly(
            Map.entry(0, "a"),
            Map.entry(1, "b"),
            Map.entry(2, "c")
        );
  }

  @Test
  void removeIf() {
    assertThat($(NULL_ARRAY).removeIf(String::isBlank).get())
        .isNull();

    assertThat($(EMPTY_ARRAY).removeIf(String::isBlank))
        .isEmpty();

    assertThat($(newAbcArray()).removeIf(String::isBlank))
        .containsExactly(newAbcArray());

    assertThat($(newAbcArray()).concat(new String[]{"", " "})
                               .removeIf(String::isBlank))
        .containsExactly(newAbcArray());
  }
  @Test
  void first() {
    Character[] original = {'a', 'B', 'C'};
    assertThat($(original).first()).isSameAs(original[0]);
    assertThat($(NULL_ARRAY).first()).isNull();
    assertThat($(EMPTY_ARRAY).first()).isNull();
  }

  @Test
  void last() {
    Character[] original = {'a','B','C'};
    assertThat($(original).last()).isSameAs(original[2]);
    assertThat($(NULL_ARRAY).last()).isNull();
    assertThat($(EMPTY_ARRAY).last()).isNull();
  }

  @Test
  void count() {
    Character[] array = {'a','B','C'};
    assertThat($(array).count(Character::isUpperCase)).isEqualTo(2);
    assertThat($(array).count(Character::isLowerCase)).isEqualTo(1);

    var wrap = $(array);
    assertThat(wrap.reverse().count(Character::isUpperCase)).isEqualTo(2);
  }

  @Test
  void splice() {
    assertThat($(newAbcArray()).splice(0).get()).isEmpty();
    assertThat($(newAbcArray()).splice(1).get()).containsExactly("a");
    assertThat($(newAbcArray()).splice(2).get()).containsExactly("a","b");
    assertThat($(newAbcArray()).splice(3).get()).containsExactly("a","b","c");
    assertThat($(newAbcArray()).splice(4).get()).containsExactly("a","b","c");

    assertThat($(newAbcArray()).splice(-1).get()).containsExactly("a","b");
    assertThat($(newAbcArray()).splice(-2).get()).containsExactly("a");
    assertThat($(newAbcArray()).splice(-3).get()).isEmpty();
    assertThat($(newAbcArray()).splice(-4).get()).isEmpty();

    assertThat($(NULL_ARRAY).splice(0).get()).isNull();
  }

  @Test
  void splice2() {
    // From start 0 - delete count [-1..4]
    assertThat($(newAbcArray()).splice(0, -1).get())
        .containsExactly("a","b","c");
    assertThat($(newAbcArray()).splice(0, 0).get())
        .containsExactly("a","b","c");
    assertThat($(newAbcArray()).splice(0, 1).get())
        .containsExactly("b","c");
    assertThat($(newAbcArray()).splice(0, 2).get())
        .containsExactly("c");
    assertThat($(newAbcArray()).splice(0, 3).get())
        .isEmpty();
    assertThat($(newAbcArray()).splice(0, 4).get())
        .isEmpty();

    // From start 1 - delete count [-1..4]
    assertThat($(newAbcArray()).splice(1, -1).get())
        .containsExactly("a","b","c");
    assertThat($(newAbcArray()).splice(1, 0).get())
        .containsExactly("a","b","c");

    // [a,b,c].splice(1,1) => [a, c]
    assertThat($(newAbcArray()).splice(1, 1).get())
        .containsExactly("a","c");

    // [a,b,c].splice(1,[2..~]) => [a]
    assertThat($(newAbcArray()).splice(1, 2).get())
        .containsExactly("a");
    assertThat($(newAbcArray()).splice(1, 3).get())
        .containsExactly("a");
    assertThat($(newAbcArray()).splice(1, 4).get())
        .containsExactly("a");

    // From start -1 - delete count [-1..4]
    assertThat($(newAbcArray()).splice(-1, -1).get())
        .containsExactly("a","b","c");
    assertThat($(newAbcArray()).splice(-1, 0).get())
        .containsExactly("a","b","c");

    // [a,b,c].splice(-1,1) => [a,b]
    assertThat($(newAbcArray()).splice(-1, 1).get())
        .containsExactly("a","b");

    // [a,b,c].splice(-1,2) => [a,b]
    assertThat($(newAbcArray()).splice(-1, 2).get())
        .containsExactly("a", "b");

    // [a,b,c].splice(-2,2) => [a]
    assertThat($(newAbcArray()).splice(-2, 2).get())
        .containsExactly("a");
    assertThat($(newAbcArray()).splice(-2, 4).get())
        .containsExactly("a");


    // [a,b,c].splice(0,4) => []
    assertThat($(newAbcArray()).splice(0, 4).get())
        .isEmpty();

    assertThat($(NULL_ARRAY).splice(0,0).get()).isNull();


    assertThat($(newAbcArray()).splice(0,0,"z"))
        .contains("z","a","b","c");
    assertThat($(newAbcArray()).splice(0,1,"z"))
        .contains("z","b","c");

    var list = Lists.<String>newList();
    var spliced = $(newAbcArray()).spliceTo(list, 0,1);
    assertThat(spliced.get()).containsExactly("b","c");
    assertThat(list).containsExactly("a");

    list = Lists.newList();
    spliced = $(newAbcArray()).spliceTo(list, 0,1, "z");
    assertThat(spliced.get()).contains("z","b","c");
    assertThat(list).contains("a");
  }

  private String[] newAbcArray() {
    return new String[]{"a","b","c"};
  }

  @Test
  void fromJson() {
    String json = """
        ["a","b","c"]""";
    String[] array = $((String[]) null).fromJson(String[].class, json).get();
    assertThat(array).containsExactly(newAbcArray());
  }
  @Test
  void deepCopy() {
    String[] original = newAbcArray();
    String[] copy = $(original).deepCopy().get();
    assertThat(copy).isNotNull()
                    .isEqualTo(original)
                    .isNotSameAs(original);

    assertThat($(NULL_ARRAY).deepCopy().get())
        .isNull();
  }
}