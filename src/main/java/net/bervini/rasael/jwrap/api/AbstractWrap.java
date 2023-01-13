/*
 * Copyright 2022-2023 Rasael Bervini
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bervini.rasael.jwrap.annotation.Beta;
import net.bervini.rasael.jwrap.annotation.Tested;
import net.bervini.rasael.jwrap.util.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

@ParametersAreNullableByDefault
public abstract class AbstractWrap<T, SELF extends AbstractWrap<T, SELF>> implements Supplier<T>, NonPrimitiveWrap {

  protected final SELF myself;

  @Nullable
  protected T value;

  protected AbstractWrap(T value) {
    Preconditions.checkArgument(!(value instanceof AbstractWrap), "Cannot wrap-a-wrap for <%s>", value);
    this.value = value;
    this.myself = self();
  }

  abstract SELF self();

  abstract Replicator<T, SELF> replicator();

  // -----------------------------------------------------------------------------------------------------------------

  /**
   * Returns the best string representation of the wrapped Object.
   * @see String#valueOf(Object)
   */
  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Beta
  @Override
  public final boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    AbstractWrap<?, ?> wrapBase = (AbstractWrap<?, ?>) o;
    return Objects.equals(value, wrapBase.value);
  }

  @Beta
  @Override
  public final int hashCode() {
    return Objects.hashCode(value);
  }


  // -----------------------------------------------------------------------------------------------------------------

  SELF setNull() {
    return set(null);
  }

  /**
   * @implNote currently this is not yet confirmed that will stay public, it seem counterintuitive
   */
  @Beta
  public SELF set(@Nullable T value) {
    // Note: if we don't chang this.value, then we could allow for constants wrap instances, which is useful of constant
    // instances like nulls or unmodifiable empty collections.
    // However, this means that every method may or may not return a new instance, which does not represent a clear API.
    // example:
    // var l = $(list).add("something");
    // System.out.println(l.add(" else").toString());
    //
    // intuitively, this should print 'something else'. Then add() must return the same instance, not a new one.
    this.value = value;
    return myself;
//    return replicator().apply(value);
  }

/*
  SELF with(E value) {
    return replicator().apply(value);
  }
*/
  @Nullable
  @Override
  public T get() {
    return value;
  }

  @Tested
  public final SELF or(T or) {
    return value!=null ? myself : replicator().apply(or);
  }

  @Tested
  public final T orElse(T orElse) {
    return value!=null ? value : orElse;
  }

  @Tested
  public final T orElseGet(@Nonnull Supplier<? extends T> supplier) {
    return value!=null ? value : supplier.get();
  }

  public final SELF orElseFill(@Nonnull Supplier<? extends T> supplier) {
    if (value==null) {
      return set(supplier.get());
    }
    return myself;
  }

  @Tested
  public final T orElseThrow() {
    if (value==null) {
      throw new NullPointerException();
    }
    return value;
  }

  public final <X extends Throwable> T orElseThrow(@Nonnull Supplier<? extends X> exceptionSupplier) throws X {
    if (value==null) {
      throw exceptionSupplier.get();
    }
    return value;
  }

  @Tested
  public SELF ifNull(@Nonnull Runnable action) {
    if (value==null) {
      action.run();
    }
    return myself;
  }

  @Tested
  public SELF ifNotNull(@Nonnull Consumer<? super T> action) {
    if (value!=null) {
      action.accept(value);
    }
    return myself;
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Override
  @Tested
  public boolean isNull() {
    return value==null;
  }

  @Override
  @Tested
  public boolean isNotNull() {
    return value!=null;
  }

  @Tested
  public boolean isSameAs(T other) {
    return value==other;
  }

  @Tested
  public boolean isEqualTo(T other) {
    return Objects.equals(value, other);
  }

  @Tested
  public boolean isNotEqualTo(T other) {
    return !Objects.equals(value, other);
  }

  @Tested
  public boolean hasHashCode(long hashCode) {
    return Objects.hashCode(value)==hashCode;
  }

  @Beta
  @Tested
  public StringWrap json() {
    return Wrap(gson.toJson(value));
  }

  @Beta
  @Tested
  public StringWrap json(boolean prettyPrint) {
    return Wrap(prettyPrint ? prettyPrintingJson.toJson(value) : gson.toJson(value));
  }

  public SELF fromJson(Class<T> aClass, String json) {
    Preconditions.requireArgNonNull(aClass);

    return set(gson.fromJson(json, aClass));
  }

  @Beta
  public SELF deepCopy() {
    if (value==null)
      return myself;

    @SuppressWarnings("unchecked")
    Class<T> ofType = (Class<T>) value.getClass();
    return fromJson(ofType, json().get());
  }

  private static final Gson gson = new GsonBuilder().create();
  private static final Gson prettyPrintingJson = new GsonBuilder().setPrettyPrinting().create();

  // -------------------------------------------------------------------------------------------------------------------

  public StringWrap asString() {
    return Wrap(String.valueOf(value));
  }

//  public Stream<E> asStream() {
//    return Stream.ofNullable(value);
//  }
}
