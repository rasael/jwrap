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

import java.awt.Point;
import java.awt.Rectangle;

public class RectangleWrap extends AbstractWrap<Rectangle, RectangleWrap> {

  protected RectangleWrap(Rectangle rectangle) {
    super(rectangle);
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Override
  RectangleWrap self() {
    return this;
  }

  @Override
  Replicator<Rectangle, RectangleWrap> replicator() {
    return RectangleWrap::new;
  }

  // -------------------------------------------------------------------------------------------------------------------

  public PointWrap getCenterPoint() {
    if (value==null)
      return new PointWrap(null);

    return new PointWrap(new Point((int)value.getCenterX(), (int)value.getCenterY()));
  }
}
