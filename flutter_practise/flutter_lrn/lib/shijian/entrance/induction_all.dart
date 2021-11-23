import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/animate/test_scale_animate.dart';
import 'package:flutter_lrn/shijian/color_theme/test_color_widget.dart';
import 'package:flutter_lrn/shijian/color_theme/test_theme_widget.dart';
import 'package:flutter_lrn/shijian/color_theme/test_value_builder_listener.dart';
import 'package:flutter_lrn/shijian/dialog/test_dialog.dart';
import 'package:flutter_lrn/shijian/feature_builder/test_feature_builder.dart';
import 'package:flutter_lrn/shijian/feature_builder/test_stream_builder.dart';
import 'package:flutter_lrn/shijian/route_bean.dart';
import 'package:flutter_lrn/shijian/share_data/test_inherited_widget.dart';
import 'package:flutter_lrn/shijian/share_data/test_share_data_provider_widget.dart';
import 'package:flutter_lrn/shijian/touch_event/test_adjust_notification.dart';
import 'package:flutter_lrn/shijian/touch_event/test_geesture_detector.dart';
import 'package:flutter_lrn/shijian/touch_event/test_gesture_drag.dart';
import 'package:flutter_lrn/shijian/touch_event/test_hint_test.dart';
import 'package:flutter_lrn/shijian/touch_event/test_notification.dart';
import 'package:flutter_lrn/shijian/touch_event/test_receive_event_bus.dart';
import 'package:flutter_lrn/shijian/touch_event/test_touch_event.dart';

class InductionAllWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => InductionAllWidgetState();
}

class InductionAllWidgetState extends State<InductionAllWidget>
    with SingleTickerProviderStateMixin {
  // List tabs = <String>["新闻", "历史", "图片"];
  final tabs = <String>['功能型和事件', '动画和自定义'];//+基础组件

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
            headerSliverBuilder: (ctx, innerBoxIsScrolled) {
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
                      tabs: tabs.map((String name) {
                        return Tab(
                          text: name,
                        );
                      }).toList(),
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
            body: TabBarView(
              children: tabs.map((name) {
                return Builder(builder: (ctx) {
                  return CustomScrollView(
                    key: PageStorageKey<String>(name),
                    slivers: <Widget>[
                      SliverOverlapInjector(
                        handle: NestedScrollView.sliverOverlapAbsorberHandleFor(
                            ctx),
                      ),
                      SliverPadding(
                        padding: const EdgeInsets.all(8.0),
                        sliver: builderSilver(ctx, name, 50),
                      ),
                    ],
                  );
                });
              }).toList(),
            ),
          ),
        ));
  }

  Widget builderSilver(BuildContext ctx, String name, int count) {
    List<RouteBean> list = [];
    if (name == tabs[0]) {
      //功能型和事件
      initFunctionEventList(list);
    } else if(name == tabs[1]) {
      //动画和自定义
      initAnimateAdjustViewList(list);
    }

    return SliverFixedExtentList(
      delegate: SliverChildBuilderDelegate((ctx, index) {
        //创建列表项
        return Container(
          alignment: Alignment.center,
          // color: Colors.lightBlue[100 * (1 % 9)],
          decoration: BoxDecoration(
            color: Colors.lightBlue[100 * (1 % 9)],
          ),
          child: TextButton(
              child: Text(list[index].name),
              onPressed: () {
                Navigator.push(
                  ctx,
                  MaterialPageRoute(builder: (ctx) {
                    return list[index].route;
                  }),
                );
              }),
        );
      }, childCount: list.length),
      itemExtent: 50,
    );
  }

  void initAnimateAdjustViewList(List<RouteBean> list) {
    /**************动画******************/
    list.add(RouteBean("缩放动画", TestScaleAnimate("缩放动画")));
  }

  void initFunctionEventList(List<RouteBean> list) {
    // var list = <RouteBean>[];
    /**************事件和手势******************/
    list.add(RouteBean("指针事件", TestOnPointWidget("指针事件")));
    list.add(RouteBean("手势", TestGestureWidget("手势")));
    list.add(RouteBean("手势-拖动", TestDragWidget("手势-拖动")));
    list.add(RouteBean("事件机制", HintTestWidget("事件机制")));
    list.add(RouteBean("bus事件总线", ReceivedBusWidget("事件总线")));
    list.add(RouteBean("通知", TestNotification("通知")));
    list.add(RouteBean("自定义通知", AdjustNotificationWidget("自定义通知")));
    
    
    /**************功能型组件******************/
    list.add( RouteBean("数据共享", TestInheritedWidget("数据共享")));
    list.add(
         RouteBean("跨组件共享provider", TestShareDataProviderWidget("跨组件")));
    list.add( RouteBean("颜色", TestColorTheme("颜色")));
    list.add( RouteBean("主题", TestTheme()));
    list.add( RouteBean("横向数据共享", TestValueListenableWidget("横向数据流")));
    list.add( RouteBean("异步ui刷新", TestFutureBuilderWidget("异步ui刷新")));
    list.add( RouteBean(
        "异步ui刷新StreamBuilder", TestStreamBuilder("异步ui刷新StreamBuilder")));
    list.add( RouteBean("对话框",  TestDialog("对话框")));
  }
  //else {
//       return SliverFixedExtentList(
//         delegate: SliverChildBuilderDelegate((ctx, index) {
//           //创建列表项
//           return Container(
//             alignment: Alignment.center,
//             color: Colors.lightBlue[100 * (1 % 9)],
//             child: Text(
//               'list item $index',
//               style: TextStyle(),
//             ),
//           );
//         }, childCount: count),
//         itemExtent: 50,
//       );
//     }
}
