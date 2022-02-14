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

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class SupplierWrapTest {

  @Test
  void test() {
    AtomicInteger counter = new AtomicInteger();

    assertThat($(counter::incrementAndGet).stream(3).toList())
        .containsExactly(1,2,3);

    assertThat($(counter::incrementAndGet).stream(3).toList())
        .containsExactly(4,5,6);

    assertThat($(counter::incrementAndGet).stream(3).count())
        .isEqualTo(3);
  }

}