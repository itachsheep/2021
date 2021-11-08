
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestTabView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestTabViewState();
}

class TestTabViewState extends State<TestTabView> with SingleTickerProviderStateMixin{
  List tabs = ["新闻", "历史", "图片"];

  //TabController 用于监听和控制 TabBarView 的页面切换，通常和 TabBar 联动。
  // 如果没有指定，则会在组件树中向上查找并使用最近的一个 DefaultTabController 。
  late TabController tabController;

  @override
  void initState() {
    super.initState();
    tabController = TabController(length: tabs.length, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("tab"),
        bottom: TabBar(
          controller: tabController,
          tabs: tabs.map((e) => Tab(text: e)).toList(),
        ),
      ),
      body: TabBarView(
        controller: tabController,
        children: tabs.map((e){
          return Container(
            alignment: Alignment.center,
            child: Text(e,textScaleFactor: 5,),
          );
        }).toList(),
      ),
    );
  }
}