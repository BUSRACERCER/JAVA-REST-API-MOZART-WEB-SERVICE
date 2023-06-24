package com.works.demo.restcontrollers;

import com.works.demo.models.MusicCategoryList;
import com.works.demo.services.MozartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mozart")
public class MozartRestController {
    final MozartService mozartService;

    @GetMapping("/service")
    public MusicCategoryList service() {
        return mozartService.result();
    }

}
