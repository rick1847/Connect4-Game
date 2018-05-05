/*
 * contains everything which pertains to cross panel communication
 */
package PanelLinking;

//imports
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 * implements sendChangeSignal; allows communication between panels based on
 * sending strings
 *
 * @author User
 */
public class SendChangeString implements SendChangeSignal {

    //stores PropertyChangeListeners
    private Set<PropertyChangeListener> listeners;
    //stores the name
    String name;

    /**
     * constructs a new SendChangeString object
     */
    public SendChangeString() {
        listeners = new HashSet<>(25);
    }

    /**
     * adds a ProperyChangeListener to listeners
     *
     * @param listener - stores the added listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * removes ProperyChangeListener from listeners
     *
     * @param listener - stores the removed listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.remove(listener);
    }

    /**
     * sets the name and fires the PropertyChangeListener
     *
     * @param newName - stores the
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        firePropertyChange("name", name, oldName);
    }

    /**
     * fires a property change event
     *
     * @param name2 - stores the propertyName
     * @param newObject - stores the old object (accidently flipped)
     * @param oldObject - stores the new object (accidently flipped)
     */
    protected void firePropertyChange(String name2, String newObject, String oldObject) {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, name2, newObject, oldObject);
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(evt);
        }
    }

}
