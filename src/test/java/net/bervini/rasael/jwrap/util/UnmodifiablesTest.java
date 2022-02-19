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
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class UnmodifiablesTest {

  @Test
  void isUnmodifiableCollection() {
    assertThat(Unmodifiables.isUnmodifiableCollection(new ArrayList<>())).isFalse();
    assertThat(Unmodifiables.isUnmodifiableCollection(Arrays.asList("a"))).isFalse();
    assertThat(Unmodifiables.isUnmodifiableCollection(java.util.Collections.singleton("a"))).isFalse();
    assertThat(Unmodifiables.isUnmodifiableCollection(java.util.Collections.singletonList("a"))).isFalse();

    assertThat(Unmodifiables.isUnmodifiableCollection(java.util.Collections.unmodifiableList(new ArrayList<>())))
        .isTrue();
    assertThat(Unmodifiables.isUnmodifiableCollection(java.util.Collections.unmodifiableCollection(new ArrayList<>())))
        .isTrue();
    assertThat(Unmodifiables.isUnmodifiableCollection(java.util.Collections.unmodifiableSet(new HashSet<>())))
        .isTrue();
  }

  @Test
  void isUnmodifiableList() {
    assertThat(Unmodifiables.isUnmodifiableList(new ArrayList<>())).isFalse();
    assertThat(Unmodifiables.isUnmodifiableList(Arrays.asList("a"))).isFalse();
    assertThat(Unmodifiables.isUnmodifiableList(java.util.Collections.singletonList("a"))).isFalse();

    assertThat(Unmodifiables.isUnmodifiableList(java.util.Collections.unmodifiableList(new ArrayList<>())))
        .isTrue();
  }

  @Test
  void isUnmodifiableSet() {
    assertThat(Unmodifiables.isUnmodifiableSet(new HashSet<>())).isFalse();
    assertThat(Unmodifiables.isUnmodifiableSet(java.util.Collections.singleton("a"))).isFalse();

    assertThat(Unmodifiables.isUnmodifiableSet(java.util.Collections.unmodifiableSet(new HashSet<>())))
        .isTrue();
  }
}