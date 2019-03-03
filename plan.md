# 前端
## 展示界面
- worker 路径的展示 Y
- Task 的展示 Y
- Point of interest 的展示 Y
- worker 和 point of interest 在界面上展示 Y

# 管理界面
## 实体添加 
- worker 的添加  Y
- point of interest 的添加 Y
- （删除元素的功能先不做）

## 重置
- 删除所有的schedule，数据库中worker 的坐标一直保持不变 Y


# 补充的功能
- 删除 schedule 中的所有信息，可以说是重置 Y
- 如何更新worker 的实时位置 (已有方案)
- 如何判断worker 已经经过了中间的点，此时可以把已经经过的 event 删除 （严重的问题）
- 前端定时向后台发送请求，并把数据保存在数据库中

# 可优化的地方
- schedule 的交互部分，当前数据量不是很大，没有影响。
- 可考虑把数据放到 redis 中，运行效率更高
- 用 map 把路线的信息存储起来


# 需要补充的知识
- vue ajax, 方法, 变量
- json
- 表单的提取
- css 的设记
