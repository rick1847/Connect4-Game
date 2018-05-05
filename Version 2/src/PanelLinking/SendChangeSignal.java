/*
 * contains everything which pertains to cross panel communication
 */
package PanelLinking;

import java.beans.PropertyChangeListener;

/**
 * interface for facilitating communication between panels using
 *
 * @author User
 */
public interface SendChangeSignal {

    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public void setName(String newName);
}
