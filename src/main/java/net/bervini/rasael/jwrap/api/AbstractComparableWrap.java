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

import javax.annotation.Nullable;

public abstract class AbstractComparableWrap<
    COMPARABLE extends Comparable<? super COMPARABLE>,
    SELF extends AbstractComparableWrap<COMPARABLE, SELF>>
    extends AbstractWrap<COMPARABLE, SELF> {

  protected AbstractComparableWrap(@Nullable COMPARABLE obj) {
    super(obj);
  }

  @Tested
  public boolean isLessThan(@Nullable COMPARABLE other) {
    return value!=null && other!=null && value.compareTo(other) < 0;
  }

  @Tested
  public boolean isLessThanOrEqualTo(@Nullable COMPARABLE other) {
    return value!=null && other!=null && value.compareTo(other) <= 0;
  }

  @Tested
  public boolean isGreaterThan(@Nullable COMPARABLE other) {
    return value!=null && other!=null && value.compareTo(other) > 0;
  }

  public boolean isGreaterThanOrEqualTo(@Nullable COMPARABLE other) {
    return value!=null && other!=null &&  value.compareTo(other) >= 0;
  }

  public boolean isNotEqualByComparisonTo(@Nullable COMPARABLE other) {
    if (value==null || other==null)
      return value!=other;

    return value.compareTo(other)!=0;
  }

  public boolean isEqualByComparisonTo(@Nullable COMPARABLE other) {
    if (value==null || other==null)
      return value==other;

    return value.compareTo(other)==0;
  }
}
