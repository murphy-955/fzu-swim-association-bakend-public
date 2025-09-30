package com.fzuswimassociation.controller;

import com.fzuswimassociation.pojo.vo.AthleteRegisterVo;
import com.fzuswimassociation.pojo.vo.PreviewRegisterInformationVo;
import com.fzuswimassociation.service.SportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 运动员报名接口
 *
 * @author 李泽聿
 * @since 2025-09-12 10:09
 */

@RestController
@RequestMapping("sport")
@CrossOrigin
public class SportController {
    @Autowired
    private SportService sportService;

    /**
     * 运动员报名接口
     *
     * @param academicNumber 格式示例<pre>{@code
     *                            {
     *                                 "name": "李泽聿",
     *                                 "academicNumber": "102301237",
     *                                 "sportType": "MAN_BREASTSTROKE_100M",
     *                                 "college": "SCHOOL_OF_ADVANCED_MANUFACTURING_AND_OCEAN_COLLEGE",
     *                                 "competitionId": "b9bad5922889491ca868ec570bed48fc"
     *                            }
     *                            }
     *                            </pre>
     * @return 成功：<pre>
     *    {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": null
     *     }
     *     }
     * </pre>
     * 失败：<pre>
     *    {@code
     *     {
     *         "statusCode": 4xx,
     *         "message": "报名时间已过或,运动员姓名或学号不匹配,运动员超过限制个人允许租到报名项目限制,运动员所属学院不允许报名,运动员选择项目在本次比赛中禁止报名",
     *         "data": null
     *     }
     *     }
     * </pre>
     * @author 李泽聿
     * @since 2025-09-20 19:49
     */

    @PostMapping("register")
    public Map<String, Object> register(@RequestBody AthleteRegisterVo academicNumber) {
        return sportService.register(academicNumber);
    }

    /**
     * 获取比赛名称和uuid接口
     *
     * @return 成功：<pre>
     *    {@code
     *     {
     *         "statusCode": 200,
     *         "message": "成功",
     *         "data": [
     *             {
     *             "competitionName": "2025年冬奥会",
     *             "competitionId": "b9bad5922889491ca868ec570bed48fc"
     *             },
     *             {
     *             "competitionName": "2025年冬奥会",
     *             "competitionId": "b9bad5922889491ca868ec570bed48fc"
     *             }
     *         ]
     *     }
     *     }
     *     </pre>
     * 如果data为空，则说明没有比赛可供运动员报名。
     * 失败：<pre>
     *            {@code
     *         {
     *             "statusCode": 4xx,
     *             "message": "获取比赛信息失败",
     *             "data": null
     *         }
     *      </pre>
     * @author 李泽聿
     * @since 2025-09-20 20:00
     */

    @GetMapping("getGameList")
    public Map<String, Object> getGameList() {
        return sportService.getGameList();
    }

    /**
     * 预览报名信息接口
     *
     * @param vo 格式示例<pre>
     *                        {@code
     *                          {
     *                              "token": "管理员token",
     *                              "gameId": "uuid"
     *                          }
     *                        }
     *                     </pre>
     * @return 成功：<pre>
     * {@code
     *  {
     *   "data": {
     *     "competitionName": "2025年福州大学振奋杯",
     *     "athleteDetail": [],
     *     "endTime": "2025-12-31",
     *     "collegeActivityLimit": "2",
     *     "athleteActivityLimit": "2",
     *     "sameActivityMaxLimit": "2",
     *     "leaderPhone": "13776528859",
     *     "allowedColleges": [
     *       "SCHOOL_OF_ADVANCED_MANUFACTURING_AND_OCEAN_COLLEGE",
     *       "SCHOOL_OF_ELECTRICAL_ENGINEERING_AND_AUTOMATION"
     *     ],
     *     "allowedEvents": [
     *       "MANS_FREESTYLE_FREESTYLE_50_100M",
     *       "MAN_FREESTYLE_100M"
     *     ]
     *   },
     *   "message": "成功",
     *   "statusCode": 200
     * }
     * }
     * </pre>
     * 失败：<pre>
     *    {@code
     *     {
     *         "statusCode": 4xx,
     *         "message": "token无效或已过期，或获取最新闻据失败",
     *         "data": null
     *     }
     *     }
     * </pre>
     * @author 李泽聿
     * @since 2025-09-20 20:05
     */

    @PostMapping("preview")
    public Map<String, Object> preview(@RequestBody PreviewRegisterInformationVo vo) {
        return sportService.preview(vo);
    }

    /**
     * 导出报名信息接口为xlsx文件
     *
     * @param vo       格式示例<pre>
     *                    {@code
     *                    {
     *                        "token": "管理员token",
     *                        "gameId": "uuid"
     *                    }
     *                    }
     *                 </pre>
     * @param response response对象
     * @author 李泽聿
     * @since 2025-09-20 20:15
     */

    @PostMapping("export")
    public void export(@RequestBody PreviewRegisterInformationVo vo, HttpServletResponse response) throws Exception {
        sportService.export(vo, response);
    }

    /**
     *
     * @param id
     * @return
     * @author 李泽聿
     * @since 2025-09-23 07:56
     */

    @GetMapping("getGameInfo")
    public Map<String, Object> getGameInfo(@RequestParam("competitionId") String id) {
        return sportService.getGameInfo(id);
    }
}
