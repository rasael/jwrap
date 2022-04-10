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

import net.bervini.rasael.jwrap.annotation.Beta;
import net.bervini.rasael.jwrap.util.Iterators;
import net.bervini.rasael.jwrap.util.Predicates;
import net.bervini.rasael.jwrap.util.Streams;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

@ParametersAreNullableByDefault
public abstract class AbstractStreamWrap<ELEMENT, SELF extends AbstractStreamWrap<ELEMENT, SELF>>
    extends AbstractWrap<Stream<ELEMENT>, SELF> implements IterableWrap<ELEMENT>, StreamableWrap<ELEMENT>/*, /*Stream<ELEMENT>*/ {

  protected AbstractStreamWrap(@Nullable Stream<ELEMENT> value) {
    super(value!=null ? value : Stream.empty());
  }

  @Override
  public Stream<ELEMENT> pureStream() {
    return value != null ? value : Streams.empty();
  }

  @NotNull
  @Override
  public Iterator<ELEMENT> iterator() {
    return value!=null ? value.iterator() : Iterators.empty();
  }

  public SELF removeNulls() {
    return filter(Objects::nonNull);
  }

  public SELF remove(Predicate<? super ELEMENT> predicate) {
    return set(Streams.filter(value, Predicates.not(predicate)));
  }

  public SELF filterWhen(boolean condition, Predicate<? super ELEMENT> predicate) {
    if (!condition)
      return myself;

    return set(Streams.filter(value, predicate));
  }

  public SELF filter(Predicate<? super ELEMENT> predicate) {
    return set(Streams.filter(value, predicate));
  }

  public <R> StreamWrap<R> filterByType(Class<R> type) {
    return Wrap(Streams.filterByType(value, type));
  }

  public <R> StreamWrap<R> map(Function<? super ELEMENT, ? extends R> mapper) {
    return Wrap(Streams.map(value, mapper));
  }

  public long count() {
    return pureStream().count();
  }
  
  /**
   * Intended for test use
   */
  @Beta
  public SELF avoidOptimizations() {
               // with a .filter() call we block any optimization on characteristic DISTINCT (eg. count())
    return this.set(Streams.filter(value, Predicates.acceptAll()))
               // with a .map() call we block any optimization on characteristic SORTED
               .set(Streams.map(value, Function.identity()));
  }

  public SELF onEach(Consumer<? super ELEMENT> action) {
    if (value==null || action==null)
      return myself;

    return this.set(Streams.filter(value, Predicates.acceptAll()))
               .set(Streams.map(value, e -> {
                 action.accept(e);
                 return e;
               }));
  }

  // -------------------------------------------------------------------------------------------------------------------
  // sort
  // -------------------------------------------------------------------------------------------------------------------

  public SELF sorted() {
    return set(Streams.sorted(value));
  }

  public SELF sorted(Comparator<? super ELEMENT> comparator) {
    return set(Streams.sorted(value, comparator));
  }

  public <U extends Comparable<? super U>> SELF sortedBy(Function<? super ELEMENT, ? extends U> keyExtractor) {
    return set(Streams.sortedBy(value, keyExtractor));
  }

  public SELF sortReversed() {
    return set(Streams.sortReversed(value));
  }

  public SELF sortReversed(Comparator<? super ELEMENT> comparator) {
    return set(Streams.sortReversed(value, comparator));
  }

  public <U extends Comparable<? super U>> SELF sortReversedBy(Function<? super ELEMENT, ? extends U> keyExtractor) {
    return set(Streams.sortReversedBy(value, keyExtractor));
  }

  public SELF limit(int limit) {
    return set(Streams.limit(value, limit));
  }
}
