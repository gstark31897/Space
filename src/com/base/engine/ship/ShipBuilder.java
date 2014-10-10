/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.ship;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

/**
 *
 * @author gsta4786
 */
public class ShipBuilder {
    static int[][] parts;
    static int part;
    static boolean idle;
    
    static {
        parts = new int[Display.getWidth()/20][Display.getHeight()/20];
        part = 0;
        idle = true;
    }
    
    public static void update() {
        if(Keyboard.isKeyDown(Keyboard.KEY_UP) && idle) {
            part++;
            idle = false;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && idle) {
            part--;
            idle = false;
        }else if(!Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            idle = true;
        }
        
        if(part < 0) {
            part = PartList.getPartCount() - 1;
        }else if(part >= PartList.getPartCount()) {
            part = 0;
        }
        
        System.out.println(part);
    }
    
    public static void render() {
        glBegin(GL_TRIANGLES);
        
            PartList.render(part, 1, 1, 0);
        
        glEnd();
    }
}
