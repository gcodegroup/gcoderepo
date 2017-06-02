package uk.ac.bangor.gcode;

public final class Point {

    private final double xValue;
    private final double yValue;
    private final double zValue;
    private final boolean layserOn;
    
    public Point(FileLine line, boolean layserOn) {
        
        String[] cells = line.getLineString().split(" ");
        this.layserOn =layserOn;
        
        String xString = null;
        String yString = null;
        String zString = "0";
        
        for(String cell : cells) {
            
            if(cell.startsWith("X")) {
                xString = cell.substring(1);
            }
            
            if(cell.startsWith("Y")) {
                yString = cell.substring(1);
            }
            
            if(cell.startsWith("Z")) {
                zString = cell.substring(1);
            }
        }

        this.xValue = Double.parseDouble(xString);
        this.yValue = Double.parseDouble(yString);
        
        this.zValue = Double.parseDouble(zString);
    }

    public double getX() {
        return xValue;
    }

    public double getY() {
        return yValue;
    }

    public double getZ() {
        return zValue;
    }
    
    public boolean isLaserOn() {
        return layserOn;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.xValue) ^ (Double.doubleToLongBits(this.xValue) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.yValue) ^ (Double.doubleToLongBits(this.yValue) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.zValue) ^ (Double.doubleToLongBits(this.zValue) >>> 32));
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
        
        final Point other = (Point) obj;
        
        return this.xValue == other.xValue && this.yValue == other.yValue && this.zValue == other.zValue;
    }
}
