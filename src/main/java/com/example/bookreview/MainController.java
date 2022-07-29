package com.example.bookreview;

import com.example.bookreview.user.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
