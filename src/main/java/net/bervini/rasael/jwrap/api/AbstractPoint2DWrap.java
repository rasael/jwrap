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

import net.bervini.rasael.jwrap.util.Points;

import java.awt.geom.Point2D;
import java.util.OptionalDouble;

public abstract class AbstractPoint2DWrap<T extends Point2D, SELF extends AbstractPoint2DWrap<T,SELF>>
    extends AbstractWrap<T, SELF> {


  protected AbstractPoint2DWrap(T value) {
    super(value);
  }

  public Point2DWrap<Point2D.Double> minus(Point2D point) {
    if (value==null)
      return new Point2DWrap<>(null);

    if (point==null)
      return asPoint2D();

    return new Point2DWrap<>(Points.difference(value, point));
  }

  public OptionalDouble abs() {
    return Points.abs(value);
  }

  public SELF translateX(int offsetX) {
    if (value!=null)
      value.setLocation(value.getX() + offsetX, value.getY());

    return self();
  }

  public SELF translateY(int offsetY) {
    if (value!=null)
      value.setLocation(value.getX(), value.getY() + offsetY);

    return self();
  }

  private Point2DWrap<Point2D.Double> asPoint2D() {
    var p2d = value!=null ? new Point2D.Double(value.getX(), value.getY()) : null;
    return new Point2DWrap<>(p2d);
  }
}
