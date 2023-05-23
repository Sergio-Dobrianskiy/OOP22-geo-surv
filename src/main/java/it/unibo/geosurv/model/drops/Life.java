package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.view.graphics.Texture;

public class Life extends GameObject {

    private final static int life = 10;
    protected final static int LIFE_HEIGHT = 25;
    protected final static int LIFE_WIDTH = 20;

    public Life(float x, float y) {
        super(x, y, ID.Life);
        this.height = LIFE_HEIGHT;
        this.width = LIFE_WIDTH;
        this.texture = Texture.LIFE;
    }

    @Override
    public void tick() {
    }

    /**
     * @return int amount of experience in the pill
     */
    public int getLife() {
        return life;
    }

}
