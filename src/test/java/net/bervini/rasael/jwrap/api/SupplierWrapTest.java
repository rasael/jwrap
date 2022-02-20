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

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class SupplierWrapTest {

  private static final Supplier<String> NULL_SUPPLIER = null;

  @Test
  void getValue() {
    AtomicInteger counter = new AtomicInteger();

    assertThat($(counter::incrementAndGet).getValue()).isOne();
    assertThat($(counter::incrementAndGet).getValue()).isEqualTo(counter.get());
  }

  @Test
  void test() {
    AtomicInteger counter = new AtomicInteger();

    assertThat($(counter::incrementAndGet).stream(3).toList())
        .containsExactly(1,2,3);

    assertThat($(counter::incrementAndGet).stream(3).toList())
        .containsExactly(4,5,6);

    assertThat($(counter::incrementAndGet).stream(3).count())
        .isEqualTo(3);

    assertThat($(counter::incrementAndGet).stream(42).toList())
        .hasSize(42);

    assertThat($(counter::incrementAndGet).infiniteStream()
                               .limit(10)
                               .toList()).hasSize(10);

    assertThat($(NULL_SUPPLIER).iterator(-1)).isExhausted();
    assertThat($(NULL_SUPPLIER).iterator(0)).isExhausted();
    assertThat($(NULL_SUPPLIER).iterator(1)).isExhausted();

    assertThat($(counter::incrementAndGet).iterator(5)).toIterable().hasSize(5);

    assertThat($(counter::incrementAndGet).infiniteIterator()).hasNext();
  }

}