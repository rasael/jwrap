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

public class LongWrap extends AbstractNumberWrap<Long, LongWrap> {

  LongWrap(@Nullable Long value) {
    super(value);
  }

  @Override
  protected LongWrap self() {
    return this;
  }

  @Override
  Replicator<Long, LongWrap> replicator() {
    return LongWrap::new;
  }

  @Override
  Long zero() {
    return 0L;
  }

  @Override
  Long one() {
    return 1L;
  }

  @Override
  Long two() {
    return 2L;
  }

  @Override
  Long absDiff(Long value, Long other) {
    return Math.abs(value - other);
  }

  @Override
  Long abs(Long value) {
    return value!=null ? Math.abs(value) : null;
  }

  @Override
  protected Long mod(Long value, Long other) {
    return value%other;
  }

  @Override
  boolean isGreaterThan(Long value, Long other) {
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
  public LongWrap asLong() {
    return myself;
  }

  @Override
  public Long longValue() {
    return get();
  }
}
