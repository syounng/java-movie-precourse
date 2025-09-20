package movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public Greeting hello(@RequestParam("name") String name){
//        return "hello world!" + name;
        return new Greeting(1L, name);
    }


}
record Greeting(Long id, String name){
}
