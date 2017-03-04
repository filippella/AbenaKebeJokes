/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abenakebejokes.view;

import abenakebejokes.model.Joke;
import abenakebejokes.presenter.JokesPresenter;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Filippo
 */
public class JokesWindow extends javax.swing.JFrame implements JokeViewContract {

    private final DefaultListModel<Joke> jokeListModel;

    /**
     * Creates new form JokesWindow
     */
    public JokesWindow() {
        initComponents();
        this.jokeListModel = new DefaultListModel<>();
        this.jokeList.setModel(this.jokeListModel);
        this.jokeList.setCellRenderer(new JokeRowView());
        this.jokeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jokeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if (!event.getValueIsAdjusting()) {
                    JList source = (JList) event.getSource();
                    Joke joke = (Joke) source.getSelectedValue();
                    
                    JokeImageDialogWindow dialogWindow = new JokeImageDialogWindow(JokesWindow.this, true);
                    dialogWindow.setLocationRelativeTo(JokesWindow.this);
                    dialogWindow.loadImage(joke.getJokeImage());
                    dialogWindow.setVisible(true);
                }
            }
        });

        JokesPresenter presenter = new JokesPresenter(this);
        presenter.loadJokes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jokeList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jokeList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Joke> jokeList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onJokeAdded(Joke joke) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jokeListModel.addElement(joke);
            }
        });
    }

    @Override
    public void onError(String message) {

    }
}