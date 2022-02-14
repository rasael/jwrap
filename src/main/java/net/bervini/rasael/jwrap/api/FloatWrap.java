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

public class FloatWrap extends AbstractNumberWrap<Float, FloatWrap> {

  FloatWrap(@Nullable Float value) {
    super(value);
  }

  @Override
  protected FloatWrap self() {
    return this;
  }

  @Override
  Replicator<Float, FloatWrap> replicator() {
    return FloatWrap::new;
  }

  @Override
  Float zero() {
    return 0f;
  }

  @Override
  Float one() {
    return 1f;
  }

  @Override
  Float two() {
    return 2f;
  }

  @Override
  Float absDiff(Float value, Float other) {
    return Math.abs(value - other);
  }

  @Override
  Float abs(Float value) {
    return value!=null ? Math.abs(value) : value;
  }

  @Override
  protected Float mod(Float value, Float other) {
    return value%other;
  }

  @Override
  boolean isGreaterThan(Float value, Float other) {
    return value > other;
  }

  @Override
  public FloatWrap asFloat() {
    return myself;
  }

  @Override
  public Float floatValue() {
    return get();
  }
}
