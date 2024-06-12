## 工厂方法模式：

### 类图：

![工厂方法](D:\HuaweiMoveData\Users\小心一点.LAPTOP-FMCHFI2D\Desktop\软工\fje\工厂方法.jpeg)

为每个style和icon新建一个工厂方法，具体的leaf和container就会根据工厂方法的设置产生。

## 抽象工厂模式：

### 类图：![抽象工厂](D:\HuaweiMoveData\Users\小心一点.LAPTOP-FMCHFI2D\Desktop\软工\fje\抽象工厂.png)

ComponentFactory作为抽象工厂，Tree和Rectangle作为两个具体工厂，Component作为抽象产品，Leaf和Container作为具体产品。

## 组合模式

### 类图：

![组合模式](D:\HuaweiMoveData\Users\小心一点.LAPTOP-FMCHFI2D\Desktop\软工\fje\组合模式.jpeg)

**组件** （Component) 接口描述了树中简单项目和复杂项目所共有的操作。

**叶节点** （Leaf） 是树的基本结构， 它不包含子项目。

一般情况下， 叶节点最终会完成大部分的实际工作， 因为它们无法将工作指派给其他部分。

**容器** （Container）——又名 “组合 （Composite）”——是包含叶节点或其他容器等子项目的单位。 容器不知道其子项目所属的具体类， 它只通过通用的组件接口与其子项目交互。

## 最终效果：

![6762dbbc100c79ed29dc686e0bec02f](D:\wechat\WeChat Files\wxid_ur3nf8p4g56p22\FileStorage\Temp\6762dbbc100c79ed29dc686e0bec02f.png)

![c4fd9903907dc863ec6b600c51cc3f9](D:\wechat\WeChat Files\wxid_ur3nf8p4g56p22\FileStorage\Temp\c4fd9903907dc863ec6b600c51cc3f9.png)

![396a8d2dd483f83e163d89d33acff2f](D:\wechat\WeChat Files\wxid_ur3nf8p4g56p22\FileStorage\Temp\396a8d2dd483f83e163d89d33acff2f.png)

![fc97b21153bb8a8af3fc67ae2562d52](D:\wechat\WeChat Files\wxid_ur3nf8p4g56p22\FileStorage\Temp\fc97b21153bb8a8af3fc67ae2562d52.png)# FunnyJsonExplorer
