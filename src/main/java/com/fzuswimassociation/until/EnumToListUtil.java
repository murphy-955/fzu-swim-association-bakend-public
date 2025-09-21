package com.fzuswimassociation.until;

import com.fzuswimassociation.enm.ActivityTypesEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将数据库中的枚举值转为List
 *
 * @author 李泽聿
 * @since 2025-09-15 09:51
 */


public class EnumToListUtil {
    public static <E extends Enum<E>> List<E> enumToList(String dbStr, Class<E> classEnum) {
        if (dbStr != null && !dbStr.isEmpty()) {
            return Arrays.stream(dbStr.split("&&"))
                    .map(String::trim)
                    .map(s -> Enum.valueOf(classEnum, s))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static  List<String> activityToChineseList(String dbStr, Class<ActivityTypesEnum> classEnum) {
        if (dbStr != null && !dbStr.isEmpty()) {
            return Arrays.stream(dbStr.split("&&"))
                    .map(String::trim)
                    .map(s -> Enum.valueOf(classEnum, s).getName())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}

