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
import net.bervini.rasael.jwrap.util.Arrays;
import net.bervini.rasael.jwrap.util.IntObjConsumer;
import net.bervini.rasael.jwrap.util.Iterators;
import net.bervini.rasael.jwrap.util.Lists;
import net.bervini.rasael.jwrap.util.Predicates;
import net.bervini.rasael.jwrap.util.Splice;
import net.bervini.rasael.jwrap.util.Spliterators;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

@ParametersAreNullableByDefault
public abstract class AbstractObjectArrayWrap<
    ELEMENT,
    SELF extends AbstractObjectArrayWrap<ELEMENT, SELF>>
    extends AbstractWrap<ELEMENT[], SELF>
    implements MutableIndexedObjectEnumerableWrap<ELEMENT, SELF>, StreamableWrap<ELEMENT> {

  private final Class<ELEMENT> type;

  protected AbstractObjectArrayWrap(ELEMENT[] value) {
    super(value);
    type = Arrays.componentType(value);
  }

  // -------------------------------------------------------------------------------------------------------------------

  public Class<?> componentType() {
    return type;
  }


  // -------------------------------------------------------------------------------------------------------------------
  // Basic operations
  // -------------------------------------------------------------------------------------------------------------------

  public ListWrap<ELEMENT> asList() {
    return Wrap(Lists.newListOrNull(value));
  }

  public SELF filter(Predicate<? super ELEMENT> predicate) {
    set(Arrays.filter(value, predicate));
    return myself;
  }

  @Override
  public SELF removeIf(Predicate<? super ELEMENT> predicate) {
    set(Arrays.filter(value, Predicates.not(predicate)));
    return myself;
  }

  @Override
  public SELF pushAll(Iterable<ELEMENT> elements) {
    return set(Arrays.addAll(value, elements));
  }

  @Override
  @Tested
  public ELEMENT pop() {
    ELEMENT peek = Arrays.peek(value);
    set(Arrays.pop(value));
    return peek;
  }

  @Override
  @Tested
  public ELEMENT shift() {
    ELEMENT first = Arrays.get(value, 0);
    set(Arrays.remove(value, 0));
    return first;
  }

  @Override
  @Tested
  public SELF unshift(ELEMENT element) {
    set(Arrays.insert(0, value, element));
    return myself;
  }

  @Override
  public SELF remove(int index) {
    return splice(index);
  }

  @Tested
  public SELF splice() {
    set(Splice.splice(value));
    return myself;
  }

  /**
   * @param start The index at which to start changing the array.<br><br>
   *              If greater than the length of the array, start will be set to the length of the array.
   *              In this case, no element will be deleted but the method will behave
   *              as an adding function, adding as many elements as items provided.<br><br>
   *              If negative, it will begin that many elements from the end of the array.<br><br>
   *              (In this case, the origin -1, meaning -n is the index of the nth last element, and is therefore
   *              equivalent to the index of array.length - n.)<br><br>
   *              If start is negative infinity, it will begin from index 0.
   * @return
   */
  public SELF splice(int start) {
    set(Splice.splice(value, start).result());
    return myself;
  }

  /**
   * @param start       The index at which to start changing the array.<br><br>
   *                    If greater than the length of the array, start will be set to the length of the array.
   *                    In this case, no element will be deleted but the method will behave
   *                    as an adding function, adding as many elements as items provided.<br><br>
   *                    If negative, it will begin that many elements from the end of the array.<br><br>
   *                    (In this case, the origin -1, meaning -n is the index of the nth last element, and is therefore
   *                    equivalent to the index of array.length - n.)<br><br>
   *                    If start is negative infinity, it will begin from index 0.
   * @param deleteCount An integer indicating the number of elements in the array to remove from start.<br><br>
   *                    If deleteCount is omitted, or if its value is equal to or larger than array.length - start
   *                    (that is, if it is equal to or greater than the number of elements left in the array,
   *                    starting at start), then all the elements from start to the end of the array will be
   *                    deleted.<br><br>
   *                    If deleteCount is 0 or negative, no elements are removed.<br><br>
   *                    In this case, you should specify at least one new element (see below).
   * @param items       The elements to add to the array, beginning from start.<br><br>
   *                    If you do not specify any elements, splice() will only remove elements from the array.
   * @return
   */
  public SELF splice(int start, int deleteCount, ELEMENT... items) {
    set(Splice.splice(value, start, deleteCount, items).result());
    return myself;
  }

  public SELF spliceTo(Collection<ELEMENT> removed, int start) {
    set(Splice.spliceTo(value, start, removed));
    return myself;
  }

  public SELF spliceTo(Collection<ELEMENT> removed, int start, int deleteCount, ELEMENT... items) {
    set(Splice.spliceTo(value, start, deleteCount, removed, items));
    return myself;
  }

  @Nullable
  @Override
  public ELEMENT get(int index) {
    return Arrays.get(value, index);
  }

  @Nullable
  @Override
  public ELEMENT at(int index) {
    return Arrays.at(value, index);
  }

  @Nonnull
  @Override
  public SELF set(int index, ELEMENT element) {
    replace(index, element);
    return myself;
  }

  @Nullable
  @Override
  public ELEMENT replace(int index, ELEMENT element) {
    return Arrays.set(value, index, element);
  }

  @Override
  public int indexOf(@Nullable ELEMENT element) {
    return Arrays.indexOf(value, element);
  }


  @SafeVarargs
  public final SELF concat(ELEMENT[]... items) {
    set(Arrays.concat(value, items));
    return myself;
  }

  public final Iterable<Map.Entry<Integer, ELEMENT>> entries() {
    return () -> Iterators.indexed(iterator());
  }

  public SELF forEachEntry(IntObjConsumer<ELEMENT> consumer) {
    if (value==null || consumer==null)
      return myself;

    for (int i = 0; i < value.length; i++) {
      consumer.accept(i, value[i]);
    }
    return myself;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Representation
  // -------------------------------------------------------------------------------------------------------------------

  @Override
  public String toString() {
    return Arrays.toString(value, false);
  }

  public String deepToString() {
    return Arrays.toString(value, true);
  }

  // -------------------------------------------------------------------------------------------------------------------

  public SELF swap(int index, int otherIndex) {
    ArrayUtils.swap(value, index, otherIndex);
    return self();
  }

  @Override
  public int size() {
    return value!=null ? value.length : 0;
  }

  @Override
  public boolean contains(ELEMENT element, int index) {
    if (value==null || index >= value.length)
      return false;

    return Objects.equals(value[index], element);
  }

  @Override
  public boolean doesNotContain(ELEMENT element, int index) {
    if (value==null || index >= value.length)
      return true;

    return !Objects.equals(value[index], element);
  }

  @Override
  public boolean contains(@Nullable Iterable<ELEMENT> iterable) {
    if (value==null || iterable==null)
      return false;

    for (ELEMENT element : iterable) {
      if (!ArrayUtils.contains(value, element))
        return false;
    }

    return true;
  }

  public SELF reverse() {
    ArrayUtils.reverse(value);
    return myself;
  }

  public SELF sort() {
    if (value!=null && size() > 1) {
      Arrays.sort(value);
    }
    return myself;
  }

  public SELF sort(Comparator<? super ELEMENT> comparator) {
    if (value!=null && size() > 1) {
      Arrays.sort(value, comparator);
    }
    return myself;
  }

  public SELF shuffle() {
    Arrays.shuffle(value);
    return myself;
  }

  public SELF clear() {
    Arrays.fill(value, null);
    return myself;
  }

  @Override
  public SELF fill(@Nullable ELEMENT element) {
    Arrays.fill(value, element);
    return myself;
  }

  // -------------------------------------------------------------------------------------------------------------------

  @NotNull
  @Override
  public Iterator<ELEMENT> iterator() {
    return Arrays.asList(value).iterator();
  }

  @Override
  public Spliterator<ELEMENT> spliterator() {
    if (value==null) {
      return Spliterators.empty();
    }
    return Spliterators.orderedArray(value);
  }

  @Override
  public Stream<ELEMENT> pureStream() {
    return Arrays.stream(value);
  }
}
