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

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.OptionalDouble;

public class Points {

  public static final Point2D ORIGIN = new Point2D(){
    @Override
    public double getX() {
      return 0;
    }

    @Override
    public double getY() {
      return 0;
    }

    @Override
    public void setLocation(double x, double y) {
      throw new UnsupportedOperationException();
    }
  };

  private Points(){}

  public static OptionalDouble abs(Point2D point) {
    if (point==null)
      return OptionalDouble.empty();

    return OptionalDouble.of(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()));
  }

  public static Point add(Point point, int x, int y) {
    if (point==null)
      return null;

    point.x = point.x+x;
    point.y = point.y+y;

    return point;
  }

  public static Point add(Point point, Point addend) {
    if (point==null || addend==null)
      return null;

    return add(point, addend.x, addend.y);
  }

  public static Point2D add(Point2D point, double x, double y) {
    if (point==null)
      return null;

    point.setLocation(point.getX() + x, point.getY() + y);
    return point;
  }

  public static Point2D add(Point2D point, Point2D addend) {
    if (point==null || addend==null)
      return null;

    return add(point, addend.getX(),  addend.getY());
  }


  public static void subtract(Point point, Point subtrahend) {
    if (point==null || subtrahend==null)
      return;

    point.x = point.x-subtrahend.x;
    point.y = point.y-subtrahend.y;
  }

  public static void subtract(Point2D point, Point2D subtrahend) {
    if (point==null || subtrahend==null)
      return;

    point.setLocation(point.getX()-subtrahend.getX(), point.getY()-subtrahend.getY());
  }

  public static Point sum(Point a, Point b) {
    a = orOrigin(a);
    b = orOrigin(b);
    return new Point(a.x+b.x, a.y+b.y);
  }

  public static Point sum(Point a, int x, int y) {
    a = orOrigin(a);
    return new Point(a.x+x, a.y+y);
  }

  public static Point2D.Double sum(Point2D a, Point2D b) {
    a = orOrigin(a);
    b = orOrigin(b);
    return new Point2D.Double(a.getX()+b.getX(), a.getY()+b.getY());
  }

  public static Point2D.Double sum(Point2D a, double x, double y) {
    a = orOrigin(a);
    return new Point2D.Double(a.getX()+x, a.getY()+y);
  }


  public static Point difference(Point a, Point b) {
    a = orOrigin(a);
    b = orOrigin(b);
    return new Point(a.x-b.x, a.y-b.y);
  }

  public static Point2D.Double difference(Point2D a, Point2D b) {
    a = orOrigin(a);
    b = orOrigin(b);
    return new Point2D.Double(a.getX()-b.getX(), a.getY()-b.getY());
  }

  public static Point2D orOrigin(Point2D point) {
    return point!=null ? point : ORIGIN;
  }

  public static Point orOrigin(Point point) {
    return point!=null ? point : new Point();
  }

  public static Point bottomLeft(Rectangle bounds) {
      if (bounds==null)
        return null;

      return new Point(bounds.x, bounds.y + bounds.height);
  }

  public static Point bottomRight(Rectangle bounds) {
    if (bounds==null)
      return null;

    return new Point(bounds.x + bounds.width, bounds.y + bounds.height);
  }

  public static Point discrete(Point2D point2d) {
    if (point2d==null)
      return null;

    var point = new Point();
    point.setLocation(point2d);
    return point;
  }
}
