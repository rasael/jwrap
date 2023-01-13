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

import net.bervini.rasael.jwrap.api.Nulls;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ParametersAreNullableByDefault
public class Strings {

  public static final String NULL_AS_STRING = String.valueOf((Object)null);

  public static final String NULL = null;

  public static final String EMPTY = "";

  private static final String[] EMPTY_ARRAY = {};

  private Strings(){}

  // -------------------------------------------------------------------------------------------------------------------

  // -------------------------------------------------------------------------------------------------------------------

  public static boolean startsWith(CharSequence value, CharSequence prefix) {
    return StringUtils.startsWith(value, prefix);
  }

  public static boolean startsWithIgnoreCase(CharSequence value, CharSequence prefix) {
    return StringUtils.startsWithIgnoreCase(value, prefix);
  }

  public static boolean endsWith(CharSequence value, CharSequence prefix) {
    return StringUtils.endsWith(value, prefix);
  }

  public static boolean endsWithIgnoreCase(CharSequence value, CharSequence prefix) {
    return StringUtils.endsWithIgnoreCase(value, prefix);
  }

  public static String[] split(CharSequence csq) {
    if (csq==null) return EMPTY_ARRAY;
    return StringUtils.split(csq.toString());
  }

  public static String[] split(CharSequence csq, char sep) {
    if (csq==null) return EMPTY_ARRAY;
    return StringUtils.split(csq.toString(), sep);
  }

  public static String[] splitByCharacterType(CharSequence csq) {
    if (csq==null) return EMPTY_ARRAY;
    return StringUtils.splitByCharacterType(csq.toString());
  }

  public static String[] splitByCamelCase(CharSequence csq) {
    if (csq==null) return EMPTY_ARRAY;
    return StringUtils.splitByCharacterTypeCamelCase(csq.toString());
  }

  public static boolean isBlank(CharSequence value) {
    return StringUtils.isBlank(value);
  }

  public static int compare(CharSequence a, CharSequence b) {
    return StringUtils.compare(orNull(a), orNull(b), Nulls.rule().nullIsLess());
  }

  public static String orNull(CharSequence s) {
    return s!=null ? s.toString() : null;
  }

  public static String orEmpty(CharSequence s) {
    return s!=null ? s.toString() : EMPTY;
  }

  public static int compareIgnoreCase(CharSequence a, CharSequence b) {
    return StringUtils.compareIgnoreCase(orNull(a), orNull(b), Nulls.rule().nullIsLess());
  }

  public static String trim(String value) {
    if (value==null)
      return null;

    return value.trim();
  }

  public static String trimToEmpty(String value) {
    return StringUtils.trimToEmpty(value);
  }

  public static String trimToNull(String value) {
    return StringUtils.trimToNull(value);
  }

  private static final Map<Character,Pattern> splitQuotedCache = new ConcurrentHashMap<>();

  public static List<String> splitQuoted(CharSequence str, char quoteChar) {
    var list = new ArrayList<String>();
    if (str!=null) {
      Pattern pattern = splitQuotedCache.computeIfAbsent(quoteChar, c -> {
        var q = Pattern.quote(Character.toString(quoteChar));
        return Pattern.compile("([^" + q + "]\\S*|" + q + ".+?" + q+ ")\\s*");
      });

      Matcher m = pattern.matcher(str);
      while(m.find())
        list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.
    }

    return list;
  }

  public static String concat(String str, CharSequence... sequences) {
    if (sequences==null)
      return str; // nothing to concat

    if (str==null)
      str = Strings.NULL_AS_STRING;

    for (CharSequence cseq : sequences) {
      str = str.concat(String.valueOf(cseq));
    }

    return str;
  }
}
