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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Lists {

  private Lists(){}


  // -------------------------------------------------------------------------------------------------------------------

  public static <T> List<T> newList() {
    return new ArrayList<>();
  }
  public static <T> List<T> newList(int capacity) {
    return capacity>0 ? new ArrayList<>(capacity) : new ArrayList<>();
  }
  @SafeVarargs
  public static <T> List<T> newList(T...a) {
    if (a==null || a.length==0)
      return newList();

    return new ArrayList<>(java.util.Arrays.asList(a));
  }

  public static <T> List<T> newList(Collection<T> collection) {
    if (Collections.isEmpty(collection))
      return newList();

    return new ArrayList<>(collection);
  }

  public static <T> List<T> newList(Iterable<T> iterable) {
    List<T> list;
    if (iterable!=null) {
      int exactSize = Math.toIntExact(iterable.spliterator().getExactSizeIfKnown());
      list = newList(exactSize);
      iterable.forEach(list::add);
    }
    else {
      list = newList();
    }
    return list;
  }

  public static <T> T getFirst(List<T> list) {
    if (list==null || list.isEmpty())
      return null;

    return list.get(0);
  }

  public static <T> T getLast(List<T> list) {
    if (list==null || list.isEmpty())
      return null;

    return list.get(list.size()-1);
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> empty() {
    return java.util.Collections.EMPTY_LIST;
  }

  public static <T> List<T> singleton(T element) {
    return java.util.Collections.singletonList(element);
  }

  public static <E> void addAll(List<E> target, E... items) {
    if (target==null || Arrays.isEmpty(items))
      return;

    target.addAll(Arrays.asList(items));
  }
  public static <E> List<E> addAll(List<E> target, Iterable<E> iterable) {
    if (target==null)
      return newList(iterable);

    if (iterable instanceof Collection<E> c) {
      target.addAll(c);
    }
    else {
      iterable.forEach(target::add);
    }
    return target;
  }
  public static void clear(List<?> list) {
    if (list!=null)
      list.clear();
  }

  public static <E> void fill(List<E> list, E with) {
    if (list==null)
      return;

    for (int i = 0; i < list.size(); i++) {
      list.set(i, with);
    }
  }

  public static <E> E set(List<E> list, int index, E element) {
    if (list==null)
      return element;

    if (isListIndexValid(index, list))
      return element;

    return list.set(index, element);
  }

  public static <E> E get(List<E> list, int index) {
    if (list==null)
      return null;

    if (isListIndexValid(index, list))
      return null;

    return list.get(index);
  }

  private static boolean isListIndexValid(int index, List<?> list) {
    if (list==null)
      return false;

    return index >= 0 && list.size() > index;
  }


  public static <E> E at(List<E> list, int index) {
    if (list==null)
      return null;

    if (index<0)
      index = list.size() + index;

    if (!isListIndexValid(index, list)) {
      return null;
    }

    return list.get(index);
  }

  public static int indexOf(List<?> value, Object element) {
    if (value==null)
      return -1;

    return value.indexOf(element);
  }

  public static <E> boolean contains(List<E> list, E element, int index) {
    if (list==null)
      return false;

    if (!isListIndexValid(index, list))
      return false;

    return Comparison.areEqual(list.get(index), element);
  }

  public static <E> boolean doesNotContain(List<E> list, E element, int index) {
    if (list==null)
      return true;

    if (!isListIndexValid(index, list))
      return true;

    return !Comparison.areEqual(list.get(index), element);
  }

  public static void remove(List<?> list, int index) {
    if (list==null)
      return;

    if (!isListIndexValid(index, list))
      return;

    list.remove(index);
  }

  public static <E> List<E> clone(List<E> value) {
    if (value==null)
      return null;

    List<E> clone = createClone(value);
    if (value.getClass().getName().contains("Immutable")) {
      return java.util.Collections.unmodifiableList(value);
    }
    return clone;
  }

  private static <E> List<E> createClone(List<E> value) {
    if (value instanceof LinkedList)
      return new LinkedList<>(value);

    return newList(value);
  }

  public static <T> List<T> newListOrNull(T[] array) {
    if (array==null)
      return null;

    return newList(array);
  }

  public static <E> E pop(List<E> list) {
    if (isEmpty(list))
      return null;

    return list.remove(list.size()-1);
  }

  public static <E> E shift(List<E> list) {
    if (isEmpty(list))
      return null;

    return list.remove(0);
  }

  public static <E> List<E> unshift(List<E> list, E element) {
    if (isEmpty(list))
      return newList(element);

    list.add(element);
    return list;
  }
  private static <E> boolean isEmpty(List<E> list) {
    return list==null || list.isEmpty();
  }
}
