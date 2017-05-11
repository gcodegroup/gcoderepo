package uk.ac.bangor.gcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class IntermediateItem implements GcodeItem {
    
    private final Point point1;
    private final Point point2;
    private final double speed;

    
    public IntermediateItem(Point point1, Point point2, double speed) {
       this.point1 = point1;
       this.point2 = point2;
       this.speed = speed;
    }


    @Override
    public String getString() {
        
        String x = getString(point1.getX());
        String y = getString(point1.getY());
        return "MOV A " + x + " B " + y + "\n" +
                "VEL A " + getVx() + " B " + getVy() + "\n" +
                "DEL " + getDelayTime();
    }    
    
    public String getDelayTime() {
        
        double xDistance = point2.getX() - point1.getX();
        double yDistance = point2.getY() - point1.getY();        
        double delayTime = Math.sqrt(xDistance * xDistance + yDistance * yDistance)/speed;
        
        return getString(delayTime);
    }
    
    
    private String getVx() {
        
        double xDistance = point2.getX() - point1.getX();
        double yDistance = point2.getY() - point1.getY();           
        double vx = speed * xDistance/Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        
        return getString(vx);
    }
    
    
    public String getVy() {
        
        double xDistance = point2.getX() - point1.getX();
        double yDistance = point2.getY() - point1.getY();           
        double vy = speed * yDistance/Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        
        return getString(vy);        
    }

    private String getString(double value) {
        return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).toPlainString();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.point1 != null ? this.point1.hashCode() : 0);
        hash = 29 * hash + (this.point2 != null ? this.point2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final IntermediateItem other = (IntermediateItem) obj;
        
        return Objects.equals(this.point1, other.point1) && Objects.equals(this.point1, other.point1);
    }
}
