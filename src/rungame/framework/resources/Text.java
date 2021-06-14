package rungame.framework.resources;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Text {
    private String text;
    private Font font;
    private Color color;

    public Text() {
        this.text = "";
        font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
        color = new Color(0, 0, 0);
    }

    public Text(String text) {
        this();
        this.text = text;
    }

    public void setFont(String name, int style, int size) {
        this.font = new Font(name, style, size);
    }

    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }

    public void draw(Graphics g, int x, int y) {
        g.setFont(this.font);
        g.setColor(this.color);
        g.drawString(text, x, y);
    }
}
