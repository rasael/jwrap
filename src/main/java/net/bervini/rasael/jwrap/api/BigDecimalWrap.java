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

package net.bervini.rasael.jwrap.api;

import net.bervini.rasael.jwrap.util.BigDecimals;

import javax.annotation.Nullable;
import java.math.BigDecimal;

public class BigDecimalWrap extends AbstractNumberWrap<BigDecimal, BigDecimalWrap> {

  public static final BigDecimal TWO = new BigDecimal(2);

  BigDecimalWrap(@Nullable BigDecimal value) {
    super(value);
  }

  @Override
  protected BigDecimalWrap self() {
    return this;
  }

  @Override
  Replicator<BigDecimal, BigDecimalWrap> replicator() {
    return BigDecimalWrap::new;
  }

  @Override
  BigDecimal zero() {
    return BigDecimal.ZERO;
  }

  @Override
  BigDecimal one() {
    return BigDecimal.ONE;
  }

  @Override
  BigDecimal two() {
    return TWO;
  }

  @Override
  BigDecimal absDiff(BigDecimal value, BigDecimal other) {
    return value.subtract(other).abs();
  }

  @Override
  BigDecimal abs(BigDecimal value) {
    return value!=null ? value.abs() : null;
  }

  @Override
  protected BigDecimal mod(BigDecimal value, BigDecimal mod) {
    return BigDecimals.mod(value, mod);
  }

  @Override
  boolean areEqual(@Nullable BigDecimal value, @Nullable BigDecimal other) {
    if (value==null) {
      return other==null;
    }
    else if (other==null) {
      return false;
    }
    else {
      return value.compareTo(other)==0;
    }
  }

  @Override
  protected boolean isGreaterThan(BigDecimal value, BigDecimal other) {
    return value.subtract(other).compareTo(BigDecimal.ZERO) > 0;
  }

  @Override
  public BigDecimalWrap asBigDecimal() {
    return myself;
  }
}
