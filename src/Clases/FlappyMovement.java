package Clases;

import Interfaz.Game;
import java.awt.Toolkit;

public class FlappyMovement extends Thread {

    public int deltaTime;
    public final Game parent;
    public static boolean stopThread;

    public double timeInit;
    public int yInit = 0;

    public static final int v0 = -30;
    public static final int ACCELERATION = 9;
    public static final int TIME_FLAPING = 5;

    public FlappyMovement(Game parent) {
        this.deltaTime = 10;
        this.parent = parent;
    }

    @Override
    public void run() {
        // int varA = 1;
        stopThread = false;
        int x = Game.jFlappy.getLocation().x;
        yInit = Game.jFlappy.getLocation().y;
        timeInit = System.currentTimeMillis();
        while (true) {
            if (stopThread) {
                break;
            }

            double time = (System.currentTimeMillis() - timeInit) / 100.0f;
            int y = (int) (yInit + v0 * time + 0.5 * ACCELERATION * time * time);
                  
            Game.jFlappy.setLocation(x, y);
            Toolkit.getDefaultToolkit().sync();
//---------------------------------------------

            try {
                Thread.sleep(getDeltaTime());
                //Game.jFlappy.setLocation(x, (y + 1));

            } catch (InterruptedException e) {
                System.out.println("Ocurrio un problema " + e);
            }
            this.parent.validarChoqueTubos();
            this.parent.detectColision();
        }

    }

    public void jump() {
        timeInit = System.currentTimeMillis();
        yInit = Game.jFlappy.getLocation().y;
    }

    /* public boolean isStopJump() {
        return stopJump;
    }

    public void setStopJump(boolean stopJump) {
        this.stopJump = stopJump;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }*/
    public int getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public boolean isStopThread() {
        return stopThread;
    }

}
