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
    static PartDef[] partDefs;
    
    static {
        try {
            File partFolder = new File("src/res/ship/parts");
            File[] partList = partFolder.listFiles();
            
            Scanner partIndexScanner = new Scanner(new File("src/res/ship/parts/PartIndex.plst"));
            
            partDefs = new PartDef[Integer.parseInt(partIndexScanner.nextLine().split(":")[1])];
            
            while(partIndexScanner.hasNextLine()) {
                String indexLine = partIndexScanner.nextLine();
                try {
                    Scanner partScanner = new Scanner(new File("src/res/ship/parts/" + indexLine.split(":")[1]));
                    
                    String name = null;
                    String[][] colors = null;
                    String[][] verticies = null;
                    String[][] faces = null;
                    
                    while(partScanner.hasNextLine()) {
                        String line = partScanner.nextLine();
                        
                        if(line.startsWith("DisplayName")) {
                            name = line.split(":")[1];
                        }else if(line.startsWith("Groups")) {
                            colors = new String[Integer.parseInt(line.split(":")[1])][];
                            verticies = new String[Integer.parseInt(line.split(":")[1])][];
                            faces = new String[Integer.parseInt(line.split(":")[1])][];
                        }else if(line.startsWith("Group")) {
                            int group = Integer.parseInt(line.split(":")[1]);
                            for(int g = 0; g < 3; g++) {
                                line = partScanner.nextLine();
                                if(line.startsWith("Color")) {
                                    colors[group] = line.split(":")[1].split(",");
                                }else if(line.startsWith("Vertex")) {
                                    verticies[group] = line.split(":")[1].split(",");
                                }else if(line.startsWith("Face")) {
                                    faces[group] = line.split(":")[1].split(",");
                                }
                            }
                        }
                    }
                    
                    partDefs[Integer.parseInt(indexLine.split(":")[0])] = new PartDef(name, colors, verticies, faces);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PartList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PartList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void render(int p, float x, float y, int r) {
        partDefs[p].render(x, y, r);
    }
}
