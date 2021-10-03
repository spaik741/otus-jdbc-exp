package otus.orm.exp.service.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleService implements IOService {

    private final InputStream in;
    private final PrintStream out;

    public ConsoleService(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String readString() {
        Scanner sc = new Scanner(in);
        return sc.nextLine();
    }

    @Override
    public void printString(String s) {
        out.println(s);
    }

}
