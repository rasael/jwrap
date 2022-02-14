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

public class IntWrap extends AbstractNumberWrap<Integer, IntWrap> {

  IntWrap(@Nullable Integer value) {
    super(value);
  }

  @Override
  protected IntWrap self() {
    return this;
  }

  @Override
  Replicator<Integer, IntWrap> replicator() {
    return IntWrap::new;
  }

  @Override
  Integer zero() {
    return 0;
  }

  @Override
  Integer one() {
    return 1;
  }

  @Override
  Integer two() {
    return 2;
  }

  @Override
  Integer absDiff(Integer value, Integer other) {
    return Math.abs(value - other);
  }

  @Override
  Integer abs(Integer value) {
    return value!=null ? Math.abs(value) : null;
  }

  @Override
  protected Integer mod(Integer value, Integer other) {
    return value%other;
  }

  @Override
  boolean isGreaterThan(Integer value, Integer other) {
    return value > other;
  }

  @Override
  public boolean isEven() {
    return value!=null && (value & one())==zero();
  }

  @Override
  public boolean isOdd() {
    return value!=null && (value & one())!=zero();
  }

  @Override
  public IntWrap asInt() {
    return myself;
  }

  @Override
  public Integer intValue() {
    return get();
  }
}
