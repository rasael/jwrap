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

import javax.annotation.ParametersAreNullableByDefault;
import java.util.stream.Stream;

@ParametersAreNullableByDefault
public class StreamWrap<T> extends AbstractStreamWrap<T, StreamWrap<T>> {

  protected StreamWrap(Stream<T> value) {
    super(value);
  }

  @Override
  StreamWrap<T> self() {
    return this;
  }

  @Override
  Replicator<Stream<T>, StreamWrap<T>> replicator() {
    return StreamWrap::new;
  }

  @Override
  public StreamWrap<T> stream() {
    return this;
  }
}
