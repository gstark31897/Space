/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.ship;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

/**
 *
 * @author gsta4786
 */
public class Ship {
    int[][] parts;
    int[][] rotations;
    
    public Ship(int width, int height) {
        parts = new int[height][width];
        rotations = new int[height][width];
    }
    
    public Ship(int[][] parts, int[][] rotations) {
        this.parts = parts;
        this.rotations = rotations;
    }
    
    public void render() {
        glBegin(GL_TRIANGLES);
        for(int y = 0; y < parts.length; y++) {
            for(int x = 0; x < parts[y].length; x++) {
                PartList.render(parts[y][x], x+x, y+y, rotations[y][x]);
            }
        }
        glEnd();
    }
}
