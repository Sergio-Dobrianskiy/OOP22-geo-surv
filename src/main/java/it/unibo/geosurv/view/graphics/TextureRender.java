package it.unibo.geosurv.view.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.monsters.Monster;

/**
 * handles game textures.
 */
public class TextureRender {

    private final Handler handler;
    private static final boolean DEBUG_MODE = false;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public TextureRender(final Handler handler) {
        this.handler = handler;

    }

    public void renderView(final Graphics g) {
        final Iterator<GameObject> goIterator = handler.getGameObjects().iterator();
        final Player player = handler.getPlayer();
        while (goIterator.hasNext()) {
            final GameObject tempObject = goIterator.next();
            final int xx = getRenderX(tempObject);
            final int yy = getRenderY(tempObject);
            g.drawImage(tempObject.getTexture().extractTexture(), xx, yy, tempObject.getWidth(), tempObject.getHeight(), null);
        }
        renderUI(g, player);
    }

    /**
     * Renders game user interface.
     *
     * @param g
     * @param player game's player
     */
    public void renderUI(final Graphics g, final Player player) {

        final int x = (int) player.getX();
        final int y = (int) player.getY();

        // exp
        final int barWidthExp = 100;
        final int barHeightExp = 6;
        final int barXExp = x - barWidthExp / 2; // bar x coordinate
        final int barYExp = y - barHeightExp - 30;

        // life
        final int barWidthLife = 100;
        final int barHeightLife = 10;
        final int barXLife = x - barWidthLife / 2; // bar x coordinate
        final int barYLife = y - barHeightLife - 40;

        /* Draw bar progress for player's experience */
        g.setColor(Color.BLACK);
        g.fillRect(barXExp, barYExp, barWidthExp, barHeightExp);
        float percentageExp = player.getExpPercentage();
        percentageExp = percentageExp > 1 ? 1 : percentageExp; // prevents bar from overflowing
        final int filledWidthExp = (int) (barWidthExp * percentageExp);
        g.setColor(Color.CYAN);
        g.fillRect(barXExp, barYExp, filledWidthExp, barHeightExp); 
        g.setColor(Color.ORANGE);
        g.drawRect(barXExp - 1, barYExp - 1, barWidthExp + 1, barHeightExp + 1); // border

        /* Draw player's life bar */
        g.setColor(Color.RED);
        g.fillRect(barXLife, barYLife, barWidthLife, barHeightLife);
        float percentage = player.getLifePercentage();
        percentage = percentage > 1 ? 1 : percentage; // prevents bar from overflowing
        final int filledWidth = (int) (barWidthLife * percentage);
        g.setColor(Color.GREEN);
        g.fillRect(barXLife, barYLife, filledWidth, barHeightLife);
        g.setColor(Color.ORANGE);
        g.drawRect(barXLife - 1, barYLife - 1, barWidthLife + 1, barHeightLife + 1); // border

        // Draw debug text
        if (DEBUG_MODE) {
            g.setColor(Color.white);
            g.drawString("Life: " + player.getLife(), x + player.getWidth(), y);
            g.drawString("Exp: " + (int) player.getExpPercentage() + "%", x + player.getWidth(), y + 20);
            g.drawString("Curr: " + player.getExperience(), x + player.getWidth(), y + 40);
            g.drawString("Max: " + player.getMaxExperience(), x + player.getWidth(), y + 60);
            g.drawString("Lvl: " + player.getLevel(), x + player.getWidth(), y + 80);
            g.drawString("Monsters: " + Monster.getMonstersCounter() + " [" + Monster.getMonstersDeadCounter() + "]",
                    x + player.getWidth(), y + 100);
        }
    }

    public void renderHitBoxes(final Graphics g, final Color color, final GameObject obj) {
        final int xx = getRenderX(obj);
        final int yy = getRenderY(obj);
        g.setColor(color);
        g.fillRect(xx, yy, obj.getWidth(), obj.getHeight());
    }

    public int getRenderX(final GameObject obj) {
        return (int) obj.getX() - obj.getWidth() / 2;
    }

    public int getRenderY(final GameObject obj) {
        return (int) obj.getY() - obj.getHeight() / 2;
    }

    /** Show debug info */
    public void showDebug(final Graphics g) {
        final int xPos = (int) this.handler.getPlayer().getX() - 120;
        final int yPos = (int) this.handler.getPlayer().getY();
        final List<GameObject> gameObjects = handler.getGameObjects();

        g.setColor(Color.RED);
        // g.drawString("FPS: " + this.fps, 850, 50);
        // g.drawString("Objects: " + this.objectsCounter, 850, 65);
        g.drawString("Experience: " + Experience.getExperienceCounter(), xPos, yPos);
        g.drawString("Monsters: " + Monster.getMonstersCounter(), xPos, yPos + 15);
        g.drawString("Player Exp: " + handler.getPlayer().getExperience(), xPos, yPos + 30);
        g.drawString("Player Life: " + handler.getPlayer().getLife(), xPos, yPos + 45);

        for (final GameObject to : gameObjects) {
            final int xx = getRenderX(to);
            final int yy = getRenderY(to);

            if (to.getId() != ID.Block && to.getId() != ID.Bullet) {
                g.drawString(to.toString(), xx + 10, yy + 10);
                this.renderHitBoxes(g, Color.red, to);
            }
        }
    }

    // protected void drawRect(final Graphics g, final Color color, final GameObject
    // obj) {
    // g.setColor(color);
    // g.fillRect((int) (obj.getX() - (obj.getWidth() / 2)), (int) (obj.getY() -
    // (obj.getHeight() / 2)), obj.getWidth(), obj.getHeight());
    //
    // }

    // protected void drawOval(Graphics g, Color color) {
    // g.setColor(color);
    // g.fillOval(getRenderX(), getRenderY(), width, height);
    // }

    // protected void renderImage(Graphics g, BufferedImage sprite) {
    // g.drawImage(sprite, (int) (x - (this.width / 2)), (int) (y - (this.height /
    // 2)), null);
    //
    // }
}
