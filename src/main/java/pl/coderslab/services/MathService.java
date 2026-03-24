package pl.coderslab.services;

import org.springframework.stereotype.Service;

@Service
public class MathService {
    public Long factorial(long a) {
        long result = 1L;
        for (long i = a; i >= 1; i--) {
            result *= i;
        }
        return result;
    }

    public Double add(double a, double b) {
        return a + b;
    }

    public Double substract(double a, double b) {
        return a - b;
    }

    public Double multiply(double a, double b) {
        return a * b;
    }

    public Double divide(double a, double b) {
        return a / b;
    }
}
