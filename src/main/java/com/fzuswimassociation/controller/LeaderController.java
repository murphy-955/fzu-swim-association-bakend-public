package com.fzuswimassociation.controller;

import com.fzuswimassociation.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接受领导请求
 *
 * @author 李泽聿
 * @since 2025-09-01 18:51
 */

@RestController
@RequestMapping("/leader")
@CrossOrigin
public class LeaderController {
    @Autowired
    private LeaderService leaderService;

    @GetMapping("/getLeaderList")
    public Map<String, Object> getLeaderList() {
        return leaderService.getLeaderList();
    }

    @GetMapping("/getLeaderDetail")
    public Map<String, Object> getLeaderDetail(@RequestParam("id") Integer id) {
        return leaderService.getLeaderDetail(id);
    }
}
