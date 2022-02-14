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

import javax.annotation.Nullable;

public class DoubleWrap extends AbstractNumberWrap<Double, DoubleWrap> {

  DoubleWrap(@Nullable Double value) {
    super(value);
  }

  @Override
  protected DoubleWrap self() {
    return this;
  }

  @Override
  Replicator<Double, DoubleWrap> replicator() {
    return DoubleWrap::new;
  }

  @Override
  Double zero() {
    return 0D;
  }

  @Override
  Double one() {
    return 1D;
  }

  @Override
  Double two() {
    return 2D;
  }

  @Override
  Double absDiff(Double value, Double other) {
    return Math.abs(value - other);
  }

  @Override
  Double abs(Double value) {
    return value!=null ? Math.abs(value) : value;
  }

  @Override
  protected Double mod(Double value, Double other) {
    return value%other;
  }

  @Override
  boolean isGreaterThan(Double value, Double other) {
    return value > other;
  }

  @Override
  public DoubleWrap asDouble() {
    return myself;
  }

  @Override
  public Double doubleValue() {
    return get();
  }
}
