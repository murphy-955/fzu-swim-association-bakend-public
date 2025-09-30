package com.fzuswimassociation.controller;

import com.fzuswimassociation.service.ExcellentAthletesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 优秀运动员控制层
 *
 * @author 李泽聿
 * @since 2025-09-01 19:06
 */

@RestController
@RequestMapping("/player")
@CrossOrigin
public class ExcellentAthletesController {
    @Autowired
    private ExcellentAthletesService excellentAthletesService;

    /**
     * 获取优秀运动员列表
     * @author 李泽聿
     * @since 2025-09-18 21:04
     * @return
     * <pre>
     * {@code
     * {
     *   "data": [
     *     {
     *       "id": "1",
     *       "name": "John",
     *       "age": "25",
     *       "grade": "A",
     *       "imgUrl": "https://via.placeholder.com/150",
     *       "introduction": "John is a good athlete"
     *     },
     *     {
     *       "id": "2",
     *       "name": "Mary",
     *       "age": "26",
     *       "grade": "B",
     *       "imgUrl": "https://via.placeholder.com/150",
     *       "introduction": "Mary is a good athlete"
     *     },
     *     {
     *       "id": "3",
     *       "name": "LiHUa",
     *       "age": "27",
     *       "grade": "C",
     *       "imgUrl": "https://via.placeholder.com/150",
     *       "introduction": "Tom is a good athlete"
     *     },
     *     {
     *       "id": "4",
     *       "name": "Tom",
     *       "age": "28",
     *       "grade": "D",
     *       "imgUrl": "https://via.placeholder.com/150",
     *       "introduction": "Jane is a good athlete"
     *     }
     *   ],
     *   "message": "成功",
     *   "statusCode": 200
     * }
     * }
     * </pre>
     */

    @GetMapping("/getExcellenceList")
    public Map<String, Object> getExcellenceList() {
        return excellentAthletesService.getExcellenceList();
    }

    @GetMapping("/getExcellenceDetail")
    public Map<String, Object> getExcellentDetail(@RequestParam("id") Integer id) {
        return excellentAthletesService.getExcellentDetail(id);
    }
}
