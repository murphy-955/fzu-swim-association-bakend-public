package com.fzuswimassociation.test;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.until.EnumToListUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试枚举值转List工具类
 *
 * @author 李泽聿
 * @since 2025-09-15 13:56
 */

@SpringBootTest
public class TestEnumToListUtilUtil {
    @Test
    public void MyTest() {
        List<ActivityTypesEnum> activityTypesEnums = EnumToListUtil.enumToList("MAN_FREESTYLE_1500M&&WOMAN_FREESTYLE_800M", ActivityTypesEnum.class);
        for (ActivityTypesEnum activityTypesEnum : activityTypesEnums){
            System.out.println(activityTypesEnum);
        }
    }
}
