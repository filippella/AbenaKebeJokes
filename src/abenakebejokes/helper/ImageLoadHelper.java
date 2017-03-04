/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.helper;

import java.awt.Image;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Filippo
 */
public class ImageLoadHelper {

    private final ExecutorService service;
    private final Map<String, Image> imagesCache;

    private ImageLoadHelper() {
        this.service = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        this.imagesCache = Collections.synchronizedMap(new WeakHashMap<>());
    }

    public final static class InstanceHolder {

        private static final ImageLoadHelper INSTANCE;

        static {
            INSTANCE = new ImageLoadHelper();
        }
    }

    public static ImageLoadHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void handle(JLabel avatar, String avatarURL) {
        Image image = this.imagesCache.get(avatarURL);
        if (image != null) {
            avatar.setIcon(new ImageIcon(image));
            System.out.println("Reusing image " + avatarURL);
        } else {
            this.service.submit(new ImageScaler(avatar, avatarURL));
            System.out.println("Loading image " + avatarURL);
        }
    }

    public class ImageScaler implements Runnable {

        private final JLabel avatar;
        private final String avatarURL;

        public ImageScaler(JLabel avatar, String avatarURL) {
            this.avatar = avatar;
            this.avatarURL = avatarURL;
        }

        @Override
        public void run() {
            Image image = new ImageIcon(ClassLoader.getSystemResource(avatarURL)).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            avatar.setIcon(new ImageIcon(image));
            imagesCache.put(avatarURL, image);
        }
    }
}
