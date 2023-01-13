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

import net.bervini.rasael.jwrap.data.GraphicsContext;
import net.bervini.rasael.jwrap.util.GraphicsUtilities;

import java.awt.Graphics;
import java.util.function.Consumer;

public abstract class AbstractGraphicsWrap<G extends Graphics, SELF extends AbstractGraphicsWrap<G, SELF>>
    extends AbstractWrap<G, SELF> {


  protected AbstractGraphicsWrap(G value) {
    super(value);
  }

  public void drawAndRestore(Consumer<? super G> renderer) {
    if (value==null || renderer==null)
      return;

    G scratchGraphics = (G) value.create();
    try {
      renderer.accept(scratchGraphics);
    }
    finally {
      scratchGraphics.dispose();
    }

    /*GraphicsContext<G> context = save();
    try {
      renderer.accept(value);
    }
    finally {
      context.restore(value);
    }*/
  }

  public GraphicsContext<G> save() {
    if (value==null)
      return null;

    return GraphicsUtilities.saveContext(value);
  }


}
