package view;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

// ResizeAdapter class is used as an adapter for handling component resize events
// Class created by : Azhan

public class ResizeAdapter extends ComponentAdapter {

    private final Window window;

    public ResizeAdapter(Window window) {
        this.window = window;
    }

    // Called whenever the component (Window) resizes
    @Override
    public void componentResized(ComponentEvent e) {
        window.resizeContentPanel();
    }
    
}
