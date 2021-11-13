import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/route_bean.dart';
import 'package:flutter_lrn/shijian/test_inherited_widget.dart';

class InductionAllWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => InductionAllWidgetState();
}

class InductionAllWidgetState extends State<InductionAllWidget>
    with SingleTickerProviderStateMixin {
  // List tabs = <String>["新闻", "历史", "图片"];
  final tabs = <String>['基础组件', '功能型和事件', '动画和自定义'];
  Widget getPagedView(String content) {
    return Container(
      color: Colors.green[200],
      child: Center(
        child: Text(content),
      ),
    );
  }

  // 构建 header
  Widget buildHeader(int i) {
    return Container(
      color: Colors.lightBlue.shade200,
      alignment: Alignment.centerLeft,
      child: Text("PersistentHeader $i"),
    );
  }

  @override
  void initState() {
    super.initState();
    //tabController = TabController(length: tabs.length, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: tabs.length,
        child: Scaffold(
          body: NestedScrollView(
            headerSliverBuilder: (ctx, innerBoxIsScrolled){
              return <Widget>[

                SliverOverlapAbsorber(
                  handle: NestedScrollView.sliverOverlapAbsorberHandleFor(ctx),
                  sliver: SliverAppBar(
                    pinned: true,
                    floating: true,
                    title: Text("归纳"),
                    expandedHeight: 150,
                    flexibleSpace: FlexibleSpaceBar(
                      background: Image.asset(
                        "pics/bg.jpeg",
                        fit: BoxFit.cover,
                      ),
                    ),
                    bottom: TabBar(
                      tabs: tabs.map((String name) => Tab(text: name)).toList(),
                    ),
                    forceElevated: innerBoxIsScrolled,
                  ),
                ),

                /*SliverAppBar(
                  snap: true,
                  floating: true,
                  forceElevated: innerBoxIsScrolled,
                  pinned: true, // 滑动到顶端时会固定住
                  expandedHeight: 250.0,
                  flexibleSpace: FlexibleSpaceBar(
                    background: Image.asset(
                      "pics/bg.jpeg",
                      fit: BoxFit.cover,
                    ),
                  ),
                  bottom: TabBar(
                    tabs: tabs.map((String name) => Tab(text: name)).toList(),
                  ),
                )*/
              ];
            },
            body:  TabBarView(
              children: tabs.map((name){
                return Builder(builder: (ctx) {
                  return CustomScrollView(
                    key: PageStorageKey<String>(name),
                    slivers:<Widget>[
                      SliverOverlapInjector(
                        handle: NestedScrollView.sliverOverlapAbsorberHandleFor(ctx),
                      ),
                      SliverPadding(
                        padding: const EdgeInsets.all(8.0),
                        sliver: builderSilver(ctx,name,50),
                      ),
                    ],
                  );
                });
              }).toList(),
            ),
          ),
        )
    );
  }

  Widget builderSilver(BuildContext ctx,String name, int count) {
    if(name == tabs[1]) {//功能型和事件
        var list = <RouteBean>[];
        list.add(new RouteBean("数据共享",TestInheritedWidget()));
        list.add(new RouteBean("数据共享1",TestInheritedWidget()));
        list.add(new RouteBean("数据共享2",TestInheritedWidget()));
        list.add(new RouteBean("数据共享3",TestInheritedWidget()));
        list.add(new RouteBean("数据共享3",TestInheritedWidget()));
        list.add(new RouteBean("数据共享4",TestInheritedWidget()));
        list.add(new RouteBean("数据共享5",TestInheritedWidget()));
        list.add(new RouteBean("数据共享6",TestInheritedWidget()));
        list.add(new RouteBean("数据共享7",TestInheritedWidget()));

        return SliverFixedExtentList(
          delegate: SliverChildBuilderDelegate((ctx,index){
            //创建列表项
            return Container(
              alignment: Alignment.center,
              color: Colors.lightBlue[100 * (index % 9)],
              child: TextButton(
                child: Text(list[index].name),
                onPressed:(){
                  Navigator.push(
                      ctx,
                      MaterialPageRoute(builder:(ctx) {
                        return list[index].route;
                      }),
                  );
                }
                ),
            );},
              childCount: list.length
          ),
          itemExtent: 50,
        );
    } else {
      return SliverFixedExtentList(
        delegate: SliverChildBuilderDelegate((ctx,index){
          //创建列表项
          return Container(
            alignment: Alignment.center,
            color: Colors.lightBlue[100 * (index % 9)],
            child: Text('list item $index'),
          );},
            childCount: count
        ),
        itemExtent: 50,
      );
    }
    }

}
