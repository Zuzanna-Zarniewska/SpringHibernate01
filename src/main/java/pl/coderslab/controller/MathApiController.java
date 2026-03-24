package pl.coderslab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.services.MathService;

@RestController
@RequestMapping("/api/math")
public class MathApiController {
    private final MathService mathService;

    public MathApiController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/factorial/{a}")
    public String factorial(@PathVariable("a") long a) {
        return Long.toString(mathService.factorial(a));
    }

    @GetMapping("/add/{a}/{b}")
    public String add(@PathVariable("a") double a, @PathVariable("b") double b) {
        return Double.toString(mathService.add(a, b));
    }

    @GetMapping("/substract/{a}/{b}")
    public String substract(@PathVariable("a") double a, @PathVariable("b") double b) {
        return Double.toString(mathService.substract(a, b));
    }

    @GetMapping("/multiply/{a}/{b}")
    public String multiply(@PathVariable("a") double a, @PathVariable("b") double b) {
        return Double.toString(mathService.multiply(a, b));
    }

    @GetMapping("/divide/{a}/{b}")
    public String divide(@PathVariable("a") double a, @PathVariable("b") double b) {
        return Double.toString(mathService.divide(a, b));
    }
}
