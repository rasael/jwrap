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
    else if (iterable!=null) {
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

    if (!isListIndexValid(list, index))
      return element;

    return list.set(index, element);
  }

  public static <E> E get(List<E> list, int index) {
    if (list==null)
      return null;

    if (!isListIndexValid(list, index))
      return null;

    return list.get(index);
  }


  public static <E> E at(List<E> list, int index) {
    if (list==null)
      return null;

    if (index<0)
      index = list.size() + index;

    if (!isListIndexValid(list, index)) {
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

    if (!isListIndexValid(list, index))
      return false;

    return Comparison.areEqual(list.get(index), element);
  }

  public static <E> boolean doesNotContain(List<E> list, E element, int index) {
    if (list==null)
      return true;

    if (!isListIndexValid(list, index))
      return true;

    return !Comparison.areEqual(list.get(index), element);
  }

  public static void remove(List<?> list, int index) {
    if (list==null)
      return;

    if (!isListIndexValid(list, index))
      return;

    list.remove(index);
  }

  public static <E> List<E> clone(List<E> value) {
    if (value==null)
      return null;

    List<E> clone = createClone(value);
    if (Unmodifiables.isUnmodifiableCollection(value)) {
      return java.util.Collections.unmodifiableList(value);
    }
    return clone;
  }

  private static <E> List<E> createClone(List<E> list) {
    if (list instanceof LinkedList)
      return new LinkedList<>(list);

    return newList(list);
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
  public static boolean isEmpty(List<?> list) {
    return list==null || list.isEmpty();
  }

  public static int size(List<?> list) {
    return list==null ? 0 : list.size();
  }

  public static <E> List<E> subList(List<E> list, int startIndexInclusive, int endIndexExclusive) {
    return subList(list, startIndexInclusive, endIndexExclusive, true);
  }

  public static <E> List<E> subList(List<E> list, int startIndexInclusive, int endIndexExclusive, boolean asView) {
    if (list==null)
      return null;

    if (startIndexInclusive<0)
      startIndexInclusive = 0;

    if (endIndexExclusive>list.size()) {
      endIndexExclusive = list.size();
    }

    int newSize = endIndexExclusive - startIndexInclusive;
    if (newSize<=0)
      return asView ? Collections.emptyList() : new LinkedList<>();

    List<E> subList = list.subList(startIndexInclusive, endIndexExclusive);
    return asView ? subList : Lists.newList(subList);
  }

  public static <E> void push(List<E> list, List<E> items) {
    if (isEmpty(list))
      return;

    Lists.addAll(list, items);
  }

  public static boolean isListIndexValid(List<?> value, int index) {
    return index >= 0 && size(value) > index;
  }

  public static String toString(List<?> list) {
    return list!=null ? list.toString() : null;
  }

  public static <T> void listcopy(List<? extends T> src, int srcPos, List<? super T> dest, int destPos, int length) {
    if (src==null || dest==null)
      return;

    dest.addAll(destPos, src.subList(srcPos, srcPos + length));
  }
}
