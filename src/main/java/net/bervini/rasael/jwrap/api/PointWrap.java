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

public class PointWrap extends AbstractPoint2DWrap<Point, PointWrap> {

  protected PointWrap(Point value) {
    super(value);
  }

  // -------------------------------------------------------------------------------------------------------------------

  @Override
  PointWrap self() {
    return this;
  }

  @Override
  Replicator<Point, PointWrap> replicator() {
    return PointWrap::new;
  }

  // -------------------------------------------------------------------------------------------------------------------
  public Point translated(int dx, int dy) {
    if (value==null)
      return null;

    return new Point(value.x + dx, value.y + dy);
  }

  public PointWrap copy() {
    if (value==null)
      return this;

    return new PointWrap(value.getLocation());
  }

  public PointWrap plus(Point point) {
    if (value==null)
      return this;

    if (point!=null)
      value.setLocation(value.x+point.x, value.y+point.y);

    return this;
  }

  public PointWrap minus(Point point) {
    if (value==null)
      return this;

    if (point==null)
      return this;

    return new PointWrap(new Point(value.x-point.x, value.y-point.y));
  }
}
