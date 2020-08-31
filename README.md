# base
### 这是一个后台管理系统一个脚手架
  * 前端使用**vue + element ui**框架,前端源代码地址：https://github.com/yixuan30/vue_base
  * 后台使用**spring boot2 + shiro + mybatispuls + mysql**数据库，后台源代码地址：https://github.com/yixuan30/base/new/master
  * 前后台通信使用axios.
### 实现功能：
	1.用户管理：实现用户的增删改，模糊查询，为用户分配相应的角色。
	2.角色管理：实现角色的增删改，模糊查询，为角色分配相应的菜单。
	3.菜单管理：模块的增删改，模糊查询。
### 使用表总共是5张：
   fz_user(用户表)、fz_menu(菜单表)、fz_role(角色表)、fz_role_menu(角色菜单表)、fz_user_role(用户角色表)、关系如下：
  * 用户《------》角色（多对多），中间表（用户菜单表）
  * 角色《------》菜单（多对多），中间表（角色菜单表）
### 项目展示
#### 后台系统首页
![后台首页](https://education-1010.oss-cn-beijing.aliyuncs.com/Snipaste_2020-08-31_20-41-28.png)
#### 用户管理页
![用户管理页](https://education-1010.oss-cn-beijing.aliyuncs.com/Snipaste_2020-08-31_16-45-27.png)
#### 角色管理页
![角色管理页](https://education-1010.oss-cn-beijing.aliyuncs.com/Snipaste_2020-08-31_16-45-38.png)
#### 菜单管理页
![菜单管理页](https://education-1010.oss-cn-beijing.aliyuncs.com/Snipaste_2020-08-31_16-44-55.png)
