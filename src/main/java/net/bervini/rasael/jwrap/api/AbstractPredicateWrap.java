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

import net.bervini.rasael.jwrap.annotation.Tested;
import net.bervini.rasael.jwrap.util.Collections;
import net.bervini.rasael.jwrap.util.Predicates;
import net.bervini.rasael.jwrap.util.Streams;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;
import java.util.function.Predicate;

@ParametersAreNullableByDefault
public abstract class AbstractPredicateWrap<T,
    SELF extends AbstractPredicateWrap<T, SELF>>
    extends AbstractWrap<Predicate<T>, SELF> implements Predicate<T> {

  protected AbstractPredicateWrap(@Nullable Predicate<T> value) {
    super(value);
  }

  /**
   * If this predicate is null, then it returns a predicate that always return true.
   * @return this predicate, if not null, or a predicate that always returns true.
   */
  public SELF orTrue() {
    return value!=null ? myself : set(Predicates.acceptAll());
  }

  /**
   * If this predicate is null, then it returns a predicate that always return false.
   * @return this predicate, if not null, or a predicate that always returns false.
   */
  public SELF orFalse() {
    return value!=null ? myself : set(Predicates.rejectAll());
  }

  /**
   * If this predicate does not accepts all the specified values, then its set to null.
   */
  @SafeVarargs
  @Tested
  public final SELF whenAccepts(T... values) {
    if (!accepts(values))
      return setNull();

    return myself;
  }

  /**
   * <p>Returns true if this predicate <strong>accepts all</strong> the specified values</p>
   *
   * <p>If this predicate is null, this method returns false</p>
   *
   * <p>If the values argument is null, this method returns false</p>
   */
  @SafeVarargs
  @Tested
  public final boolean accepts(T... values) {
    return Predicates.accepts(value, values);
  }

  /**
   * <p>Returns true if this predicate <strong>accepts all</strong> the specified values</p>
   *
   * <p>If this predicate is null, this method returns false</p>
   *
   * <p>If the values argument is null, this method returns false</p>
   */
  public boolean acceptsAll(Iterable<T> iterable) {
    return Predicates.acceptsAll(value, iterable);
  }


  /**
   * <p>Returns true if this predicate <strong>rejects all</strong> the specified values</p>
   *
   * <p>If this predicate is null, this method returns true (a null predicate cannot reject nor accept values)</p>
   *
   * <p>If the values argument is null, this method returns true (a null-set is always rejected)</p>
   *
   * <p>If the values argument is empty, this method returns false</p>
   */
  @SafeVarargs
  @Tested
  public final boolean rejects(T... values) {
    return Predicates.rejects(value, values);
  }

  /**
   * <p>Returns true if this predicate <strong>rejects all</strong> the specified values</p>
   *
   * <p>If this predicate is null, this method returns true (a null predicate cannot reject nor accept values)</p>
   *
   * <p>If the values argument is null, this method returns true (a null-set is always rejected)</p>
   *
   * <p>If the values argument is empty, this method returns false</p>
   */
  public boolean rejectsAll(Iterable<T> iterable) {
    return Predicates.rejectsAll(value, iterable);
  }

  @SafeVarargs
  @Tested
  public final boolean rejectsAny(T... values) {
    return Predicates.rejectsAny(value, values);
  }

  public boolean rejectsAny(Iterable<T> iterable) {
    return Predicates.rejectsAny(value, iterable);
  }

  @Override
  public boolean test(T t) {
    if (value==null)
      return false;

    return value.test(t);
  }

  /**
   * <p>Removes all entries that <strong>do not match</strong> this predicate using
   * {@link Collection#removeIf(Predicate)}.</p>
   *
   * <p>If this predicate is null, the collection is returned unaltered.</p>
   *
   * <p>If this collection is null, null is returned.</p>
   * @param collection the collection to filter
   * @param <COLLECTION> the concrete collection type
   * @return the input collection
   * @see #orTrue()
   * @see #orFalse()
   */
  public <COLLECTION extends Collection<T>> COLLECTION filter(COLLECTION collection) {
    if (value==null || collection==null)
      return collection;

    collection.removeIf(Predicates.not(value));

    return collection;
  }

  /**
   * <p>Removes all entries that <strong>match</strong> this predicate using
   * {@link Collection#removeIf(Predicate)}.</p>
   *
   * <p>If this predicate is null, the collection is returned unaltered.</p>
   *
   * <p>If the collection is null, null is returned.</p>
   * @param collection the collection to remove values from
   * @param <COLLECTION> the concrete collection type
   * @return the input collection
   * @see #orTrue()
   * @see #orFalse()
   */
  public <COLLECTION extends Collection<T>> COLLECTION remove(COLLECTION collection) {
    if (value==null || collection==null)
      return collection;

    collection.removeIf(value);

    return collection;
  }

  /**
   * <p>Removes all entries that <strong>do not match</strong> this predicate using
   * {@link java.util.stream.Stream#filter(Predicate)}, and returns a new collection.</p>
   *
   * <p>If this predicate is null, the iterable is returned unaltered as new list.
   * If the iterable is a collection, it will be returned unaltered.</p>
   *
   * <p>If the iterable is null, null is returned.</p>
   * @param iterable the iterable to filter
   * @return the input iterable
   * @see #orTrue()
   * @see #orFalse()
   */
  @Tested
  public Collection<T> filter(Iterable<T> iterable) {
    if (value==null)
      return Collections.toCollection(iterable);

    return Streams.stream(iterable).filter(value).toList();
  }

  /**
   * <p>Removes all entries that <strong>match</strong> this predicate using
   * {@link java.util.stream.Stream#filter(Predicate)}, and returns a new collection.</p>
   *
   * <p>If this predicate is null, the iterable is returned unaltered as new list.
   * If the iterable is a collection, it will be returned unaltered.</p>
   *
   * <p>If the iterable is null, null is returned.</p>
   * @param iterable the iterable to filter
   * @return the input iterable
   * @see #orTrue()
   * @see #orFalse()
   */
  public Collection<T> remove(Iterable<T> iterable) {
    if (value==null)
      return Collections.toCollection(iterable);

    return Streams.stream(iterable).filter(value.negate()).toList();
  }

  public SELF not() {
    return set(Predicates.not(value));
  }

}
