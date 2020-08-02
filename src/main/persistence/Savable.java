package persistence;

import java.io.PrintWriter;

// Represents data which can be saved to file
public interface Savable {
    // MODIFIES: printWriter
    // EFFECTS: record the savable to printWriter
    void save(PrintWriter printWriter);
}
