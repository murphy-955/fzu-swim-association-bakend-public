package com.fzuswimassociation.until;

import com.fzuswimassociation.mappers.ExcellentAthletesMapper;
import com.fzuswimassociation.mappers.LeaderMapper;
import com.fzuswimassociation.pojo.ExcellentAthletes;
import com.fzuswimassociation.pojo.Leader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 将公共人物姓名替换为a标签工具类
 *
 * @author 李泽聿
 * @since 2025-08-28 10:41
 */

@Component
public class ReplacePublishFiguresUtil {

    @Autowired
    private ExcellentAthletesMapper excellentAthletesMapper;

    @Autowired
    private LeaderMapper leaderMapper;

    /**
     * 替换后格式化内容<br>
     * 优秀运动员格式：{@code <a href="/excellentAthletes/getExcellentAthletesById?id=%s">%s</a>}<br>
     * 领导格式：{@code <a href="/leaders/getLeaderById?id=%s">%s</a>}<br>
     *
     * @param content 原始内容
     * @return 替换后的内容
     * @author 李泽聿
     * @since 2025-09-07 14:49
     */

    public String replaceContent(String content) {
        for (ExcellentAthletes i : excellentAthletesMapper.getExcellentAthletesList()) {
            // 使用正则表带，匹配内容中是否含有人名
            if (content.matches(".*%s.*".formatted(i.getName()))) {
                String tagA = """
                        <a href="/excellentAthletes/getExcellentAthletesById?id=%s">%s</a>""".formatted(i.getId(), i.getName());
                content = content.replaceAll(i.getName(), tagA);
                System.out.println(i.getId());
            }
        }

        for (Leader i : leaderMapper.getLeadersList()) {
            if (content.matches(i.getName())) {
                String tagA = """
                        <a href="/leaders/getLeaderById?id=%s">%s</a>""".formatted(i.getId(), i.getName());
                content = content.replaceAll(i.getName(), tagA);
            }
        }
        return content;
    }

}
