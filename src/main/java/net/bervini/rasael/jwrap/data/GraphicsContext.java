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

package net.bervini.rasael.jwrap.data;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;

public class GraphicsContext<G extends Graphics> {

  private Font font;
  private Shape clip;
  private Color color;

  // -------------------------------------------------------------------------------------------------------------------
  // Factory
  // -------------------------------------------------------------------------------------------------------------------

  public static GraphicsContext2D get(Graphics2D graphics) {
    return new GraphicsContext2D(graphics);
  }
  public static <G extends Graphics> GraphicsContext<G> get(G graphics) {
    if (graphics instanceof Graphics2D g2d) {
      return (GraphicsContext<G>) get(g2d);
    }
    return (GraphicsContext<G>) new Impl(graphics);
  }

  public final void restore(G graphics) {
    restoreImpl(graphics);
  }

  // -------------------------------------------------------------------------------------------------------------------

  public final Font getFont() {
    return font;
  }

  public final Color getColor() {
    return color;
  }

  public final Shape getClip() {
    return clip;
  }

  // -------------------------------------------------------------------------------------------------------------------

  private GraphicsContext(G graphics) {
    storeContext(graphics);
  }

  protected void storeContext(G graphics) {
    this.font = graphics.getFont();
    this.color = graphics.getColor();
    this.clip = graphics.getClip();
  }

  protected void restoreImpl(G graphics) {
    graphics.setClip(clip);
    graphics.setColor(color);
    graphics.setFont(font);
  }

  private static class Impl extends GraphicsContext<Graphics> {

    public Impl(Graphics g) {
      super(g);
    }
  }

  private static class GraphicsContext2D extends GraphicsContext<Graphics2D> {

    private Stroke stroke;
    private Color background;
    private Paint paint;
    private RenderingHints renderingHints;
    private Composite composite;

    public GraphicsContext2D(Graphics2D g2d) {
      super(g2d);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Stroke getStroke() {
      return stroke;
    }

    public Color getBackground() {
      return background;
    }

    public Paint getPaint() {
      return paint;
    }

    public RenderingHints getRenderingHints() {
      return (RenderingHints) renderingHints.clone();
    }

    public Composite getComposite() {
      return composite;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected void storeContext(Graphics2D graphics) {
      super.storeContext(graphics);

      composite = graphics.getComposite();
      renderingHints = (RenderingHints) graphics.getRenderingHints().clone();
      paint = graphics.getPaint();
      stroke = graphics.getStroke();
      background = graphics.getBackground();
    }

    @Override
    protected void restoreImpl(Graphics2D graphics) {
      super.restoreImpl(graphics);

      graphics.setBackground(background);
      graphics.setStroke(stroke);
      graphics.setPaint(paint);
      graphics.setRenderingHints(renderingHints);
      graphics.setComposite(composite);
    }
  }
}
