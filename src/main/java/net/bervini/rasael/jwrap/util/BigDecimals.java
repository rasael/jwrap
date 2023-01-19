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

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimals {

  public static final BigDecimal MINUS_ONE = new BigDecimal(-1);

  private BigDecimals(){}

  public static BigDecimal toBigDecimal(Number n) {
    if (n==null) {
      return null;
    }
    else if (n instanceof BigDecimal bigDecimal) {
      return bigDecimal;
    }
    else if (n instanceof BigInteger bigInteger) {
      return new BigDecimal(bigInteger);
    }
    else {
      return !(n instanceof Integer) && !(n instanceof Long) && !(n instanceof Byte) && !(n instanceof Short) ? new BigDecimal(n.toString()) : BigDecimal.valueOf(n.longValue());
    }
  }

  public static boolean isNumber(String s) {
    try {
      new BigDecimal(s);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  public static BigDecimal mod(BigDecimal value, BigDecimal mod) {
    return value.remainder(mod);
  }

  public static BigDecimal negate(BigDecimal value) {
    return MINUS_ONE.multiply(value);
  }
}
