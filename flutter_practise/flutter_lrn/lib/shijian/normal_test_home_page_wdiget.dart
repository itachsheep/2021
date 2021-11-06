

import 'package:flutter/cupertino.dart';
import 'package:flutter_lrn/shijian/parent_widget_manager.dart';
import 'package:flutter_lrn/shijian/tap_box_a_state.dart';
import 'package:flutter_lrn/shijian/test_clip_widget.dart';
import 'package:flutter_lrn/shijian/test_constrain_box_widget.dart';
import 'package:flutter_lrn/shijian/test_flex_widget.dart';
import 'package:flutter_lrn/shijian/test_layout_builder_widget.dart';
import 'package:flutter_lrn/shijian/test_single_scroll.dart';
import 'package:flutter_lrn/shijian/test_stack_widget.dart';

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
    return TestSingleScrollWidget();
  }

}