/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.helper;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author Filippo
 */
public class FontHelper {

    public Font getFont() {
        InputStream fontInputStream = ClassLoader.getSystemResourceAsStream("abenakebejokes/assets/fonts/nyala.ttf");
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontInputStream).deriveFont(16f);
        } catch (FontFormatException | IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
