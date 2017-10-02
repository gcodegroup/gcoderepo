package uk.ac.bangor.gcode;

/**
 * The LineType enum define the type of line.
 *
 * @author zc
 */
public enum LineType {

   
    /**
     * The line with G0, F, X, Y and Z. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.385 Y44.175 Z0.300
     * 
     */
    G0_FXYZ_LINE,
    
    /**
     * The line with G0, F, X and Y. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */    
    G0_FXY_LINE,
    
    
    /**
     * The line with G0, X and Y. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */    
    G0_XY_LINE,
    
    /**
     * The line with G1, E and F. This line is probably unused. 
     * 
     */       
    G1_EF_LINE, 
    
     /**
     * The line with G1, E, F, X and Y. This line is used to turn on the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */      
    G1_EFXY_LINE, 
    
     /**
     * The line with G1, E, X and Y. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */      
    G1_EXY_LINE, 
    
     /**
     * The line with G1, F, X, Y and Z. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */   
    G1_FXYZ_LINE,     
    
     /**
     * The line with G1, X and Y. This line is used to turn off the laser and move to the new position. 
     * For example: G0 F9000 X42.035 Y43.814
     * 
     */      
    G1_XY_LINE,  
    
    /**
     * The line with G00, X, Y and Z. This line is the same function as G0_XYZ.
     */
    G0_XYZ_LINE,
    
    
    /**
     * The line with G01, X, Y and Z. This line is the same function as G1_XYZ.
     */
    G1_XYZ_LINE,
   
    
    
    
    /**
     * The line which is not used. This line can be omitted during the translation.
     */
    UNUSED_LINE;
}
