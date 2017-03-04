/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.presenter;

import abenakebejokes.helper.FileLoaderHelper;
import abenakebejokes.model.FileLoaderCallback;
import abenakebejokes.model.Joke;
import abenakebejokes.view.JokeViewContract;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filippo
 */
public class JokesPresenter {

    private final JokeViewContract view;
    private JokesLoader loader;

    public JokesPresenter(JokeViewContract view) {
        this.view = view;
    }

    public void loadJokes() {
        loader = new JokesLoader();
        loader.start();
    }

    public class JokesLoader extends Thread {

        @Override
        public void run() {
            FileLoaderHelper helper = new FileLoaderHelper();
            helper.loadJson("abenakebejokes/assets/json/jokes.json", new FileLoaderCallback() {
                @Override
                public void onLoadSuccess(String json) {
                    Joke[] jokes = new Gson().fromJson(json, Joke[].class);
                    for (Joke joke : jokes) {
                        view.onJokeAdded(joke);
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JokesPresenter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                @Override
                public void onLoadError(String message) {
                    System.err.println(message);
                    view.onError(message);
                }
            });
        }
    }
}
