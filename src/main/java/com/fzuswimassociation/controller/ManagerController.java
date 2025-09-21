package com.fzuswimassociation.controller;

import com.fzuswimassociation.pojo.vo.*;
import com.fzuswimassociation.service.ManagerService;
import com.fzuswimassociation.validator.AddExcellentAthlete;
import com.fzuswimassociation.validator.AddLeaderGroup;
import com.fzuswimassociation.validator.CheckToDeleteNews;
import com.fzuswimassociation.validator.CheckUploadNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 管理员控制层
 *
 * @author 李泽聿
 * @since 2025-09-01 19:18
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody ManagerLoginVo managerLoginVo) {
        return managerService.login(managerLoginVo);
    }

    @PostMapping("/deleteNews")
    public Map<String, Object> deleteNews(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo){
        return managerService.deleteNews(deleteNewsVo);
    }

    @PostMapping("/withdrawDeletionNews")
    public Map<String, Object> withdrawDeletionNews(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo){
        return managerService.withdrawDeletionNews(deleteNewsVo);
    }

    @PostMapping("/addLeader")
    public Map<String, Object> addLeader(@Validated(AddLeaderGroup.class)@RequestBody AddLeaderVo addLeaderVo){
        return managerService.addLeader(addLeaderVo);
    }

    @PostMapping("/deleteLeader")
    public Map<String, Object> deleteLeader(@Validated(CheckToDeleteNews.class)@RequestBody DeleteNewsVo deleteNewsVo){
        return managerService.deleteLeader(deleteNewsVo);
    }

    @PostMapping("/addExcellentAthlete")
    public Map<String, Object> addExcellentAthlete(@Validated(AddExcellentAthlete.class)@RequestBody AddExcellentAthleteVo addExcellentAthleteVo){
        return managerService.addExcellentAthlete(addExcellentAthleteVo);
    }

    @PostMapping("/deleteExcellentAthlete")
    public Map<String, Object> deleteExcellentAthlete(@Validated(CheckToDeleteNews.class)@RequestBody DeleteNewsVo deleteNewsVo){
        return managerService.deleteExcellentAthlete(deleteNewsVo);
    }

    @PostMapping("/uploadNews")
    public Map<String, Object> uploadNews(@Validated(CheckUploadNews.class)@RequestBody UploadNewsVo uploadNewsVo){
        return managerService.uploadNews(uploadNewsVo);
    }

    @PostMapping("/uploadVideo")
    public Map<String, Object> uploadVideo(@Validated(CheckUploadNews.class)@RequestBody UploadVideoVo uploadVideoVo){
        return managerService.uploadVideo(uploadVideoVo);
    }

    @PostMapping("/postSignUpList")
    public Map<String, Object> postSignUpList(@RequestBody ManagerUploadRegisterVo uploadRegisterVo){
        return managerService.postSignUpList(uploadRegisterVo);
    }
}
