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

import net.bervini.rasael.jwrap.util.Streams;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.Iterator;
import java.util.function.Supplier;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

@ParametersAreNullableByDefault
public abstract class AbstractSupplierWrap<T, SELF extends AbstractSupplierWrap<T,SELF>>
    extends AbstractWrap<Supplier<T>,SELF> {

  protected AbstractSupplierWrap(Supplier<T> value) {
    super(value);
  }

  public T getValue() {
    return value!=null ? value.get() : null;
  }

  public StreamWrap<T> infiniteStream() {
    return Wrap(Streams.generate(value));
  }

  public StreamWrap<T> stream(int size) {
    return Wrap(Streams.generate(value, size));
  }

  public Iterator<T> iterator(int size) {
    return stream(size).iterator();
  }

  public Iterator<T> infiniteIterator(){
    return infiniteStream().iterator();
  }
}
