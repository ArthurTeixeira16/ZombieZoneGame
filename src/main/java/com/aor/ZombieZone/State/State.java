package com.aor.ZombieZone.State;

import javax.swing.*;
import java.io.IOException;

public interface State {
    void run() throws IOException;
    void handleInput(KeyStroke key) throws IOException;
}
