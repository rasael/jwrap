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

public class FileWrap extends AbstractFileWrap<FileWrap> {

  public FileWrap(File file) {
    super(file);
  }

  @Override
  FileWrap self() {
    return this;
  }

  @Override
  Replicator<File, FileWrap> replicator() {
    return FileWrap::new;
  }

  public DirectoryWrap asDirectory() {
    return new DirectoryWrap(value!=null && value.isDirectory() ? value : null);
  }

  public boolean touch() {
    return Files.touch(value);
  }
}
