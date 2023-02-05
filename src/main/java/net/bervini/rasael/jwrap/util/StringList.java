/*
 * Copyright 2022-2023 Rasael Bervini
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

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class StringList implements Iterable<String> {


  private final List<String> list;

  private StringList(@Nonnull List<String> list) {
    this.list = list;
  }

  public static StringList newStringList() {
    return new StringList(new ArrayList<>());
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public boolean anyPresent() {
    return !list.isEmpty();
  }

  public int size() {
    return list.size();
  }

  public Stream<String> stream() {
    return list.stream();
  }

  public String first() {
    return list.get(0);
  }

  public StringList addEmpty() {
    list.add(Strings.EMPTY);
    return this;
  }

  public StringList applyPrefix(String prefix) {
    return replaceAll(e -> prefix + e);
  }

  public StringList applySuffix(String suffix) {
    return replaceAll(e -> e + suffix);
  }

  public StringList replaceAll(UnaryOperator<String> operator) {
    Objects.requireNonNull(operator);

    list.replaceAll(t -> Objects.requireNonNull(operator.apply(t), "operator produced a null value"));
    return this;
  }

  public StringList removeEmpty() {
    return removeIf(String::isEmpty);
  }

  public StringList removeBlank() {
    return removeIf(String::isBlank);
  }

  public StringList removeIf(Predicate<String> predicate) {
    list.removeIf(Objects.requireNonNull(predicate));
    return this;
  }

  public StringList addAll(Iterable<String> collection) {
    Objects.requireNonNull(collection).forEach(list::add);
    return this;
  }

  public StringList addAll(Collection<String> collection) {
    list.addAll(Objects.requireNonNull(collection));
    return this;
  }
  public StringList add(String s) {
    list.add(Objects.requireNonNull(s));
    return this;
  }

  public String get(int index) {
    return list.get(index);
  }

  public String mergeLines() {
    return merge("\n");
  }

  public String mergeComma() {
    return merge(", ");
  }

  public String merge(String delimiter) {
    return String.join(Objects.requireNonNull(delimiter, "delimiter"), list);
  }

  @NotNull
  @Override
  public Iterator<String> iterator() {
    return list.iterator();
  }

  public String[] toArray() {
    return list.toArray(Strings.EMPTY_ARRAY);
  }
}
