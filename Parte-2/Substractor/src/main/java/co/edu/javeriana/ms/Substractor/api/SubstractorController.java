package co.edu.javeriana.ms.Substractor.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubstractorController {

    @Autowired
    private Environment env;

    @GetMapping("/resta")
    public String sum(@RequestParam String name, @RequestParam String a, @RequestParam String b) {
        String port = env.getProperty("local.server.port");
        port = "Resultado de " + a + " - " + b + " = " + String.valueOf(Double.valueOf(a) - Double.valueOf(b))
                + ": Microservicio en puerto->" + port;
        this.escribirLog(name, a, b);
        return port;
    }

    public void escribirLog(String usuario, String a, String b) {
        File f = new File("logs.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter(f, true));) {
            Date fecha = Date.from(Instant.now());
            String servicio = "resta";
            bwriter.append("[ INFO - " + fecha.toString() + " ] -> " + "El usuario " + usuario
                    + " accedio al servicio de " + servicio + " y realizo " + a + " - " + b + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/logs")
    public String view() {
        try {
            return new String(Files.readAllBytes(Paths.get("logs.txt")));
        } catch (IOException e) {
            return "";
        }
    }
}