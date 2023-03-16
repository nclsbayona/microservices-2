package co.edu.javeriana.ms.Cliente.api;

import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
public class Cliente {

    @Autowired
    private RestTemplate rt;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/calculadora/suma")
    public String sumar(@RequestParam String a, @RequestParam String b, @RequestParam String user) {
        String r = rt.getForObject("http://sumador/suma?a={a}&b={b}&name={user}", String.class, a, b, user);
        return r;
    }

    @GetMapping("/calculadora/div")
    public String dividir(@RequestParam String a, @RequestParam String b, @RequestParam String user) {
        String r = rt.getForObject("http://divisor/division?a={a}&b={b}&name={user}", String.class, a, b, user);
        return r;
    }

    @GetMapping("/calculadora/multip")
    public String multiplicar(@RequestParam String a, @RequestParam String b, @RequestParam String user) {
        String r = rt.getForObject("http://multiplicador/multiplicar?a={a}&b={b}&name={user}", String.class, a, b,
                user);
        return r;
    }

    @GetMapping("/calculadora/resta")
    public String restar(@RequestParam String a, @RequestParam String b, @RequestParam String user) {
        String r = rt.getForObject("http://substractor/resta?a={a}&b={b}&name={user}", String.class, a, b, user);
        return r;
    }

    @GetMapping("/logs")
    public LogsContainer view() {
        LogsContainer lg = new LogsContainer();
        lg.addLog("| Sumador |",rt.getForObject("http://sumador/logs", String.class));
        lg.addLog("| Divisor |",rt.getForObject("http://divisor/logs", String.class));
        lg.addLog("| Multiplicador |",rt.getForObject("http://multiplicador/logs", String.class));
        lg.addLog("| Substractor |",rt.getForObject("http://substractor/logs", String.class));
        return lg;
    }
}

@Getter
@Setter
@NoArgsConstructor
class LogsContainer {
    private ArrayList<TreeMap<String, String>> logs = new ArrayList<TreeMap<String, String>>();;

    public void addLog(String service, String log) {
    TreeMap<String, String> logl=new TreeMap<String, String>();
    logl.put(service,log);
        this.logs.add(logl);
    }
}
