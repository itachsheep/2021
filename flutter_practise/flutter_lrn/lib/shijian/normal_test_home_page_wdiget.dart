

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/othwe_todo/parent_widget_manager.dart';
import 'package:flutter_lrn/shijian/othwe_todo/tap_box_a_state.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_clip_widget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_constrain_box_widget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_flex_widget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_grid_view.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_layout_builder_widget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_list_view.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_list_view_head.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_list_view_more.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_page_view.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_scroll_notification.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_scroller.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_single_scroll.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_stack_widget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_will_scope.dart';

class NormalTestHomePage extends StatelessWidget {
  const NormalTestHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    // return const TapBoxA();
    // return const ParentWidget();
    // return TestConstrainWidget();
    // return TestFlexWidget();
    // return TestStackWidget();
    // return TestLayoutBuilderWidget();
    // return TestClipWidget();
    // return TestSingleScrollWidget();
    // return TestListViewWidget();
    // return TestLoadMoreListView();
    // return TestListViewHeadWidget();
    // return TestScrollerWidget();
    return Scaffold(
      appBar: AppBar(
        title: Text("gridview"),
      ),
      // body: ScrollNotificationTestRoute(),
      // body: TestGridViewWidget(),
      // body: TestPageView(),
      body: WillPopScopeTestRoute(),
    );
  }

}