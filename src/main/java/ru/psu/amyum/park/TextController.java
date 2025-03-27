package ru.psu.amyum.park;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @GetMapping("/api/charCount")
    public int getCharCount(@RequestParam String text) {
        return text.length();
    }
}
