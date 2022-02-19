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

import net.bervini.rasael.jwrap.annotation.Beta;

import java.io.Serializable;

/**
 * 'undefined' pseudotype. When used, it throws a ClassCastException for type 'undefined' to the expected type
 */
@Beta
class undefined implements Serializable {

  private undefined(){}

  private static class Singleton {
    private static final Object instance = new undefined();
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Public API
  // -------------------------------------------------------------------------------------------------------------------

  public static char character() {
    throw new UndefinedReferenceException("char");
  }

  public static <T> T get() {
    return (T) Singleton.instance;
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Override
  public boolean equals(Object obj) {
    throw new UndefinedReferenceException();
//    return this==obj;
  }

  @Override
  public int hashCode() {
    throw new UndefinedReferenceException();
//    return -1;
  }

  @Override
  public String toString() {
    throw new UndefinedReferenceException();
//    return "undefined";
  }

  // -------------------------------------------------------------------------------------------------------------------

}
