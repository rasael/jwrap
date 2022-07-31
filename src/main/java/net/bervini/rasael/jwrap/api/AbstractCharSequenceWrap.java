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
import net.bervini.rasael.jwrap.annotation.Tested;
import net.bervini.rasael.jwrap.util.CharSequences;
import net.bervini.rasael.jwrap.util.Characters;
import net.bervini.rasael.jwrap.util.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.stream.IntStream;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

@ParametersAreNullableByDefault
public abstract class AbstractCharSequenceWrap<
    ACTUAL extends CharSequence,
    SELF extends AbstractCharSequenceWrap<ACTUAL, SELF>>
    extends AbstractWrap<ACTUAL, SELF> implements CharSequence {

  protected AbstractCharSequenceWrap(@Nullable ACTUAL obj) {
    super(obj);
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Tested
  public boolean isNullOrEmpty() {
    return value==null || value.length()==0;
  }

  public boolean isBlank() {
    return Strings.isBlank(value);
  }

  @Tested
  public ObjectArrayWrap<String> split() {
      return Wrap(Strings.split(value));
  }

  public ObjectArrayWrap<String> splitByCharacterType() {
    return Wrap(Strings.splitByCharacterType(value));
  }

  @Tested
  public ObjectArrayWrap<String> splitByCamelCase() {
    return Wrap(Strings.splitByCamelCase(value));
  }

  @Tested
  public ObjectArrayWrap<String> split(char separator) {
    return Wrap(Strings.split(value, separator));
  }

  public ListWrap<String> splitQuoted() {
    return splitQuoted('"');
  }

  public ListWrap<String> splitQuoted(char quoteChar) {
    return Wrap(Strings.splitQuoted(value, quoteChar));
  }

  @Tested
  public boolean startsWith(CharSequence prefix) {
    return Strings.startsWith(value ,prefix);
  }

  @Tested
  public boolean startsWithIgnoreCase(CharSequence prefix) {
    return Strings.startsWithIgnoreCase(value, prefix);
  }

  public boolean endsWith(CharSequence prefix) {
    return Strings.endsWith(value ,prefix);
  }

  @Tested
  public boolean isStartOf(CharSequence suffix) {
    return Strings.startsWith(suffix, value);
  }

  @Tested
  public boolean isEndOf(CharSequence suffix) {
    return Strings.endsWith(suffix, value);
  }

  @Tested
  public boolean endsWithIgnoreCase(CharSequence prefix) {
    return Strings.endsWithIgnoreCase(value, prefix);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // CharSequence implementation
  // -------------------------------------------------------------------------------------------------------------------

  public int size() {
    return length();
  }

  @Override
  public int length() {
    return CharSequences.length(value);
  }

  @Override
  public char charAt(int index) {
    Character c = CharSequences.charAt(value, index);
    return c!=null ? c : Nulls.rule().get(c);
  }


  /**
   * <p>Returns a String representing the character at the given {@code index}.
   *
   * <p>This method accepts negative indexes, which are computed from the end of this CharSequence
   * @param index the index in this string, which can be negative
   */
  public String at(int index) {
    return Characters.toString(CharSequences.at(value, index));
  }

  @Override
  public boolean isEmpty() {
    return CharSequences.isEmpty(value);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return CharSequences.subSequence(value, start, end);
  }

  @Override
  public IntStream chars() {
    return CharSequences.chars(value);
  }

  @Override
  public IntStream codePoints() {
    return CharSequences.codePoints(value);
  }

  @Nonnull
  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Beta
  public boolean containsHtml5() {
    return CharSequences.containsHtml5Tags(value);
  }

  public SELF writeTo(Path path, OpenOption... options) throws IOException {
    if (path!=null && value!=null) {
      Files.writeString(path, value, options);
    }
    return self();
  }
}
