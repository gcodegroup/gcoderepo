package uk.ac.bangor.gcode;

/**
 * The LineType enum define the type of line.
 *
 * @author zc
 */
public enum LineType {

    /**
     * The comment line. This line can be omitted during the translation.
     */
    COMMENT, 
    
    G0_XYZ,
    
    G0_XY,
    
    GFE_LINE, 
    
    
    GFXYE_LINE, 
    
    
    GXYE_LINE, 
    
    
    GFXY_LINE, 
    
    /**
     * The line which is not used. This line can be omitted during the translation.
     */
    UNUSED_LINE;
}
