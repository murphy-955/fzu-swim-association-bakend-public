package com.fzuswimassociation.controller;

import com.fzuswimassociation.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 报名控制类
 *
 * @author 李泽聿
 * @since 2025-09-10 14:02
 */

@RequestMapping("/register")
@RestController
@CrossOrigin
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/allowSportType")
    public Map<String,Object> allowSportType(@RequestParam("game") String gameId){
        return registerService.allowSportType(gameId);
    }

    @GetMapping("/getCollegeDetail")
    public Map<String,Object> getCollegeDetail(){
        return registerService.getCollegeDetail();
    }



}
