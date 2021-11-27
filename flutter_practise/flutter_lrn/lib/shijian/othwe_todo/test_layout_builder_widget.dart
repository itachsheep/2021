

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';

class TestLayoutBuilderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var _children = List.filled(8, Text("测试"));
    var _children2 = List.filled(8, Text("无限制"));
    return Scaffold(
      appBar: AppBar(
        title: Text("layoutBuilder控件"),
      ),
      body: Column(
        children: [
          SizedBox(
            width: 190,
            child: ResponsiveColumn(children: _children),
          ),

          ResponsiveColumn(children: _children2),

          LayoutLogPrint(child: Text("xxx"), tag: "TestLayoutBuilderWidget")

        ],
      ),
    );
  }

}

class ResponsiveColumn extends StatelessWidget {
  final List<Widget> children;

  ResponsiveColumn({Key? key, required this.children}):super(key: key);

  @override
  Widget build(BuildContext context) {
    // 通过 LayoutBuilder 拿到父组件传递的约束，然后判断 maxWidth 是否小于200
    return LayoutBuilder(
        builder: (BuildContext ctx, BoxConstraints constraints) {
          if(constraints.maxWidth < 200) {
            return Column(children: children,
              mainAxisSize: MainAxisSize.min,
            );
          } else {
            // 大于200，显示双列
            var _children = <Widget>[];
            for (var i = 0; i < children.length; i += 2) {
              if (i + 1 < children.length) {
                _children.add(Row(
                  children: [children[i], children[i + 1]],
                  mainAxisSize: MainAxisSize.min,
                ));
              } else {
                _children.add(children[i]);
              }
            }
            return Column(children: _children,
              mainAxisSize: MainAxisSize.min
            );
          }
        }
    );
  }
}

class LayoutLogPrint extends StatelessWidget {
  final Widget child;
  final String tag;

  LayoutLogPrint({Key? key,required this.child, required this.tag}): super(key: key);

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
        builder: (_, constraints){
          assert( () {
            // print('${tag ?? key ?? child}: $constraints');
            LogUtils.d(tag,"$child: $constraints");
            return true;
          }());
          return child;
        },
    );
  }

}
