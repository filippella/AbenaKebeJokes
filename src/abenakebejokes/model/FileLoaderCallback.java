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
public interface FileLoaderCallback {

    void onLoadSuccess(String json);

    void onLoadError(String message);
}
