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

import net.bervini.rasael.jwrap.util.Strings;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.stream.Stream;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

public abstract class AbstractStringWrap<SELF extends AbstractStringWrap<SELF>> extends AbstractCharSequenceWrap<String, SELF>
    implements Comparable<String> {

  protected AbstractStringWrap(@Nullable String obj) {
    super(obj);
  }

  public SELF reversed() {
    if (value==null)
      return myself;

    return set(StringUtils.reverse(value));
  }

  @Override
  public boolean isEmpty() {
    return value==null || value.isEmpty();
  }

  @Override
  public boolean isBlank() {
    return value==null || value.isBlank();
  }

  public int compareIgnoreCase(@Nullable CharSequence b) {
    return Strings.compareIgnoreCase(value, b);
  }

  @Override
  public int compareTo(@Nullable String b) {
    return compare(b);
  }

  public int compare(@Nullable CharSequence b) {
    return Strings.compare(value, b);
  }

  public SELF trim() {
    return set(Strings.trim(value));
  }

  public SELF trimToEmpty() {
    return set(Strings.trimToEmpty(value));
  }

  public SELF trimToNull() {
    return set(Strings.trimToNull(value));
  }

  public SELF concat(CharSequence...sequences) {
    return set(Strings.concat(value, sequences));
  }

  public StreamWrap<String> lines() {
    return Wrap(value!=null ? value.lines() : Stream.empty());
  }

  public SELF removeEnding(String end) {
    if (value==null || end==null)
      return myself;

    while(value.endsWith(end)) {
      set(value.substring(0, value.length() - end.length()));
    }

    return myself;
  }

  public SELF removeEnd(String end) {
    if (value==null || end==null)
      return myself;

    if (value.endsWith(end)) {
      set(value.substring(0, value.length() - end.length()));
    }

    return myself;
  }

}
