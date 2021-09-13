package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.io.File;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.AbstractBorder;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Husain
 */
public class DBConn {

    public File myfile;
    public FileInputStream fin;
    JFileChooser fc;
    public Driver d;
    public Connection con;
    public Statement stmt;
    public ResultSet rs;
    public PreparedStatement pstmt;

    public String Times, Dates, Days;
    public String mypath;
    
     public String path=null;
     public byte[] userimage=null;

    public void nonQuery(String query) throws Exception {
        d = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(d);
        con = DriverManager.getConnection("jdbc:mysql://localhost/coffee_shop", "root", "");
        stmt = con.createStatement();
        stmt.executeUpdate(query);
    }

    public void selectQuery(String query) throws Exception {
        d = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(d);
        con = DriverManager.getConnection("jdbc:mysql://localhost/coffee_shop", "root", "");
        stmt = con.createStatement();
        stmt.executeQuery(query);
        rs = stmt.getResultSet();
        //stmt.executeUpdate(query);
    }

    public void myDate() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatD = DateTimeFormatter.ofPattern("dd/MM/yyy");
        Dates = now.format(formatD);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
        Times = now.format(format);

        DateTimeFormatter day = DateTimeFormatter.ofPattern("EEEE");
        Days = now.format(day);
    }

    public String showD() {
        try {
             fc = new JFileChooser();
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                myfile = fc.getSelectedFile();

                fin = new FileInputStream(myfile.getAbsolutePath());

                String getpath=myfile.getAbsolutePath();
                
                System.out.println(myfile.getAbsolutePath());
            }
        } catch (Exception ew) {
            System.out.println("Error in Photo");
        }

      
        return myfile.getAbsolutePath();
    }
    
 
}




class TextBubbleBorder extends AbstractBorder {

    private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    private boolean left = true;
    RenderingHints hints;

    TextBubbleBorder(
            Color color) {
        this(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize, boolean left) {
        this(color, thickness, radii, pointerSize);
        this.left = left;
    }

    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        if (left) {
            // left point
            pointer.addPoint(
                    strokePad + radii + pointerPad,
                    bottomLineY);
            // right point
            pointer.addPoint(
                    strokePad + radii + pointerPad + pointerSize,
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    strokePad + radii + pointerPad + (pointerSize / 2),
                    height - strokePad);
        } else {
            // left point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad),
                    bottomLineY);
            // right point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + pointerSize),
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                    height - strokePad);
        }

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent = c.getParent();
        if (parent != null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}
