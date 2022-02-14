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

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static net.bervini.rasael.jwrap.api.JWrap.$;
import static org.assertj.core.api.Assertions.*;

class AbstractWrapTest {

  private static final String NULL_STRING = null;
  private static final String EMPTY_STRING = "";

  private static final String[] NULL_STRING_ARRAY = null;
  private static final BigDecimal NULL_BIG_DECIMAL = null;

  @Test
  void cannotWrapAWrap() {
    var wrap = $(NULL_STRING);
    assertThatThrownBy(() -> $(wrap))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("wrap-a-wrap");
  }

  @Test
  void orElse() {
    assertThat($("nonNull").orElse("else"))
        .isNotEqualTo("else");

    assertThat($(NULL_STRING).orElse("else"))
        .isEqualTo("else");
  }
  @Test
  void testHashCode() {
    assertThat($(NULL_STRING).hashCode()).isZero();
    assertThat($(EMPTY_STRING)).hasSameHashCodeAs(EMPTY_STRING);
  }


  @Test
  void orElseThrowNoArg() {
    var tmp = $(NULL_STRING);
    assertThatThrownBy(tmp::orElseThrow)
        .isInstanceOf(NullPointerException.class);

    var tmp2 = $(EMPTY_STRING);
    assertThatCode(tmp2::orElseThrow)
        .doesNotThrowAnyException();
  }

  @Test
  void orElseGet() {
    assertThat($("nonNull").orElseGet(() ->"else"))
        .isNotEqualTo("else");

    assertThat($(NULL_STRING).orElseGet(() -> "else"))
        .isEqualTo("else");
  }

  @Test
  void orElseFill() {
    assertThat($(NULL_STRING).orElseFill(() -> "else").get())
        .isEqualTo("else");
    assertThat($("nonNull").orElseFill(() -> "else").get())
        .isNotEqualTo("else");
  }

  @Test
  void orElseThrow() {
    assertThatCode(() -> $(NULL_STRING).orElseThrow())
        .isInstanceOf(NullPointerException.class);
    assertThatCode(() -> $("nonNull").orElseThrow())
        .doesNotThrowAnyException();
  }

  @Test
  void orElseThrowSupplied() {
    String message = "TestException";
    Supplier<RuntimeException> supplier = () -> new RuntimeException(message);

    assertThatCode(() -> $(NULL_STRING).orElseThrow(supplier))
        .isInstanceOf(RuntimeException.class)
        .hasMessage(message);

    assertThatCode(() -> $("nonNull").orElseThrow(supplier))
        .doesNotThrowAnyException();
  }
  @Test
  void testToString() {
    assertThat($("hello")).hasToString("hello");
    assertThat($(NULL_STRING)).hasToString("null");
    assertThat($(NULL_STRING_ARRAY)).hasToString("null");
    assertThat($(NULL_BIG_DECIMAL)).hasToString("null");
    assertThat($(EMPTY_STRING)).hasToString("");
    assertThat($(new BigDecimal(42))).hasToString("42");
  }

  @Test
  void testEquals() {
    assertThat($(NULL_STRING)).isEqualTo($(NULL_STRING));
    assertThat($("a")).isEqualTo($("a"));
    assertThat($("a")).isNotEqualTo($("b"));

//    assertThat($(NULL_STRING)).isSameAs($(NULL_STRING));
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Test
  void isNull() {
    String nonNull = "String";
    assertThat($(nonNull).isNull()).isFalse();
    assertThat($(NULL_STRING).isNull()).isTrue();
  }

  @Test
  void isNotNull() {
    String nonNull = "String";
    assertThat($(nonNull).isNotNull()).isTrue();
    assertThat($(NULL_STRING).isNotNull()).isFalse();
  }

  @Test
  void isEqualTo() {
    assertThat($("hello").isEqualTo("hello")).isTrue();
    assertThat($("hello").isEqualTo(new String("hello"))).isTrue();
    assertThat($(NULL_STRING).isEqualTo(null)).isTrue();
  }

  @Test
  void isNotEqualTo() {
    assertThat($("hello").isNotEqualTo("Hello")).isTrue();

    assertThat($("hello").isNotEqualTo("hello")).isFalse();
    assertThat($("hello").isNotEqualTo(new String("hello"))).isFalse();
    assertThat($(NULL_STRING).isNotEqualTo(null)).isFalse();
  }

  @Test
  void isSameAs() {
    assertThat($("hello").isSameAs("hello")).isTrue();
    assertThat($("hello").isSameAs(new String("hello"))).isFalse();
    assertThat($(NULL_STRING).isSameAs(null)).isTrue();
    assertThat($(NULL_STRING).isSameAs("null")).isFalse();
  }

  @Test
  void hasHashCode() {
    assertThat($(42).hasHashCode(42))
        .isTrue();

    assertThat($((Integer) null).hasHashCode(0))
        .isTrue();
  }

  @Test
  void ifNull() {
    AtomicInteger counter = new AtomicInteger(0);
    $("a").ifNull(counter::incrementAndGet);
    assertThat(counter).hasValue(0);

    $((String)null).ifNull(counter::incrementAndGet);
    assertThat(counter).hasValue(1);
  }

  @Test
  void ifNotNull() {
    AtomicInteger counter = new AtomicInteger(0);
    $((String)null).ifNotNull(t -> counter.incrementAndGet());
    assertThat(counter).hasValue(0);

    $("a").ifNotNull(t -> counter.incrementAndGet());
    assertThat(counter).hasValue(1);
  }
}