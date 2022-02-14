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

import net.bervini.rasael.jwrap.util.Arrays;
import net.bervini.rasael.jwrap.util.Consumers;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class StreamWrapTest {

  @Test
  void stream() {
    assertThat($((Stream)null).toList()).isEmpty();
  }

  @Test
  void remove() {
    var source = Arrays.asList("a"," ","c");
    assertThat($(source.stream()).remove(String::isBlank).toList())
      .containsExactly("a","c");
  }

  @Test
  void filterByType() {
    var source = Arrays.asList("a",null, new Object(), 1,"c");
    assertThat($(source.stream()).filterByType(String.class).toList())
        .containsExactly("a","c");
  }

  @Test
  void peek() {
    var source = new String[]{"a","b","C"};
    AtomicLong counter = new AtomicLong();
    long count = Arrays.stream(source)
                       .peek(o -> counter.incrementAndGet())
                       .count();

    assertThat(counter).doesNotHaveValue(count);
  }

  @Test
  void removeNulls() {
    var source = new String[]{"a", null, "b"};
    assertThat($(source).stream().removeNulls().toList()).containsExactly("a","b");

    assertThat($((String[])null).stream().removeNulls().toList()).isEmpty();

    assertThat($(new String[]{null, null, null}).stream().removeNulls().toList()).isEmpty();
  }

  @Test
  void normalStreamOperations() {
    var source = new String[]{"a","b","C"};

    assertThat($(source).stream()
             .filter(s -> Character.isLowerCase(s.charAt(0)))
             .map(String::toUpperCase)
             .toList())
        .containsExactly("A","B");

    assertThat($(source).stream()
                                .filter(s -> Character.isLowerCase(s.charAt(0)))
                                .map(String::toUpperCase)
                                .toArray(String[]::new))
        .containsExactly("A","B");

    assertThat($((String[])null).stream()
                        .filter(s -> Character.isLowerCase(s.charAt(0)))
                        .map(String::toUpperCase)
                        .toList())
        .isEmpty();


  }

  @Test
  void avoidOptimizations() {
    var source = new String[]{"a","b","C"};
    AtomicLong counter = new AtomicLong();
    long count = $(source).pureStream().peek(o -> counter.incrementAndGet()).count();
    assertThat(counter)
        .doesNotHaveValue(count)
        .hasValue(0);

    count = $(source).stream()
                     .avoidOptimizations()
                     .pureStream()
                     .peek(o -> counter.incrementAndGet())
                     .count();
    assertThat(counter).hasValue(3);
    assertThat(count).isEqualTo(3);
  }

  @Test
  void onEach() {
    var source = new String[]{"a","b","C"};
    AtomicLong counter = new AtomicLong();
    long count = $(source).stream()
                          .onEach(o -> counter.incrementAndGet())
                          .count();

    assertThat(counter)
        .hasValue(count);

    assertThatCode(() -> $((String[])null).stream()
                                          .onEach(Consumers.nullConsumer()))
        .doesNotThrowAnyException();
  }

  @Test
  void sort() {
    var source = new String[]{"a","c","b"};
    assertThat($(source).stream().sorted().toList())
        .containsExactly("a","b","c");

    assertThat($(source).stream().sortReversed().toList())
        .containsExactly("c","b","a");

    Comparator<String> comparator = Comparator.reverseOrder();
    assertThat($(source).stream().sorted(comparator).toList())
        .containsExactly("c","b","a");

    assertThat($(source).stream().sortReversed(comparator).toList())
        .containsExactly("a","b","c");
  }
}