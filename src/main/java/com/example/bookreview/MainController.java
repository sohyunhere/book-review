package com.example.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String main() throws IOException {
        return "main";
    }
}
