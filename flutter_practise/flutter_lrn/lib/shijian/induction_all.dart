import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class InductionAllWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => InductionAllWidgetState();
}

class InductionAllWidgetState extends State<InductionAllWidget>
    with SingleTickerProviderStateMixin {
  // List tabs = <String>["新闻", "历史", "图片"];
  final tabs = <String>['猜你喜欢', '今日特价', '发现更多'];
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
                        sliver: builderSilver(50),
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

  Widget builderSilver(int count) {
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
