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

import javax.annotation.ParametersAreNullableByDefault;
import java.util.stream.IntStream;

@ParametersAreNullableByDefault
public class CharSequences {

  private CharSequences(){}

  // -------------------------------------------------------------------------------------------------------------------

  public static int length(CharSequence value) {
    if (value==null)
      return 0;

    return value.length();
  }

  public static Character charAt(CharSequence value, int index) {
    if (value==null)
      return null;

    return value.charAt(index);
  }

  public static boolean isEmpty(CharSequence value) {
    if (value==null)
      return true;

    return value.isEmpty();
  }

  public static CharSequence subSequence(CharSequence value, int start, int end) {
    if (value==null)
      return null;

    return value.subSequence(start, end);
  }

  public static IntStream chars(CharSequence value) {
    if (value==null)
      return null;

    return value.chars();
  }

  public static IntStream codePoints(CharSequence value) {
    if (value==null)
      return null;

    return value.codePoints();
  }

  public static Character at(CharSequence value, int index) {
    if (value==null)
      return null;

    index = resolveNegativeIndexes(index, value.length());
    if (index==-1)
      return null;

    return value.charAt(index);
  }

  private static int resolveNegativeIndexes(int index, int length) {
    if (index<0)
      index = length + index; // negative indexes start from the end

    if (index<0 || index>=length)
      return -1;

    return index;
  }
}
