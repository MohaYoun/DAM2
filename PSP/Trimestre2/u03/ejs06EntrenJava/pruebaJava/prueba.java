package ut01.u03.ejs06EntrenJava.pruebaJava;


import java.io.*;
import java.lang.*;
import java.util.*;
public class prueba {
    public static void main(String[] args) throws IOException {
        // creating list of commands
        List<String> commands = new ArrayList<String>();
        commands.add("ls"); // command
        commands.add("-l"); // command
        commands.add("/home/moha"); // command 
 
        // creating the process
        ProcessBuilder pb = new ProcessBuilder(commands);
 
        // starting the process
        Process process = pb.start();
 
        // for reading the output from stream
        BufferedReader stdInput
            = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    } 
}
