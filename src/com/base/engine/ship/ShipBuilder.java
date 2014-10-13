/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.ship;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
    static int[][] rotations;
    static int part;
    static int rotation;
    static boolean idle;
    
    static {
        parts = new int[Display.getWidth()/40][Display.getHeight()/40];
        rotations = new int[Display.getWidth()/40][Display.getHeight()/40];
        part = 0;
        rotation = 0;
        idle = true;
        
        for(int y = 0; y < parts.length; y++) {
            for(int x = 0; x < parts[y].length; x++) {
                parts[y][x] = -1;
            }
        }
    }
    
    public static void update() {
        if(Keyboard.isKeyDown(Keyboard.KEY_UP) && idle) {
            part++;
            idle = false;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && idle) {
            part--;
            idle = false;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && idle) {
            rotation++;
            idle = false;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && idle) {
            rotation--;
            idle = false;
        }else if(!Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            idle = true;
        }
        
        if(part < 0) {
            part = PartList.getPartCount() - 1;
        }else if(part >= PartList.getPartCount()) {
            part = 0;
        }
        
        if(rotation < 0) {
            rotation = 3;
        }else if(rotation > 3) {
            rotation = 0;
        }
        
        if(Mouse.isButtonDown(0)) {
            rotations[(int)(Mouse.getY()/40.0+0.5)][(int)(Mouse.getX()/40.0+0.5)] = rotation;
            parts[(int)(Mouse.getY()/40.0+0.5)][(int)(Mouse.getX()/40.0+0.5)] = part;
        }
    }
    
    public static void render() {
        glBegin(GL_TRIANGLES);
        
        for(int y = 0; y < parts.length; y++) {
            for(int x = 0; x < parts[y].length; x++) {
                if(parts[y][x] > -1)
                    PartList.render(parts[y][x], x+x, y+y, rotations[y][x]);
            }
        }
        
        PartList.render(part, 1, 1, rotation);
        
        glEnd();
    }
}
