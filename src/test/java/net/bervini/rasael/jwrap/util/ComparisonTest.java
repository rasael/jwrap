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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComparisonTest {

  @Test
  void areEqualWithInt() {
    assertThat(Comparison.areEqual(1,1)).isTrue();
    assertThat(Comparison.areEqual(1,0)).isFalse();
    assertThat(Comparison.areEqual(0,1)).isFalse();
  }
  
  @Test
  void areEqualWithNull() {
    assertThat(Comparison.areEqual(null, new Object())).isFalse();
    assertThat(Comparison.areEqual(new Object(), null)).isFalse();
    assertThat(Comparison.areEqual(null, null)).isTrue();
  }

  @Test
  void areIntArrayEqual() {
    int[] a = {1,2,3};
    int[] b = {1,2,3};
    int[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areLongArrayEqual() {
    long[] a = {1,2,3};
    long[] b = {1,2,3};
    long[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areByteArrayEqual() {
    byte[] a = {1,2,3};
    byte[] b = {1,2,3};
    byte[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areCharArrayEqual() {
    char[] a = {1,2,3};
    char[] b = {1,2,3};
    char[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areDoubleArrayEqual() {
    double[] a = {1,2,3};
    double[] b = {1,2,3};
    double[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areShortArrayEqual() {
    short[] a = {1,2,3};
    short[] b = {1,2,3};
    short[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areFloatArrayEqual() {
    float[] a = {1,2,3};
    float[] b = {1,2,3};
    float[] c = {2,3,4};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areBooleanArrayEqual() {
    boolean[] a = {true,true,false};
    boolean[] b = {true,true,false};
    boolean[] c = {false,false,true};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }

  @Test
  void areStringArrayEqual() {
    String[] a = {"1","2","3"};
    String[] b = {"1","2","3"};
    String[] c = {"2","3","4"};
    assertThat(Comparison.areEqual(a,b)).isTrue();
    assertThat(Comparison.areEqual(a,c)).isFalse();
  }
}