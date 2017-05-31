package uk.ac.bangor.gcode.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author zc
 */
public class FileNameModel {

    public static final String FILE_NAME_PROPERTY = "FILE_NAME_PROPERTY";

    private String fileName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {

        String oldValue = this.fileName;
        this.fileName = fileName;
        support.firePropertyChange(FILE_NAME_PROPERTY, oldValue, fileName);
    }

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public synchronized boolean hasListeners(String propertyName) {
        return support.hasListeners(propertyName);
    }

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
