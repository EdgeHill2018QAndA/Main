package org.coursework.console;

import java.io.PrintStream;

public class ConsoleHelper {

    int lengthOfLast = 0;
    PrintStream out;

    public ConsoleHelper(PrintStream stream) {
        this.out = stream;
    }

    public ConsoleHelper print(String line) {
        this.lengthOfLast = line.length();
        out.print(line);
        return this;
    }

    public ConsoleHelper delete() {
        String backspace1 = null;
        for (int A = 0; A < this.lengthOfLast; A++) {
            if (backspace1 == null) {
                backspace1 = "\b";
            } else {
                backspace1 = backspace1 + '\b';
            }
        }
        out.print(backspace1);
        return this;
    }

    public ConsoleHelper newLine() {
        out.println();
        return this;
    }

}
