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
import java.util.function.Predicate;

public class PredicateWrap<T> extends AbstractPredicateWrap<T, PredicateWrap<T>> {

  protected PredicateWrap(@Nullable Predicate<T> value) {
    super(value);
  }

  @Override
  protected PredicateWrap<T> self() {
    return this;
  }

  @Override
  Replicator<Predicate<T>, PredicateWrap<T>> replicator() {
    return PredicateWrap::new;
  }
}
