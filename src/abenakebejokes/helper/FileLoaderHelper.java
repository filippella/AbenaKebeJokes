/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.helper;

import abenakebejokes.model.FileLoaderCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Filippo
 */
public class FileLoaderHelper {

    public void loadJson(String path, FileLoaderCallback callback) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            callback.onLoadSuccess(buffer.toString());
        } catch (IOException e) {
            callback.onLoadError(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
    }
}
