/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.ship;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author Octalus
 */
public class PartDef {
    String displayName;
    int groups;
    float[][] colors;
    float[][] verticies;
    int[][] faces;
    
    public PartDef(String displayName, String[][] colors, String[][] verticies, String[][] faces) {
        this.displayName = displayName;
        groups = colors.length;
        this.colors = new float[groups][];
        this.verticies = new float[groups][];
        this.faces = new int[groups][];
        
        for(int g = 0; g < groups; g++) {
            this.colors[g] = new float[colors[g].length];
            for(int c = 0; c < colors[g].length; c++) {
                this.colors[g][c] = Float.parseFloat(colors[g][c]);
            }
            
            this.verticies[g] = new float[verticies[g].length];
            for(int v = 0; v < verticies[g].length; v++) {
                this.verticies[g][v] = Float.parseFloat(verticies[g][v]);
            }
            
            this.faces[g] = new int[faces[g].length];
            for(int f = 0; f < faces[g].length; f++) {
                this.faces[g][f] = Integer.parseInt(faces[g][f]);
            }
        }
    }
    
    public void render(float x, float y, int r) {
        switch(r) {
            case 0:
                for(int g = 0; g < groups; g++) {
                    glColor3f(colors[g][0],colors[g][1],colors[g][2]);
                    for(int f = 0; f < faces[g].length; f++) {
                        int vertex = faces[g][f] * 2;
                        glVertex3f(verticies[g][vertex] + x, verticies[g][vertex+1] + y, 0);
                    }
                }
                break;
            
            case 1:
                for(int g = 0; g < groups; g++) {
                    glColor3f(colors[g][0],colors[g][1],colors[g][2]);
                    for(int f = 0; f < faces[g].length; f++) {
                        int vertex = faces[g][f] * 2;
                        glVertex3f(verticies[g][vertex+1] + x, -verticies[g][vertex] + y, 0);
                    }
                }
                break;
                
            case 2:
                for(int g = 0; g < groups; g++) {
                    glColor3f(colors[g][0],colors[g][1],colors[g][2]);
                    for(int f = 0; f < faces[g].length; f++) {
                        int vertex = faces[g][f] * 2;
                        glVertex3f(-verticies[g][vertex] + x, -verticies[g][vertex+1] + y, 0);
                    }
                }
                break;
                
            case 3:
                for(int g = 0; g < groups; g++) {
                    glColor3f(colors[g][0],colors[g][1],colors[g][2]);
                    for(int f = 0; f < faces[g].length; f++) {
                        int vertex = faces[g][f] * 2;
                        glVertex3f(-verticies[g][vertex+1] + x, verticies[g][vertex] + y, 0);
                    }
                }
                break;
        }
    }
}