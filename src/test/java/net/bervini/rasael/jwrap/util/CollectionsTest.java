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

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

class CollectionsTest {

  private static final Collection<String> NULL_COLLECTION = null;

  private static final Collection<String> EMPTY_COLLECTION = Collections.emptyList();

  @Test
  void size() {
    assertThat(Collections.size(NULL_COLLECTION)).isZero();
    assertThat(Collections.size(EMPTY_COLLECTION)).isZero();

    assertThat(Collections.size(Arrays.asList(1))).isOne();
  }

  @Test
  void isEmpty() {
    //noinspection ConstantConditions
    assertThat(Collections.isEmpty(NULL_COLLECTION)).isTrue();
    assertThat(Collections.isEmpty(EMPTY_COLLECTION)).isTrue();

    assertThat(Collections.isEmpty(Arrays.asList(1))).isFalse();
  }

  @Test
  void toArray() {
    //noinspection ConstantConditions
    assertThatThrownBy(() -> Collections.toArray(NULL_COLLECTION, null))
        .isInstanceOf(IllegalArgumentException.class);
    //noinspection ConstantConditions
    assertThatCode(() -> Collections.toArray(EMPTY_COLLECTION, null))
        .isInstanceOf(IllegalArgumentException.class);

    assertThat(Collections.toArray(NULL_COLLECTION, String[]::new)).isEmpty();
    assertThat(Collections.toArray(EMPTY_COLLECTION, String[]::new)).isEmpty();

    var list = Arrays.asList("a","b");
    assertThat(Collections.toArray(list, String[]::new))
        .containsExactly("a","b");
  }

  @Test
  void unmodifiable() {
    assertThat(Collections.unmodifiableList(null))
        .isNotNull()
        .isUnmodifiable()
        .isEmpty();

    assertThat(Collections.unmodifiableList(Arrays.asList(1)))
        .isNotNull()
        .isUnmodifiable()
        .hasSize(1);
  }

  @Test
  void toCollection() {
    assertThat(Collections.toCollection(null)).isNotNull().isEmpty();

    assertThat(Collections.toCollection(() -> Iterators.of(1,2,3)))
        .containsExactly(1,2,3);

    assertThat(Collections.toCollection(Arrays.asList(1,2,3)))
        .containsExactly(1,2,3);
  }
}