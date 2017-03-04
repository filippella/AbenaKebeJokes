/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.model;

/**
 *
 * @author Filippo
 */
public class Joke {

    private String jokeTitle;
    private String avatarURL;
    private int jokeId;
    private String jokeContent;
    private String jokeImage;

    public int getJokeId() {
        return jokeId;
    }

    public void setJokeId(int jokeId) {
        this.jokeId = jokeId;
    }

    public String getJokeContent() {
        return jokeContent;
    }

    public void setJokeContent(String jokeContent) {
        this.jokeContent = jokeContent;
    }

    public String getJokeImage() {
        return jokeImage;
    }

    public void setJokeImage(String jokeImage) {
        this.jokeImage = jokeImage;
    }

    public void setJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
