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

import net.bervini.rasael.jwrap.util.Lists;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

@ParametersAreNullableByDefault
public class ListWrap<ELEMENT> extends AbstractListWrap<List<ELEMENT>, ELEMENT, ListWrap<ELEMENT>>  {

  protected ListWrap(@Nullable List<ELEMENT> list) {
    super(list);
  }

  @Override
  ListWrap<ELEMENT> self() {
    return null;
  }

  @Override
  Replicator<List<ELEMENT>, ListWrap<ELEMENT>> replicator() {
    return ListWrap::new;
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Override
  public ListWrap<ELEMENT> pushAll(Iterable<ELEMENT> elements) {
    return set(Lists.addAll(value, elements));
  }

  @Override
  public ListWrap<ELEMENT> unshift(ELEMENT element) {
    return set(Lists.unshift(value, element));
  }
}
