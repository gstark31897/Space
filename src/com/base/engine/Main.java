/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.ship.PartList;
import com.base.engine.ship.Ship;
import com.base.engine.ship.ShipBuilder;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.create();
        
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
        
        Ship s = new Ship(new int[][]{{6, 0, 0, 6},{6, 0, 0, 6},{6, 0, 0, 6},{5, 3, 4, 5}}, new int[][]{{2, 0, 0, 0},{2, 0, 0, 0},{2, 0, 0, 0},{3, 0, 0, 0}});
        
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            glScalef(20, 20, 20);
            
            ShipBuilder.update();
            ShipBuilder.render();
            
            Display.update();
            Display.sync(60);
        }
    }
}