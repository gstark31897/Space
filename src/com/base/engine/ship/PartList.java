/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.ship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Octalus
 */
public class PartList {
    static ArrayList<PartDef> partDefs;
    
    static {
        File partFolder = new File("src/res/ship/parts");
        File[] partList = partFolder.listFiles();
        
        partDefs = new ArrayList<PartDef>();
        
        for(int i = 0; i < partList.length; i++) {
            try {
                Scanner partScanner = new Scanner(partList[i]);
                
                while(partScanner.hasNextLine()) {
                    partDefs.add(new PartDef(partScanner.nextLine(), partScanner.nextLine().split(","), partScanner.nextLine().split(",")));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PartList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void render() {
        glBegin(GL_TRIANGLES);
        
        partDefs.get(0).render();
        
        glEnd();
    }
}
