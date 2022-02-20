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

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Predicate;
import java.util.stream.Stream;

@ParametersAreNullableByDefault
public class Arrays {

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  // -------------------------------------------------------------------------------------------------------------------

  private Arrays(){}

  // -------------------------------------------------------------------------------------------------------------------
  public static <ELEMENT> void sort(ELEMENT[] value) {
    sort(value, null);
  }

  public static <ELEMENT> void sort(ELEMENT[] value, Comparator<? super ELEMENT> comparator) {
    if (value==null)
      return;

    if (value.length<2)
      return;

    java.util.Arrays.sort(value, comparator);
  }

  @SafeVarargs
  public static <T> T[] array(T... values) {
    return values;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public static <T> T[] array(AtomicReferenceArray<T> atomicReferenceArray) {
    if (atomicReferenceArray == null) {
      return null;
    }
    else {
      int length = atomicReferenceArray.length();
      if (length == 0) {
        return array();
      } else {
        List<T> list = new ArrayList<>();

        for(int i = 0; i < length; ++i) {
          list.add(atomicReferenceArray.get(i));
        }

        return (T[]) list.toArray((Object[]) Array.newInstance(Object.class, length));
      }
    }

  }

  public static String toString(Object[] value, boolean deep) {
    if (deep) {
      return java.util.Arrays.deepToString(value);
    }
    else {
      return java.util.Arrays.toString(value);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> Class<T> componentType(Object array) {
    if (array==null)
      return null;

    return (Class<T>) array.getClass().getComponentType();
  }

  public static <E> E[] clear(E[] array) {
    if (isEmpty(array))
      return array;

    return java.util.Arrays.copyOf(array, 0);
  }

  public static <E> void fill(E[] array, E element) {
    if (array==null)
      return;

    java.util.Arrays.fill(array, element);
  }

  public static void shuffle(Object[] array) {
    if (array==null || array.length<2)
      return;

    ArrayUtils.shuffle(array, SECURE_RANDOM);
  }

  @SafeVarargs
  @Nonnull
  public static <T> List<T> asList(T...elements) {
    if (elements==null || elements.length==0)
      return Collections.emptyList();

    return java.util.Arrays.asList(elements);
  }

  @SafeVarargs
  @Nonnull
  public static <T> List<T> newList(T...elements) {
    if (elements==null || elements.length==0)
      return Collections.newList();

    return Lists.newList(elements);
  }

  @Contract(value = "null -> true", pure = true)
  public static boolean isEmpty(Object[] values) {
    return size(values)==0;
  }

  public static int size(Object[] array) {
    return array!=null ? array.length : 0;
  }

  @Nonnull
  public static <T> Stream<T> stream(T[] array) {
    return Streams.stream(array);
  }

  public static <E> E get(E[] value, int index) {
    return ArrayUtils.get(value, index);
  }

  public static <E> E set(E[] array, int index, E element) {
    if (array==null || isEmpty(array)) {
      return null;
    }

    if (!ArrayUtils.isArrayIndexValid(array, index)) {
      return null;
    }

    E prevValue = array[index];
    array[index] = element;
    return prevValue;
  }

  public static <E> E at(E[] array, int index) {
    if (array==null || isEmpty(array))
      return null;

    if (index<0)
      index = array.length + index;

    if (!ArrayUtils.isArrayIndexValid(array, index)) {
      return null;
    }

    return get(array, index);
  }

  @SafeVarargs
  public static <E> E[] push(E[] array, E... elements) {
    if (isEmpty(elements)) {
      return array;
    }
    return ArrayUtils.addAll(array, elements);
  }


  public static <E> E[] addAll(E[] array, Iterable<E> elements) {
    if (array==null || Iterables.isEmpty(elements)) {
      return array;
    }

    return ArrayUtils.addAll(array, Iterables.toArray(elements, i -> newArrayLike(array, i)));
  }

  public static <E> E peek(E[] array) {
    if (array==null || isEmpty(array))
      return null;

    return array[array.length-1];
  }

  public static <E> E[] pop(E[] array) {
    if (array==null || isEmpty(array))
      return array;

    return ArrayUtils.remove(array, size(array)-1);
  }

  public static <E> E[] remove(E[] array, int index) {
    if (array==null || isEmpty(array))
      return array;

    return ArrayUtils.remove(array, index);
  }

  public static <E> E[] subarray(E[] array, int startIndexInclusive, int endIndexExclusive) {
    return ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
  }


  public static <E> E[] remove(E[] array, int startIndexInclusive, int endIndexExclusive) {
    return removeImpl(array, startIndexInclusive, endIndexExclusive, null);
  }

  static <E> E[] removeImpl(E[] array, int startIndexInclusive, int endIndexExclusive, E[] insert) {
    if (array==null || isEmpty(array))
      return array;

    // ensure 'toIndex' is not out of bounds
    endIndexExclusive = Math.min(array.length, endIndexExclusive);

    // check bounds for from/toIndex
    if (!ArrayUtils.isArrayIndexValid(array, startIndexInclusive) || !ArrayUtils.isArrayIndexValid(array, endIndexExclusive)) {
      return array;
    }

    // Simple case, we subarray [0..from]
    if (endIndexExclusive>=array.length) {
      return ArrayUtils.subarray(array, 0, startIndexInclusive);
    }

    var elementsBeforeStart = startIndexInclusive;
    var elementsAfterEnd = array.length - endIndexExclusive;
    var insertsCount = size(insert);
    var elementsToCopy = elementsBeforeStart + elementsAfterEnd + insertsCount;

    var result = newArrayLike(array, elementsToCopy);

    // [0...from]
    System.arraycopy(array, 0, result, 0, startIndexInclusive);

    if (insertsCount>0) {
      System.arraycopy(insert, 0, result, startIndexInclusive, insertsCount);
    }

    // [toIndex+1...array.length]
    System.arraycopy(array, endIndexExclusive, result, startIndexInclusive + insertsCount, array.length - endIndexExclusive);

    return result;
  }

  public static <E> E[] newArrayLike(E[] array) {
    return newArrayLike(array, 0);
  }

  public static <E> E[] newArrayLike(E[] array, int newSize) {
    if (array==null)
      return null;

    Class<?> type = array.getClass().getComponentType();

    @SuppressWarnings("unchecked") // OK, because array is of type E
    final E[] emptyArray = (E[]) Array.newInstance(type, Math.max(0, newSize));
    return emptyArray;
  }

  public static <E> E[] insert(int index, E[] array, E element) {
    return ArrayUtils.insert(index, array, element);
  }



  public static <E> E[] filter(E[] array, Predicate<? super E> predicate) {
    var size = size(array);
    if (size==0 || predicate==null)
      return array;

    if (size==1)
      return predicate.test(array[0]) ? array : newArrayLike(array);

    var list = Lists.<E>newList(size);
    for (E e : array) {
      if (predicate.test(e)) {
        list.add(e);
      }
    }
    return list.toArray(i -> newArrayLike(array, i));
  }

  public static <E> E[] clone(E[] array) {
    if (array==null)
      return null;

    return array.clone();
  }

  // -------------------------------------------------------------------------------------------------------------------

  public static <E> int indexOf(E[] array, E element) {
    return ArrayUtils.indexOf(array, element);
  }

  @SafeVarargs
  public static <E> E[] concat(E[] array, E[]... items) {
    if (isEmpty(items))
      return clone(array);

    for (E[] item : items) {
      array = concat(array, item);
    }

    return array;
  }

  @SafeVarargs
  public static <E> E[] concat(E[] array, E... items) {
    if (isEmpty(items))
      return clone(array);

    if (isEmpty(array))
      return clone(items);

    return ArrayUtils.addAll(array, items);
  }

  @SafeVarargs
  public static <E> List<E> asListOrNull(E...values) {
    if (values==null)
      return null;

    return asList(values);
  }

  public static String toString(Object array) {
    return ArrayUtils.toString(array);
  }

  @SafeVarargs
  public static <E> List<E> toList(E...elements) {
    return Lists.newList(asList(elements));
  }

  @SafeVarargs
  public static <E> Iterable<E> toSet(E...elements) {
    return Sets.newOrderedSet(asList(elements));
  }
}
