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

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Splice {

  private Splice(){}

  // -------------------------------------------------------------------------------------------------------------------
  // List splice
  // -------------------------------------------------------------------------------------------------------------------

  public static <E> List<E> splice(List<E> list) {
    return Splice.<E>listSplicer().splice(list);
  }

  /**
   * <p>Splice the given list, from the {@code start} index.
   *
   * <p>All elements in the list that have index greater or equal to {@code start} are removed from this list.
   *
   * <p>Negative {@code start} values are counted from the end of the list.
   *
   * <p>This method may return a clone of the input list.
   *
   * @param list the list to splice
   * @param start the position to splice from
   * @return the splice result (the resulting and the removed elements)
   */
  public static <E> Spliced<List<E>> splice(List<E> list, int start) {
    if (list==null)
      return Spliced.undefined();

    return Splice.<E>listSplicer().splice(list, start, true);
  }

  /**
   * <p>Convenience method to splice the given list and put the removed values into the given {@code removed} collection.
   *
   * <p>This method may return a clone of the input list.
   *
   * @see #splice(List, int)
   */
  public static <E> List<E> spliceTo(List<E> list, int start, Collection<E> removed) {
    if (list==null)
      return Lists.newList(0);

    var spliced = Splice.<E>listSplicer().splice(list, start, removed!=null);
    if (removed!=null) {
      removed.addAll(spliced.removed());
    }
    return spliced.result();
  }

  /**
   * <p>Splice the given list, removing {@code deleteCount} entries from the {@code start} index.
   *
   * <p>Starting from the {@code start} index, {@code deleteCount} elements are removed from the given list.
   *
   * <p>Negative {@code start} values are counted from the end of the list.
   *
   * <p>This method may return a clone of the input list.
   *
   * @param list the list to splice
   * @param start the position to splice from
   * @param deleteCount the number of items to splice
   * @return the splice result (the resulting and the removed elements)
   */
  public static <E> Spliced<List<E>> splice(List<E> list, int start, int deleteCount) {
    return splice(list, start, deleteCount, Lists.empty());
  }

  /**
   * <p>Splice the given list, removing {@code deleteCount} entries from the {@code start} index and inserting
   * {@code insert} in the list.
   *
   * <p>Starting from the {@code start} index, {@code deleteCount} elements are removed from the given list.
   * Then all elements of {@code insert} are inserted at the {@code start} position.
   *
   * <p>Negative {@code start} values are counted from the end of the list.
   *
   * <p>This method may return a clone of the input list.
   *
   * @param list the list to splice
   * @param start the position to splice from
   * @param deleteCount the number of items to splice
   * @param insert the items to insert at the start position
   * @return the splice result (the resulting and the removed elements)
   */
  public static <E> Spliced<List<E>> splice(List<E> list, int start, int deleteCount, List<E> insert) {
    if (list==null)
      return Spliced.undefined();

    return Splice.<E>listSplicer().splice(list, start, deleteCount, true, insert);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Array splice
  // -------------------------------------------------------------------------------------------------------------------

  public static <E> E[] splice(E[] array) {
    return Splice.<E>arraySplicer().splice(array);
  }

  @SafeVarargs
  public static <E> Spliced<E[]> splice(E[] array, int start, int deleteCount, E... insert) {
    if (array==null)
      return Spliced.undefined();

    return Splice.<E>arraySplicer().splice(array, start, deleteCount, true, insert);
  }

  public static <E> Spliced<E[]> splice(E[] array, int start) {
    if (array==null)
      return Spliced.undefined();

    return Splice.<E>arraySplicer().splice(array, start, false);
  }

  public static <E> E[] spliceTo(E[] array, int start, Collection<E> removed) {
    if (array==null)
      return null;

    var spliced = Splice.<E>arraySplicer().splice(array, start, removed!=null);
    if (removed!=null) {
      removed.addAll(Arrays.asList(spliced.removed()));
    }
    return spliced.result();
  }

  public static <E> E[] spliceTo(E[] array, int start, int deleteCount, Collection<E> removed, E...items) {
    if (array==null)
      return null;

    var spliced = Splice.<E>arraySplicer().splice(array, start, deleteCount, removed!=null, items);
    if (removed!=null) {
      removed.addAll(Arrays.asList(spliced.removed()));
    }
    return spliced.result();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Splicers
  // -------------------------------------------------------------------------------------------------------------------

  private static <E> Splicer<E[]> arraySplicer() {
    return ArraySplicer.get();
  }

  private static <E> Splicer<List<E>> listSplicer() {
    return ListSplicer.get();
  }


  // -------------------------------------------------------------------------------------------------------------------

  @ThreadSafe
  private interface Splicer<A> {

    A remove(A value, int startIndexInclusive, int endIndexExclusive, A items);

    A cloneOrClear(A value, A items);

    A clone(A value);

    int size(A value);

    A empty(A value);

    A subset(A value, int startIndexInclusive, int endIndexExclusive);

    A push(A value, A items);

    // -------------------------------------------------------------------------------------------------------------------

    default A splice(A value) {
      return clone(value);
    }

    default Spliced<A> splice(A array, int start) {
      if (array==null)
        return Spliced.undefined();

      return splice(array, start, false);
    }

    default Spliced<A> splice(A value, int start, boolean returnRemoved) {
      int size = size(value);
      if (size==0)
        return Spliced.of(value, empty(value));

      start = toSpliceStart(start, size);

      A removed = null;
      if (returnRemoved)
        removed = subset(value, start, size(value));

      return Spliced.of(subset(value, 0, start), removed);
    }

    default Spliced<A> splice(A value, int start, int deleteCount, boolean returnRemoved, A items) {
      int size = size(value);
      if (size==0)
        return Spliced.of(cloneOrClear(value, items), empty(value));

      // If deleteCount is 0 or negative, no elements are removed.
      if (deleteCount <= 0 && size(items)==0)
        return Spliced.of(value, empty(value));

      start = Splice.toSpliceStart(start, size);

      // If 0..deleteCount is the entire array, return items or an empty array
      if (start==0 && deleteCount > size) {
        A result = cloneOrClear(value, items);
        if (!returnRemoved) {
          return Spliced.of(result, empty(value));
        }
        return Spliced.of(result, value);
      }

      // If deleteCount is equal to or larger than array.length - start
      // then all the elements from start to the end of the array will be deleted.
      A removed = null;
      if (deleteCount >= (size - start)) {

        if (returnRemoved) {
          // compute the splice subarray only if required as result
          removed = subset(value, start, size(value));
        }

        value = subset(value, 0, start);
        return Spliced.of(push(value, items), removed);
      }

      if (returnRemoved) {
        removed = subset(value, start, start + deleteCount);
      }

      if (removed==null)
        removed = empty(value);

      return Spliced.of(remove(value, start, start + deleteCount, items), removed);
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Utility methods
  // -------------------------------------------------------------------------------------------------------------------

  public static int toSpliceStart(int start, int size) {
    if (start < 0)
      start = size + start;

    return Math.min(size, start);
  }

  // -------------------------------------------------------------------------------------------------------------------

  @ThreadSafe
  private static class ListSplicer<E> implements Splicer<List<E>> {

    @SuppressWarnings("unchecked")
    public static <E> ListSplicer<E> get() {
      return (ListSplicer<E>) Singleton.instance;
    }

    private static class Singleton {
      private static final ListSplicer<?> instance = new ListSplicer<>();
    }

    private ListSplicer(){}

    @Override
    public List<E> clone(List<E> value) {
      return value!=null ? Lists.clone(value) : Lists.newList();
    }

    @Override
    public List<E> cloneOrClear(List<E> value, List<E> toClone) {
      if (!Lists.isEmpty(toClone)) {
        return new ArrayList<>(toClone);
      }
      Lists.clear(value);
      return value;
    }

    @Override
    public List<E> push(List<E> value, List<E> items) {
      Lists.pushAll(value, items);
      return value;
    }

    @Override
    public int size(List<E> value) {
      return Lists.size(value);
    }

    @Override
    public List<E> empty(List<E> value) {
      return Lists.newList();
    }

    @Override
    public List<E> subset(List<E> value, int startIndexInclusive, int endIndexExclusive) {
      return Lists.subList(value, startIndexInclusive, endIndexExclusive, false);
    }

    @Override
    public List<E> remove(List<E> array, int startIndexInclusive, int endIndexExclusive, List<E> insert) {
      if (array==null || Lists.isEmpty(array))
        return array;

      // ensure 'toIndex' is not out of bounds
      endIndexExclusive = Math.min(array.size(), endIndexExclusive);

      // check bounds for from/toIndex
      if (!Lists.isListIndexValid(array, startIndexInclusive) || !Lists.isListIndexValid(array, endIndexExclusive)) {
        return array;
      }

      // Simple case, we subarray [0..from]
      if (endIndexExclusive>=array.size()) {
        return subset(array, 0, startIndexInclusive);
      }

      var elementsBeforeStart = startIndexInclusive;
      var elementsAfterEnd = array.size() - endIndexExclusive;
      var insertsCount = size(insert);
      var elementsToCopy = elementsBeforeStart + elementsAfterEnd + insertsCount;

      var result = new ArrayList<E>(elementsToCopy);

      // [0...from]
      Lists.listcopy(array, 0, result, 0 ,startIndexInclusive);

      if (insertsCount>0) {
        Lists.listcopy(insert, 0, result, startIndexInclusive, insertsCount);
      }

      // [toIndex+1...array.length]
      Lists.listcopy(array, endIndexExclusive, result, startIndexInclusive + insertsCount, array.size() - endIndexExclusive);

      return result;
    }
  }

  private static class ArraySplicer<E> implements Splicer<E[]> {

    @SuppressWarnings("unchecked")
    public static <E> ArraySplicer<E> get() {
      return (ArraySplicer<E>) Singleton.instance;
    }

    private static class Singleton {
      private static final ArraySplicer<?> instance = new ArraySplicer<>();
    }

    private ArraySplicer(){}

    @Override
    public E[] clone(E[] value) {
      return Arrays.clone(value);
    }

    @Override
    public E[] cloneOrClear(E[] array, E[] toClone) {
      return !Arrays.isEmpty(toClone) ? Arrays.clone(toClone) : Arrays.clear(array);
    }

    @Override
    public E[] push(E[] value, E[] items) {
      return Arrays.push(value, items);
    }

    @Override
    public int size(E[] value) {
      return Arrays.size(value);
    }

    @Override
    public E[] empty(E[] value) {
      return Arrays.newArrayLike(value);
    }

    @Override
    public E[] subset(E[] value, int startIndexInclusive, int endIndexExclusive) {
      return Arrays.subarray(value, startIndexInclusive, endIndexExclusive);
    }

    @Override
    public E[] remove(E[] value, int startIndexInclusive, int endIndexExclusive, E[] insert) {
      return Arrays.removeImpl(value, startIndexInclusive, endIndexExclusive, insert);
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Result
  // -------------------------------------------------------------------------------------------------------------------

  public static record Spliced<T>(T result, T removed){

    private static final Spliced<?> NULL = new Spliced<>(null, Collections.emptyList());

    @SuppressWarnings("unchecked")
    public static <A> Spliced<A> undefined() {
      return (Spliced<A>) NULL;
    }

    public static <A> Spliced<A> of(A value, A spliced) {
      if (value==null)
        return undefined();

      return new Spliced<>(value, spliced);
    }
  }
}
