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

import java.awt.Color;

public class ColorWrap extends AbstractWrap<Color, ColorWrap> {
 
  protected ColorWrap(Color value) {
    super(value);
  }

  @Override
  ColorWrap self() {
    return this;
  }

  @Override
  Replicator<Color, ColorWrap> replicator() {
    return ColorWrap::new;
  }

  // -------------------------------------------------------------------------------------------------------------------

  public ColorWrap shift() {
    if (value==null) return this;
    return new ColorWrap(new Color(value.getBlue(), value.getRed(), value.getGreen()));
  }

  public ColorWrap unshift() {
    if (value==null) return this;
    return new ColorWrap(new Color(value.getGreen(), value.getBlue(), value.getRed()));
  }
  
  public int getRGB() {
    return value!=null ? value.getRGB() : 0;
  }
  
  // -------------------------------------------------------------------------------------------------------------------
  
  public int red() {
    return value!=null ? value.getRed() : 0;
  }

  public ColorWrap red(int red) {
    if (value==null)
      return this;

    return new ColorWrap(new Color(red, value.getGreen(), value.getBlue(), value.getAlpha()));
  }

  public float floatRed() {
    return value!=null ? value.getRed()/255f : 0f;
  }

  public ColorWrap floatRed(float red) {
    if (value==null)
      return this;

    return red((int)(red*255+0.5));
  }
  
  public int green() {
    return value!=null ? value.getGreen() : 0;
  }

  public ColorWrap green(int green) {
    if (value==null)
      return this;

    return new ColorWrap(new Color(value.getGreen(), green, value.getBlue(), value.getAlpha()));
  }

  public float floatGreen() {
    return value!=null ? value.getGreen()/255f : 0f;
  }

  public ColorWrap floatGreen(float green) {
    if (value==null)
      return this;

    return green((int)(green*255+0.5));
  }

  public int blue() {
    return value!=null ? value.getBlue() : 0;
  }

  public ColorWrap blue(int blue) {
    if (value==null)
      return this;

    return new ColorWrap(new Color(value.getRed(), value.getGreen(), blue, value.getAlpha()));
  }

  public float floatBlue() {
    return value!=null ? value.getBlue()/255f : 0f;
  }

  public ColorWrap floatBlue(float blue) {
    if (value==null)
      return this;
    
    return blue((int)(blue*255+0.5));
  }
  
  public int alpha() {
    return value!=null ? value.getAlpha() : 0;
  }

  public ColorWrap alpha(int alpha) {
    if (value==null)
      return this;

    return new ColorWrap(new Color(value.getRed(), value.getGreen(), value.getBlue(), alpha));
  }

  public float floatAlpha() {
    return value!=null ? value.getAlpha()/255f : 0f;
  }

  public ColorWrap floatAlpha(float alpha) {
    if (value==null)
      return this;

    return alpha((int)(alpha*255+0.5));
  }
}
