package uk.ac.bangor.gcode;

import java.util.Objects;

/**
 * The AbstractGcodeOperation class is a super class which represents an operation.
 *
 * @author zc
 */
public abstract class AbstractGcodeOperation implements GcodeOperation {

    protected final String string;

    protected AbstractGcodeOperation(String string) {
        this.string = string;
    }

    @Override
    public final String getString() {
        return string;
    }

    @Override
    public final int hashCode() {
        
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.string);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final AbstractGcodeOperation other = (AbstractGcodeOperation) obj;
        return Objects.equals(this.string, other.string);
    }
}
