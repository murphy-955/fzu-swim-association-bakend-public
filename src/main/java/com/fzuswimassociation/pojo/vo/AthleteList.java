package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.enm.FzuAcademyEnum;
import lombok.Data;

/**
 *
 * @author 李泽聿
 * @since 2025-09-11 10:53
 */


@Data
public class AthleteList {
    private String name;
    private String academicNumber;
    private FzuAcademyEnum college;
}
