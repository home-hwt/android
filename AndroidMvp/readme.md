MVP  
M（model）负责数据的请求，解析，过滤等数据操作  
V（View）负责图示部分展示，图示事件处理，Activity，Fragment，Dialog，ViewGroup等呈现视图的组件都可以承担该角色  
P（presenter）是View和Model交互的桥梁  

`最大的一点在于持有关系上，MVP呢是Presenter 持有View 和Model 作为两者的桥梁，这样也是理所应当的。
同时View只持有Presenter。和Model没有任何关系。那么这样的话，业务就是业务，很纯粹。而View毕竟是视图方面的，
与用户交互相关，那么用户交互了就得涉及相关的业务处理，所以持有presenter，让其作为桥梁控制Model的业务。
所以一切业务相关的界面变化，估计也是在Presenter里面连接进行间接控制的。说白了就是View与Model无联系无联系！`