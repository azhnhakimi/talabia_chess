package view;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeAdapter extends ComponentAdapter {
    

    private final Window window;

    public ResizeAdapter(Window window) {
        this.window = window;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        window.resizeContentPanel();
    }
    
}
