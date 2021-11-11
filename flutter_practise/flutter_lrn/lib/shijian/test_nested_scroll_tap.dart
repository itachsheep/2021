

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NestedScrollTabViewWidget extends StatelessWidget {

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

  @override
  Widget build(BuildContext context) {
    final _tabs = <String>['猜你喜欢', '今日特价', '发现更多'];
    return DefaultTabController(
        length: _tabs.length,
        child: Scaffold(
          body: NestedScrollView(
            headerSliverBuilder:(ctx,innerBoxIsScrolled){
              return <Widget>[
                SliverOverlapAbsorber(
                    handle: NestedScrollView.sliverOverlapAbsorberHandleFor(ctx),
                  sliver: SliverAppBar(
                    title: Text("商城"),
                    floating: true,
                    snap: true,
                    forceElevated: innerBoxIsScrolled,
                    bottom: TabBar(
                      tabs: _tabs.map((String name) => Tab(text: name)).toList(),
                    ),
                  ),
                )
              ];
            },
            body: TabBarView(
              children: _tabs.map((name){
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
}