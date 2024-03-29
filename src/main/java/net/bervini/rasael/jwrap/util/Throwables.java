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

package net.bervini.rasael.jwrap.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class Throwables {

  private Throwables(){}

  public static String toString(Throwable thrown) {
    String throwable = "";
    if (thrown != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      pw.println();
      thrown.printStackTrace(pw);
      pw.close();
      throwable = sw.toString();
    }
    return throwable;
  }

  public static StringList collectAllMessages(Throwable throwable, boolean includeCurrent) {
    Objects.requireNonNull(throwable, "throwable");
    return collectMessages(throwable, includeCurrent);
  }

  private static StringList collectMessages(Throwable t, boolean add) {
    var list = StringList.newStringList();
    if (add) list.add(t.getMessage());

    for (var suppressed : t.getSuppressed()) {
      list.addAll(collectMessages(suppressed, true));
    }

    var cause = t.getCause();
    if (cause!=null) {
      list.addAll(collectMessages(cause, true));
    }

    return list;
  }
}
