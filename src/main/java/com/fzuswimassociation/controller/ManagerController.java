package com.fzuswimassociation.controller;

import com.fzuswimassociation.pojo.vo.*;
import com.fzuswimassociation.service.ManagerService;
import com.fzuswimassociation.validator.AddExcellentAthlete;
import com.fzuswimassociation.validator.AddLeaderGroup;
import com.fzuswimassociation.validator.CheckToDeleteNews;
import com.fzuswimassociation.validator.CheckUploadNews;
import jakarta.servlet.http.HttpServletRequest;
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
    public Map<String, Object> login(@RequestBody ManagerLoginVo managerLoginVo, HttpServletRequest request) {
        return managerService.login(managerLoginVo, request.getRemoteAddr());
    }

    /**
     * 管理员湖获取其他管理员的列表
     *
     * @param vo 管理员列表获取参数,包含token
     * @return 管理员列表
     * <pre>
     *     {@code
     *     {
     *   "data": [
     *     {
     *       "id": 1,
     *       "userName": "admin",
     *     }
     *   ],
     *   "message": "成功",
     *   "statusCode": 200
     * }
     *     }
     * </pre>
     * @author 李泽聿
     * @since 2025-09-23 07:41
     *
     */

    @PostMapping("getAdminList")
    public Map<String, Object> getAdminList(@RequestBody GetManagerListVo vo) {
        return managerService.getAdminList(vo);
    }

    /**
     * 管理员删除其他管理员
     *
     * @param vo 包含token和adminId
     * @return 成功
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": null
     *     }
     *     }
     * </pre>
     * <p>
     * 失败：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 446,
     *         "message": "删除管理员失败",
     *         "data": null
     *     }
     *     }
     * </pre>
     * @author 李泽聿
     * @since 2025-09-23 08:14
     */

    @PostMapping("/deleteAdmin")
    public Map<String, Object> deleteAdmin(@RequestBody DeleteAdminVo vo) {
        return managerService.deleteAdmin(vo);
    }

    /**
     * 管理员添加其他管理员
     *
     * @param vo 包含token和adminName和password，且都不为空
     * @return 成功：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": null
     *     }
     *     }
     *     }
     * </pre>
     * <br>
     * 失败：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 447,
     *         "message": "添加管理员失败",
     *         "data": null
     *     }
     *     }
     *  </pre>
     * @author 李泽聿
     * @since 2025-09-23 08:22
     *
     */

    @PostMapping("addAdmin")
    public Map<String, Object> addAdmin(@RequestBody AddAdminVo vo) {
        return managerService.addAdmin(vo);
    }


    /**
     * 管理员更新其他管理员信息
     *
     * @param vo 前端传入的token，id，adminName，password
     * @return 成功
     * <pre>
     *     {@code
     *         {
     *             "statusCode": 200,
     *             "message":"成功",
     *             "data":null
     *         }
     *     }
     *  <pre/>
     *  失败：
     * <pre>
     *     {@code
     *         {
     *             "statusCode": 448,
     *             "message":"更新管理员失败",
     *             "data":null
     *         }
     *     }
     *  </pre>
     * @author 李泽聿
     * @since 2025-09-23 15:58
     */

    @PostMapping("updateAdmin")
    public Map<String, Object> updateAdmin(@RequestBody UpdateAdminVo vo) {
        return managerService.updateAdmin(vo);
    }


    /**
     * 删除新闻<br>
     * 注意，这里的删除只是将数据库news的deleted字段设置为false，并不会真正删除，后续恢复操作见{@link ManagerService#withdrawNews(DeleteNewsVo)}
     *
     * @param deleteNewsVo 前端传入的token和id
     * @return 成功：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": null
     *     }
     *     }
     * </pre>
     * <br>
     * 失败：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 449,
     *         "message": "删除新闻失败",
     *         "data": null
     *     }
     *     }
     *  </pre>
     * @author 李泽聿
     * @since 2025-09-23 16:05
     */

    @PostMapping("/deleteNews")
    public Map<String, Object> deleteNews(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo) {
        return managerService.deleteNews(deleteNewsVo);
    }

    /**
     * 恢复删除新闻<br>
     * 将数据库的deleted字段恢复为true
     *
     * @param deleteNewsVo 前端传入的token和id
     * @return 成功：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": null
     *     }
     *     }
     * </pre>
     * <br>
     * 失败：
     * <pre>
     *     {@code
     *     {
     *         "statusCode": 427,
     *         "message": "恢复删除新闻失败",
     *         "data": null
     *     }
     *     }
     * @author 李泽聿
     * @since 2025-09-23 16:09
     */

    @PostMapping("/withdrawNews")
    public Map<String, Object> withdrawNews(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo) {
        return managerService.withdrawNews(deleteNewsVo);
    }

    /**
     * 添加领导
     *
     * @param addLeaderVo 格式如下：
     *                    <pre>
     *                          {@code
     *                            {
     *                                  "token": "xxxxx",
     *                                  "name": "xxx",
     *                                  "age": 25,
     *                                  "introduction": "xxxxx",
     *                                  "position": "xxxxx"
     *                              }
     *                          }
     *                      </pre>
     * @return 成功：
     * <pre>
     *     {@code
     *     {
     *     "statusCode": 200,
     *     "message": "成功",
     *     "data": null
     * }
     *     }
     * </pre>
     * <br>
     * 失败：
     * <pre>
     *     {@code
     *     {
     *     "statusCode": 450,
     *     "message": "添加领导失败",
     *     "data": null
     *     }
     *     }
     * </pre>
     * @author 李泽聿
     * @since 2025-09-23 16:12
     */

    @PostMapping("/addLeader")
    public Map<String, Object> addLeader(@Validated(AddLeaderGroup.class) @RequestBody AddLeaderVo addLeaderVo) {
        return managerService.addLeader(addLeaderVo);
    }

    /**
     * 删除领导<br>
     * 这个是真删了
     *
     * @param deleteNewsVo token，id
     * @return 同上
     * @author 李泽聿
     * @since 2025-09-23 16:46
     */

    @PostMapping("/deleteLeader")
    public Map<String, Object> deleteLeader(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo) {
        return managerService.deleteLeader(deleteNewsVo);
    }

    /**
     * 添加优秀运动员
     *
     * @param addExcellentAthleteVo token，name，age，grade，introduction
     * @return 同上
     * @author 李泽聿
     * @since 2025-09-23 16:57
     */
    @PostMapping("/addExcellence")
    public Map<String, Object> addExcellence(@Validated(AddExcellentAthlete.class) @RequestBody AddExcellentAthleteVo addExcellentAthleteVo) {
        return managerService.addExcellence(addExcellentAthleteVo);
    }

    @PostMapping("/deleteExcellence")
    public Map<String, Object> deleteExcellence(@Validated(CheckToDeleteNews.class) @RequestBody DeleteNewsVo deleteNewsVo) {
        return managerService.deleteExcellence(deleteNewsVo);
    }

    @PostMapping("uploadNews")
    public Map<String, Object> uploadNews(@RequestBody UploadNewsVo vo) {
        return managerService.uploadNews(vo);
    }

    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(@Validated(CheckUploadNews.class) @ModelAttribute UploadImageVo vo) {
        return managerService.uploadImage(vo);
    }

    @PostMapping("/uploadVideo")
    public Map<String, Object> uploadVideo(@Validated(CheckUploadNews.class) @ModelAttribute UploadVideoVo uploadVideoVo) {
        return managerService.uploadVideo(uploadVideoVo);
    }

    @PostMapping("/postSignUpList")
    public Map<String, Object> postSignUpList(@RequestBody ManagerUploadRegisterVo uploadRegisterVo) {
        return managerService.postSignUpList(uploadRegisterVo);
    }
}
