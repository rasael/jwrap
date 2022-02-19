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

import net.bervini.rasael.jwrap.annotation.Beta;

import java.util.Arrays;
import java.util.function.Predicate;

@Beta
public class Comparison {

  private Comparison(){}
  
  // -------------------------------------------------------------------------------------------------------------------
  public static <T> Predicate<T> isEqualTo(Object obj) {
    return t -> areEqual(t, obj);
  }

  public static boolean areEqual(Object obj, Object other) {
    if (obj==null) {
      return other==null;
    }
    else {
      Class<?> actualClass = obj.getClass();
      if (actualClass.isArray() && other!=null) {
        Class<?> otherClass = other.getClass();
        if (otherClass.isArray()) {
          if (actualClass.getComponentType().isPrimitive() && otherClass.getComponentType().isPrimitive()) {
            if (obj instanceof byte[] a && other instanceof byte[]  b) {
              return java.util.Arrays.equals(a, b);
            }

            if (obj instanceof short[] a && other instanceof short[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof int[] a && other instanceof int[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof long[] a && other instanceof long[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof char[] a && other instanceof char[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof float[] a && other instanceof float[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof double[] a && other instanceof double[] b) {
              return java.util.Arrays.equals(a,  b);
            }

            if (obj instanceof boolean[] a && other instanceof boolean[] b) {
              return java.util.Arrays.equals(a,  b);
            }
          }

          if (obj instanceof Object[] a && other instanceof Object[] b) {
            return Arrays.deepEquals(a,  b);
          }
        }
      }

      return obj.equals(other);
    }
  }
}
