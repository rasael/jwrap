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

import net.bervini.rasael.jwrap.util.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static net.bervini.rasael.jwrap.api.JWrap.Wrap;

public abstract class AbstractFileWrap<SELF extends AbstractFileWrap<SELF>> extends AbstractComparableWrap<File, SELF> {

  protected AbstractFileWrap(File file) {
    super(file);
  }

  public final boolean exists() {
    return Files.exists(value);
  }

  public final boolean isDirectory() {
    return Files.isDirectory(value);
  }

  public final boolean isFile() {
    return Files.isFile(value);
  }

  public final boolean isEmptyFile() {
    return Files.isEmptyFile(value);
  }

  public final boolean containsFiles() {
    return containsFiles(false);
  }

  public final boolean containsFilesRecursive() {
    return containsFiles(true);
  }

  public final boolean containsFiles(boolean recursive) {
    return Files.containsFiles(value, recursive);
  }

  public final SELF absoluteFile() {
    if (value==null)
      return myself;

    return set(value.getAbsoluteFile());
  }

  public final StringWrap absolutePath() {
    if (value==null)
      return Wrap((String) null);

    return Wrap(value.getAbsolutePath());
  }

  public final SELF canonicalFile() throws IOException {
    if (value==null)
      return myself;

    return set(value.getCanonicalFile());
  }

  public final Path toPath() {
    return Files.toPath(value);
  }

  public final PathWrap asPath() {
    return new PathWrap(Files.toPath(value));
  }
}

