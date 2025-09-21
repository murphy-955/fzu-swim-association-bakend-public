package com.fzuswimassociation.test;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李泽聿
 * @since 2025-08-30 09:19
 */

@SpringBootTest
public class TestReplacePublishFiguresUtil {
    @Test
    public void testReplacePublishFiguresUtil() {
        // 匹配LiHua
        System.out.println("my LiHua LiHua name".matches(""));
    }

    @Test
    public void testReplacePublishFiguresUtil2() {
        String allowedEvents = "";
        for (ActivityTypesEnum i : ActivityTypesEnum.values()) {
            allowedEvents = allowedEvents.concat(String.valueOf(i));
            allowedEvents = allowedEvents.concat("&&");
        }
        System.out.println(allowedEvents);
    }

    @Test
    public void testReplacePublishFiguresUtil3() {
        String str1 = "AAA&&BBB&&CCC";
        String str2 = "BBB";
        System.out.println(str1.contains(str2));
    }

    @Test
    public void testReplacePublishFiguresUtil4() {
        String str = "MAN_BREASTSTROKE_100M&&MAN_BREASTSTROKE_100M&&";
        String[] split = str.split("&&");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(str);
    }

    @Test
    public void testReplacePublishFiguresUtil5() {
        try {
            String manFreestyle50M = FzuAcademyEnum.valueOf("DEPARTMENT_OF_PHYSICAL_EDUCATION_AND_RESEARCH").getAcademyName();
            System.out.println(manFreestyle50M);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
