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
import net.bervini.rasael.jwrap.data.Offset;
import net.bervini.rasael.jwrap.data.Percentage;
import net.bervini.rasael.jwrap.util.Preconditions;
import org.apache.commons.lang3.compare.ComparableUtils;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Objects;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

public abstract class AbstractNumberWrap<NUMBER extends Number & Comparable<NUMBER>,
    SELF extends AbstractNumberWrap<NUMBER, SELF>>
    extends AbstractComparableWrap<NUMBER, SELF> {

  protected AbstractNumberWrap(@Nullable NUMBER value) {
    super(value);
  }

  // -------------------------------------------------------------------------------------------------------------------

  boolean areEqual(@Nullable NUMBER value, @Nullable NUMBER other) {
    return Objects.equals(value, other);
  }

  boolean isGreaterThanOrEqualTo(@Nullable NUMBER value, @Nullable NUMBER other) {
    return areEqual(value, other) || this.isGreaterThan(value, other);
  }

  abstract boolean isGreaterThan(@Nullable NUMBER value, @Nullable NUMBER other);

  abstract NUMBER zero();

  abstract NUMBER one();

  abstract NUMBER two();

  abstract NUMBER absDiff(NUMBER value, NUMBER other);

  abstract NUMBER abs(NUMBER value);

  protected abstract NUMBER mod(NUMBER value, NUMBER other);

  // -------------------------------------------------------------------------------------------------------------------

  public boolean isEven() {
    return value!=null && mod(abs(value), two()).compareTo(zero())==0;
  }

  public boolean isOdd() {
    return value!=null && mod(abs(value), two()).compareTo(zero())!=0;
  }

  @Tested
  public boolean isPositive() {
    return isGreaterThan(zero());
  }

  public boolean isNotNegative() {
    return isNull() || isGreaterThanOrEqualTo(zero());
  }

  public boolean isNotPositive() {
    return isNull() || isLessThanOrEqualTo(zero());
  }

  @Tested
  public boolean isZero() {
    return isEqualTo(zero());
  }

  public boolean isNotZero() {
    return isNotEqualTo(zero());
  }

  public boolean isOne() {
    return value!=null && value.compareTo(one())==0;
  }

  public boolean isBetween(NUMBER from, NUMBER to) {
    return value!=null && ComparableUtils.between(from, to).test(value);
  }

  public boolean isStrictlyBetween(NUMBER from, NUMBER to) {
    return value!=null && ComparableUtils.betweenExclusive(from, to).test(value);
  }

  public boolean isCloseTo(@Nullable NUMBER other, Offset<NUMBER> offset) {
    if (value==null || other==null) {
      return false;
    }

    if (!areEqual(value, other)) {
      if (!offset.strict() && this.isGreaterThan(this.absDiff(value, other), offset.value())) {
        return false;
      }
      else if (offset.strict() && this.isGreaterThanOrEqualTo(this.absDiff(value, other), offset.value())) {
        return false;
      }
    }

    return true;
  }

  public boolean isNotCloseTo(@Nullable NUMBER other, Offset<NUMBER> offset) {
    if (value==null || other==null) {
      return true;
    }

    Preconditions.requireArgNonNull(offset);
    NUMBER diff = absDiff(value, other);
    if (!offset.strict() || !this.isGreaterThanOrEqualTo(diff, offset.value())) {
      if (offset.strict() || this.areEqual(value, other) || !this.isGreaterThan(diff, offset.value())) {
        return false;
      }
    }

    return true;
  }


  public boolean isCloseTo(NUMBER other, Percentage percentage) {
    if (areEqual(value, other)) {
      return true;
    }

    double acceptableDiff = Math.abs(percentage.value() * other.doubleValue() / 100.0D);
    double actualDiff = this.absDiff(value, other).doubleValue();
    return !(actualDiff > acceptableDiff)
        && !Double.isNaN(actualDiff)
        && !Double.isInfinite(actualDiff);
  }


  public boolean isNotCloseTo(NUMBER other, Percentage percentage) {
    Preconditions.requireArgNonNull(percentage);
    double diff = Math.abs(percentage.value() * other.doubleValue() / 100.0D);
    boolean areEqual = this.areEqual(value, other);
    if (areEqual || !Double.isInfinite(diff)) {
      if (this.absDiff(value, other).doubleValue() <= diff || areEqual) {
        return false;
      }
    }
    return true;
  }

  @Tested
  public boolean isNegative() {
    return isLessThan(zero());
  }

  // -------------------------------------------------------------------------------------------------------------------

  public SELF orZero() {
    return value==null ? replicator().apply(zero()) : self();
  }

  public Integer intValue() {
    return value!=null ? value.intValue() : null;
  }

  public IntWrap asInt() {
    return Wrap(value!=null ? value.intValue() : null);
  }

  public Long longValue() {
    return value!=null ? value.longValue() : null;
  }

  public LongWrap asLong() {
    return Wrap(value!=null ? value.longValue() : null);
  }

  public Float floatValue() {
    return value!=null ? value.floatValue() : null;
  }

  public FloatWrap asFloat() {
    return Wrap(value!=null ? value.floatValue() : null);
  }

  public Double doubleValue() {
    return value!=null ? value.doubleValue() : null;
  }

  public DoubleWrap asDouble() {
    return Wrap(value!=null ? value.doubleValue() : null);
  }

  public BigDecimalWrap asBigDecimal() {
    return Wrap(value!=null ? new BigDecimal(value.toString()) : null);
  }

  public Byte byteValue() {
    if (value==null)
      return null;

    return (byte)value.intValue();
  }

  public Short shortValue() {
    if (value==null)
      return null;

    return (short)value.intValue ();
  }
}
