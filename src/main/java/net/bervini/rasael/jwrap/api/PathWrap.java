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

import net.bervini.rasael.jwrap.util.Iterables;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Iterator;

public class PathWrap extends AbstractComparableWrap<Path, PathWrap>
    implements ObjectIterableWrap<Path, Path, PathWrap> {

  public PathWrap(Path path) {
    super(path);
  }

  @Override
  PathWrap self() {
    return this;
  }

  @Override
  Replicator<Path, PathWrap> replicator() {
    return PathWrap::new;
  }

  @Override
  public int size() {
    return Iterables.size(value);
  }

  @Override
  public boolean contains(@Nullable Iterable<Path> iterable) {
    return Iterables.contains(value, iterable);
  }

  @NotNull
  @Override
  public Iterator<Path> iterator() {
    return Iterables.iterator(value);
  }
}
