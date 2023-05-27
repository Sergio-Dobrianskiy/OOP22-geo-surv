package it.unibo.geosurv.view.graphics;

import it.unibo.geosurv.model.Game;
import java.awt.Dimension;
import javax.swing.JFrame;

/** TODO: class def. */
public class Window {

	/** TODO: javadoc */
	public Window(final int width, final int height, final String title, final Game game) {
		final JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);

	}
}

// package it.unibo.geosurv.view.graphics;
//
// import java.awt.BorderLayout;
// import java.awt.Dimension;
// import java.awt.event.ActionListener;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import it.unibo.geosurv.model.Game;
//
// public class Window {
// public Window(int width, int height, String title, Game game) {
// JFrame frame = new JFrame(title);
// frame.setLayout(new BorderLayout());
//
// frame.getContentPane();
// frame.setPreferredSize(new Dimension(width,height));
// frame.setMaximumSize(new Dimension(width,height));
// frame.setMinimumSize(new Dimension(width,height));
// frame.add(game, BorderLayout.CENTER);
//
// ActionListener al1 = (e)->{
// final JButton bt = (JButton)e.getSource();
// game.pause = !game.pause;
// bt.setText("Pausa = " + game.pause);
// };
//
// JButton jb1 = new JButton("test");
// jb1.addActionListener(al1);
// frame.add(jb1, BorderLayout.SOUTH);
//
//
// frame.setResizable(false);
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.setLocationRelativeTo(null);
// frame.setVisible(true);
// frame.setAlwaysOnTop(true);
//
// }
// }
