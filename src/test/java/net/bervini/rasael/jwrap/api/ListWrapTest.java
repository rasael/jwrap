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

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;

class ListWrapTest {

  @Test
  void pushAll() {
    var list = Lists.<String>newList();
    $(list).push("a","b");
    assertThat(list).containsExactly("a","b");

    $(list).push("c","d");
    assertThat(list).containsExactly("a","b","c","d");
  }

  @Test
  void unshift() {
    var list = Lists.<String>newList();

    $(list).unshift("b");
    assertThat(list).containsExactly("b");

    $(list).unshift("a");
    assertThat(list).containsExactly("a", "b");

    $(list).shift();
    assertThat(list).containsExactly("b");

    $(list).shift();
    assertThat(list).isEmpty();
  }
}