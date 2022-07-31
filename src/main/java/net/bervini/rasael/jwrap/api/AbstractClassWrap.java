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

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public abstract class AbstractClassWrap<T, SELF extends AbstractClassWrap<T, SELF>>
    extends AbstractWrap<Class<T>, SELF>
    implements AbstractAnnotatedElement<SELF>,
               AbstractModifiableElement<SELF> {

  protected AbstractClassWrap(Class<T> type) {
    super(type);
  }

  // -------------------------------------------------------------------------------------------------------------------

  public StringWrap getDisplayName() {
    return new StringWrap(className(value));
  }

  private String className(Class<?> type) {
    if (type==null)
      return null;

    if (type.isArray()) {
      return className(type.componentType()) + "[]";
    }
    return type.getSimpleName();
  }

  @Override
  public OptionalInt modifiers() {
    return value==null ? OptionalInt.empty() : OptionalInt.of(value.getModifiers());
  }


  public boolean hasDefaultConstructor() {
    return hasConstructor();
  }

  public boolean hasConstructor(Class<?>...parameterTypes) {
    return getConstructor(parameterTypes).isPresent();
  }

  public Optional<Constructor<T>> getDefaultConstructor() {
    return getConstructor();
  }

  public Optional<Constructor<T>> getConstructor(Class<?>...parameterTypes) {
    if (value==null)
      return Optional.empty();

    try {
      return Optional.of(value.getConstructor(parameterTypes));
    }
    catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }

  public T newInstance() throws ReflectiveOperationException {
    if (!hasDefaultConstructor())
      return null;

    return this.getDefaultConstructor()
               .orElseThrow()
               .newInstance();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // AnnotatedElement implementation
  // -------------------------------------------------------------------------------------------------------------------

  @Override
  public Annotation[] getDeclaredAnnotations() {
    if (value==null)
      return NO_ANNOTATIONS;

    return value.getDeclaredAnnotations();
  }

  @Override
  public <A extends Annotation> A getAnnotation(@NotNull Class<A> annotationClass) {
    if (value==null)
      return null;

    return value.getAnnotation(annotationClass);
  }

  public <A extends Annotation> Stream<Method> getDeclaredMethodsAnnotatedWith(Class<A> annotationClass) {
    if (value==null)
      return Stream.empty();

    return Arrays.stream(value.getDeclaredMethods())
                 .filter(e -> e.isAnnotationPresent(annotationClass));
  }

  public <A extends Annotation> Stream<Method> getMethodsAnnotatedWith(Class<A> annotationClass) {
    if (value==null)
      return Stream.empty();

    return Arrays.stream(value.getMethods())
                 .filter(e -> e.isAnnotationPresent(annotationClass));
  }

  @Override
  public Annotation[] getAnnotations() {
    if (value==null)
      return NO_ANNOTATIONS;

    return value.getAnnotations();
  }
}
