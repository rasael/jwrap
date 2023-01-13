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

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.awt.geom.Point2D;

import static org.assertj.core.api.Assertions.assertThat;

class PointsTest {

  @Test
  void abs() {
    assertThat(Points.abs(new Point(3,4))).hasValue(5d);
  }

  @Test
  void add() {
    Point p1 = new Point(1,2);
    Point p2 = new Point(2, 1);

    Points.add(p1, p2);
    assertThat(p1).isEqualTo(new Point(3, 3));
  }

  @Test
  void add2d() {
    Point2D p1 = new Point2D.Double(1d, 2d);
    Point2D p2 = new Point2D.Double(2d, 1d);

    Points.add(p1, p2);
    assertThat(p1).isEqualTo(new Point2D.Double(3d, 3d));
  }

  @Test
  void subtract() {
    Point p1 = new Point(1,2);
    Point p2 = new Point(2, 1);

    Points.subtract(p1, p2);
    assertThat(p1).isEqualTo(new Point(-1, 1));
  }

  @Test
  void subtract2d() {
    Point2D p1 = new Point2D.Double(1d, 2d);
    Point2D p2 = new Point2D.Double(2d, 1d);

    Points.subtract(p1, p2);
    assertThat(p1).isEqualTo(new Point2D.Double(-1d, 1d));
  }

  @Test
  void sum() {
  }

  @Test
  void testSum() {
  }

  @Test
  void difference() {
  }

  @Test
  void testDifference() {
  }
}