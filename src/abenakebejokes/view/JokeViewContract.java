/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.view;

import abenakebejokes.model.Joke;

/**
 *
 * @author Filippo
 */
public interface JokeViewContract {

    void onJokeAdded(Joke joke);

    void onError(String message);
}
