package it.unibo.geosurv.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  it.unibo.geosurv.view.graphics.menuView;

public class MainMenuController {

    public menuView menuView;

    public MainMenuController(menuView menuView) {
        this.menuView = menuView;
    
        //collego gli actionListener
        menuView.getStartButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
        }
    });

    menuView.getCloseButton().addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            closeApplication();
        }
    });
}

public void startGame() {
    // Logica per avviare il gioco
    }

public void closeApplication() {
    System.exit(0);
}

/*
public void startMenu() {
    mainMenuView.setVisible(true);
}
*/
}
