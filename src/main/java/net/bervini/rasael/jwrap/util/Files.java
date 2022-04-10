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

package net.bervini.rasael.jwrap.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class Files {

  private Files(){}

  // -------------------------------------------------------------------------------------------------------------------

  public static boolean isEmptyDirectory(File file) {
    if (file==null) return true;
    return Objects.requireNonNull(file.listFiles()).length==0;
  }

  public static boolean containsFiles(File file, boolean recursive) {
    if (file==null || file.isFile())
      return false;

    if (!Arrays.isEmpty(file.listFiles(File::isFile)))
      return true;

    if (recursive) {
      return Streams.stream(file.listFiles(File::isDirectory))
                    .anyMatch(dir -> containsFiles(dir, true));
    }

    return false;
  }

  public static boolean exists(File value) {
    return value!=null && value.exists();
  }

  public static boolean isFile(File value) {
    return value!=null && value.isFile();
  }

  public static boolean isDirectory(File value) {
    return value!=null && value.isDirectory();
  }

  public static boolean isEmptyFile(File value) {
    return exists(value) && value.isFile() && value.length()==0;
  }

  public static boolean touch(File file) {
    if (file==null)
      return false;

    if (file.exists())
      return true;

    if (!file.canWrite())
      return false;

    try {
      return file.createNewFile();
    }
    catch (IOException e) {
      return false;
    }
  }

  public static Path toPath(File file) {
    return file!=null ? file.toPath() : null;
  }
}
