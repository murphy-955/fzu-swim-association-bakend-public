# 游泳协会后端项目

由于已经在私有仓库中进行过几次调试和业务功能的新增，所以版本直接是1.2.0

- 报名业务见[这里](docs/报名流程.md)

## 项目功能

| 功能           | 状态  | 实现                                                                                           |
|--------------|-----|----------------------------------------------------------------------------------------------|
| 新闻上传，浏览      | 已完成 | [上传+浏览实现类](src/main/java/com/fzuswimassociation/controller/NewsController.java)              |
| 运动等级查询（前端实现） | /   | [静态数据](src/main/resources/static/swimmingTechnicalLevel.json)                                |
| 领导/负责人展示     | 已完成 | [领导实现类](src/main/java/com/fzuswimassociation/controller/LeaderController.java)               |
| 优秀运动员展示      | 已完成 | [优秀运动员实现类](src/main/java/com/fzuswimassociation/controller/ExcellentAthletesController.java) |                                                                                |
| 运动员报名        | 已完成 | [报名实现类](src/main/java/com/fzuswimassociation/controller/SportController.java)                |                                                                                |
| 管理员操作        | 已完成 | [管理员实现类](src/main/java/com/fzuswimassociation/controller/ManagerController.java)             |                                                                                |
| 缓存更新         | 待完成 | [定时任务类](src/main/java/com/fzuswimassociation/schedule/ClearRedisCacheSchedule.java)          |                                                                                |

**[前端项目](https://github.com/WuJunkai2004/swimming_frontend)**