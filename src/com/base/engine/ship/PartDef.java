/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.ship;

/**
 *
 * @author Octalus
 */
public class PartDef {
    String displayName;
    float[] verticies;
    int[] faces;
    
    public PartDef(String displayName, String[] verticies, String[] faces) {
        this.displayName = displayName;
        this.verticies = new float[verticies.length];
        this.faces = new int[faces.length];
        
        for(int i = 0; i < verticies.length; i++) {
            this.verticies[i] = Float.parseFloat(verticies[i]);
        }
        
        for(int i = 0; i < faces.length; i++) {
            this.faces[i] = Integer.parseInt(faces[i]);
        }
    }
}
